/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Attributes.LocalVariableTableAttribute;
import ConstantPoolTypes.ConstantPoolElem;
import java.util.Stack;

/**
 *
 * @author Zachy
 */
class castore extends InstructionElem {

    public castore(int pos) {
        super(pos);
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool, LocalVariableTableAttribute table) {
        char value;
        if(VariableStack.peek() instanceof Integer){
            value=(char) ((Integer)VariableStack.pop()).intValue();
        }else{
            value = (char) VariableStack.pop();
        }
        int index = (int) VariableStack.pop();
        char[] array = (char[]) VariableStack.pop();
        array[index] = value;
    }
    
}
