/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Attributes.LocalVariableTableAttribute;
import ConstantPoolTypes.ConstantPoolElem;
import java.util.Stack;
import myjava.Convertor;

/**
 *
 * @author Zachy
 */
class iastore extends InstructionElem {

    public iastore(int pos) {
        super(pos);
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool, LocalVariableTableAttribute table) {
        
        int value = Convertor.toInt(VariableStack.pop());
        int index = Convertor.toInt(VariableStack.pop());
        int[] array = (int[]) VariableStack.pop();
        array[index] = value;
    }
    
}
