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
public class caload extends InstructionElem {

    public caload(int pos) {
        super(pos);

    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool, LocalVariableTableAttribute table) {

        int index = (int) VariableStack.pop();
        char[] arr = (char[]) VariableStack.pop();
        VariableStack.push(arr[index]);
    }
}
