/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Attributes.LocalVariableTableAttribute;
import ConstantPoolTypes.ConstantPoolElem;
import java.util.Queue;
import java.util.Stack;
import myjava.Convertor;
import myjava.StaticLibrary;

/**
 *
 * @author Zachy
 */
public class lcmp  extends InstructionElem {

    public lcmp(int pos) {
        super(pos);
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool, LocalVariableTableAttribute table) {
        long value1=Convertor.toLong(VariableStack.pop());
        long value2=Convertor.toLong(VariableStack.pop());
        int result=0;
        if(value1>value2){
            result=1;
        }
        if(value1<value2){
            result=-1;
        }
        VariableStack.push(result);
    }
}
