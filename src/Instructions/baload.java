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
public class baload extends InstructionElem {

    public baload(int pos) {
        super(pos);

    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool, LocalVariableTableAttribute table) {

        int index = Convertor.toInt(VariableStack.pop());
        boolean[] arr = (boolean[]) VariableStack.pop();
        VariableStack.push(arr[index]);
    }
}
