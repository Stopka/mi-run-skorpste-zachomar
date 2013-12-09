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
 * VALID
 */
public class astore_ extends InstructionElem {

    int index;

    public astore_(byte b, int pos) {
        super(pos);
        index = b - 75;
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool, LocalVariableTableAttribute table) {
        Object o =  VariableStack.pop();
        table.setLocalVariable(index, o);
    }
}
