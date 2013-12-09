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
 * iload_<n>
 * The <n> must be an index into the local variable array of the current frame (§2.6).
 * The local variable at <n> must contain an int.
 * The value of the local variable at <n> is pushed onto the operand stack.
 * VALID
 */
public class iload_ extends InstructionElem{
    int index;
    public iload_(int b, int pos){
        super(pos);
        index = b - 26;
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool,
    LocalVariableTableAttribute table) {
        VariableStack.push(Convertor.toInt(table.getLocalVariable(index)));
     }
}
