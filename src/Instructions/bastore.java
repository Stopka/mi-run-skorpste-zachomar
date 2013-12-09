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
class bastore extends InstructionElem {

    public bastore(int pos) {
        super(pos);
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool, LocalVariableTableAttribute table) {
        
        boolean value = Convertor.toBoolean(VariableStack.pop());
        int index = Convertor.toInt(VariableStack.pop());
        boolean[] array = (boolean[]) VariableStack.pop();
        array[index] = value;
    }
    
}
