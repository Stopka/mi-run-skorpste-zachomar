/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attributes;

import Attributes.LocalVariableTableAttribute.LocalVariable;
import ConstantPoolTypes.ConstantPoolElem;
import Instructions.ControlElem;
import Instructions.InstructionElem;
import Instructions.InstructionGenerator;
import Parser.Parser;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import myjava.StaticLibrary;

/**
 *
 * @author Zachy
 */
/*
 * 
 * Code_attribute {
 u2 attribute_name_index;
 u4 attribute_length;
 u2 max_stack;
 u2 max_locals;
 u4 code_length;
 u1 code[code_length];
 u2 exception_table_length;
 {   u2 start_pc;
 u2 end_pc;
 u2 handler_pc;
 u2 catch_type;
 } exception_table[exception_table_length];
 u2 attributes_count;
 attribute_info attributes[attributes_count];
 }
 */
public class CodeAttribute extends AttributeInfo {

    int nameIndex;
    int length;
    int maxStack;
    int maxLocals;
    int codeLength;
    byte code[];
    int attrCnt;
    AttributeInfo[] attributes;
    int exceptionTableLength;
    ExceptionItem[] exceptionTable;
    InstructionElem[] instructions; //derived from code
    LineNumberTableAttribute lineNumberTable;
    LocalVariableTableAttribute localVariableTable;
    Stack<Object> localVariableStack;
    Object returnValue;
    boolean hasReturnValue;
    private ConstantPoolElem[] pool;

    public Stack<Object> getLocalVariableStack() {
        return localVariableStack;
    }

    class ExceptionItem {

        int start;
        int end;
        int handler;
        int catchType;

        public ExceptionItem(Queue<Byte> queue) {
            start = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
            end = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
            handler = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
            catchType = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        }
    }

    public CodeAttribute(int nameIndex, Queue<Byte> queue, ConstantPoolElem[] pool) {
        this.pool = pool;
        returnValue = null;
        hasReturnValue = false;
        localVariableStack = new Stack<>();
        this.nameIndex = nameIndex;
        this.length = (StaticLibrary.ByteToInt(queue.poll()) << 24) + (StaticLibrary.ByteToInt(queue.poll()) << 16)
                + (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        maxStack = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        maxLocals = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        codeLength = (StaticLibrary.ByteToInt(queue.poll()) << 24) + (StaticLibrary.ByteToInt(queue.poll()) << 16)
                + (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        code = new byte[codeLength];
        for (int i = 0; i < codeLength; i++) {
            code[i] = queue.poll();
        }
        exceptionTableLength = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        exceptionTable = new ExceptionItem[exceptionTableLength];
        for (int i = 0; i < exceptionTable.length; i++) {
            exceptionTable[i] = new ExceptionItem(queue);
        }
        attrCnt = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        attributes = new AttributeInfo[attrCnt];
        for (int i = 0; i < attributes.length; i++) {
            attributes[i] = AttributeGenerator.createAttributeElem(queue, pool);
        }
        instructions = InstructionGenerator.CreateInstructionElems(code, this);
        AssignAttributes();
    }

    public Object getReturnValue() {
        return returnValue;
    }

    public boolean HasReturnValue() {
        return hasReturnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
        hasReturnValue = true;
    }

    private void AssignAttributes() {
        for (AttributeInfo i : attributes) {
            if (i.getClass() == LineNumberTableAttribute.class) {
                lineNumberTable = (LineNumberTableAttribute) i;
            }
            if (i.getClass() == LocalVariableTableAttribute.class) {
                localVariableTable = (LocalVariableTableAttribute) i;
            }

        }
    }

    public void AssignLocalVariables(Stack<Object> stack, Class[] classes) {
        LocalVariable[] var = localVariableTable.getLocalVariableTable();
        for (int j = 0; j < classes.length; j++) {
            localVariableTable.getLocalVariableTable()[j + 1].value = stack.pop();
        }
        localVariableTable.getLocalVariableTable()[0].value = stack.pop();//snad staci takhle
    }

    public void InitVariableStack(Stack<Object> stack) {
        localVariableStack.addAll(stack);
    }

    public void ExecuteCode() {
        for (int i = 0; i < instructions.length; i++) {
         //   System.out.println(instructions[i]);
                 instructions[i].ExcecuteInstruction(localVariableStack, pool, localVariableTable);
            if (instructions[i] instanceof ControlElem) {
                ControlElem control = (ControlElem) instructions[i];
                int offset = control.JumpTo();
                if (offset != -1) {
                    i = FindInstructionIndex(instructions[i].getPosition() + offset) - 1;
                }
            }
        }
    }

    private int FindInstructionIndex(int position) {
        //BinarySearch possible, instructions are sorted!!!
        //SOURCE: http://www.roseindia.net/tutorial/java/core/binarySearch.html
        int start, end, midPt;
        start = 0;
        end = instructions.length - 1;
        while (start <= end) {
            midPt = (start + end) / 2;
            if (instructions[midPt].getPosition() == position) {
                return midPt;
            } else if (instructions[midPt].getPosition() < position) {
                start = midPt + 1;
            } else {
                end = midPt - 1;
            }
        }
        return -1;
    }
}
