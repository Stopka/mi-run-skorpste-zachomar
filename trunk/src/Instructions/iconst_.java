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
 * @author Zachy Push the int constant <i> (-1, 0, 1, 2, 3, 4 or 5) onto the
 * operand stack.
 * VALID
 */
public class iconst_ extends InstructionElem {

    int value;

    public iconst_(byte b, int pos) {
        super(pos);
        value = b - 3;
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool,
            LocalVariableTableAttribute table) {
        VariableStack.push(value);
    }
}
