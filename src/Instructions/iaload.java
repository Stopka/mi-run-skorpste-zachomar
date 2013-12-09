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
public class iaload extends InstructionElem {

    public iaload(int pos) {
        super(pos);

    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool, LocalVariableTableAttribute table) {

        int index = Convertor.toInt(VariableStack.pop());
        int[] arr = (int[]) VariableStack.pop();
        VariableStack.push(arr[index]);
    }
}
