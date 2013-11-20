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
 * @author Zachy Load reference from local variable The <n> must be an index
 * into the local variable array
 * VALID
 */
public class aload_ extends InstructionElem {

    int index;

    public aload_(byte b, int pos) {
        super(pos);
        index = b - 42;
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool,
            LocalVariableTableAttribute table) {
        VariableStack.push(table.getLocalVariableTable()[index].value);
    }
}
