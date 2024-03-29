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
public class lshl extends InstructionElem {

    public lshl(int pos) {
        super(pos);
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool, LocalVariableTableAttribute table) {
        long value1 = Convertor.toLong(VariableStack.pop());
        int value2 = Convertor.toInt(VariableStack.pop());
        value2 = value2 & 0x3f;
        long result = value1 << value2;
        VariableStack.push(result);
    }
}
