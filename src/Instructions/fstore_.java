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
public class fstore_ extends InstructionElem {
    int index;
    public fstore_(byte b, int pos) {
        super(pos);
        index = 67-b;
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool, LocalVariableTableAttribute table) {
   
        float value = (float) VariableStack.pop();
        table.setLocalVariable(index, value);
    }
}
