/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Attributes.CodeAttribute;
import Attributes.LocalVariableTableAttribute;
import ConstantPoolTypes.CONSTANT_Fieldref_info;
import ConstantPoolTypes.CONSTANT_Methodref_info;
import ConstantPoolTypes.CONSTANT_NameAndType_info;
import ConstantPoolTypes.ConstantPoolElem;
import Descriptors.MethodDescriptor;
import Heap.HeapRef;
import Heap.InbuiltObject;
import Infos.MethodInfo;
import Parser.Parser;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import myjava.StaticLibrary;

/**
 *
 * @author Zachy invokespecial indexbyte1 indexbyte2 The unsigned indexbyte1 and
 * indexbyte2 are used to construct an index into the run-time constant pool of
 * the current class (§2.6), where the value of the index is (indexbyte1 << 8) |
 * indexbyte2. The run-time constant pool item at that index must be a symbolic
 * reference to a method (§5.1), which gives the name and descriptor (§4.3.3) of
 * the method as well as a symbolic reference to the class in which the method
 * is to be found. The named method is resolved (§5.4.3.3). Finally, if the
 * resolved method is protected (§4.6), and it is a member of a superclass of
 * the current class, and the method is not declared in the same run-time
 * package (§5.3) as the current class, then the class of objectref must be
 * either the current class or a subclass of the current class.
 */
public class invokespecial extends InstructionElem {

    int index;

    public invokespecial(int pos, Queue<Byte> queue) {
        super(pos);
        index = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool,
            LocalVariableTableAttribute table) {

        String function = constantPool[index - 1].toString();
        CONSTANT_Methodref_info i = (CONSTANT_Methodref_info) constantPool[index - 1];
        MethodInfo info = Parser.instance.getMethodByName(i.getName().toString(), i.getClassName());
        if (info != null) {
            //if ("<init>".equals(i.getName().toString())) {
                CodeAttribute attr = info.GetCodeAttribute(constantPool);
                CONSTANT_NameAndType_info nt = (CONSTANT_NameAndType_info) i.getName();
                Class[] arr = nt.GetMethodDescriptor().getInput();
                attr.AssignLocalVariables(VariableStack, arr);
                attr.ExecuteCode();
                if(attr.HasReturnValue()){
                    VariableStack.push(attr.getReturnValue());
                }
            /*} else {
                //TODO volání metody
                
            }*/
        } else {
            if("java/lang/Object".equals(i.getClassName())){
                VariableStack.pop();
                return;
            }
            if ("<init>".equals(i.getName().toString())) {
                construct(i, VariableStack);
                return;
            } else {
                throw new UnsupportedOperationException();
            }
        }
    }

    private void construct(CONSTANT_Methodref_info info, Stack<Object> VariableStack) {
        try {
            CONSTANT_NameAndType_info nt = (CONSTANT_NameAndType_info) info.getName();
            MethodDescriptor desc = nt.GetMethodDescriptor();
            Class c = StaticLibrary.LoadClass(info.getClassName());
            List<Object> args = new LinkedList<>();
            Constructor construct;
            Class[] classes = desc.getInput();
            Class out = desc.getOutput();
            for (int i = 0; i < classes.length; i++) {
                args.add(VariableStack.pop());
            }
            construct = c.getConstructor(classes);
            InbuiltObject ibo=(InbuiltObject)((HeapRef)VariableStack.pop()).get();
            ibo.setInstance(construct.newInstance(args.toArray()));
        } catch (Exception ex) {
            ex.printStackTrace(); //FileReader new (filepath) pada pri neexistujicim souboru
        }
    }
}
