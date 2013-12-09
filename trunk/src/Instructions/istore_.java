/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Attributes.LocalVariableTableAttribute;
import Attributes.LocalVariableTableAttribute.LocalVariable;
import ConstantPoolTypes.ConstantPoolElem;
import java.util.Stack;
import myjava.Convertor;

/**
 *
 * @author Zachy istore_<n>
 * The <n> must be an index into the local variable array of the current frame
 * (§2.6). The value on the top of the operand stack must be of type int. It is
 * popped from the operand stack, and the value of the local variable at <n> is
 * set to value.
 * VALID
 */
public class istore_ extends InstructionElem {

    int index;

    public istore_(byte b, int pos) {
        super(pos);
        index = b - 59;
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool,
            LocalVariableTableAttribute table) {
        int value = Convertor.toInt(VariableStack.pop());
        table.setLocalVariable(index, value);
    }
}
