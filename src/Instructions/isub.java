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
public class isub extends InstructionElem {

    public isub(int pos) {
        super(pos);
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool,
    LocalVariableTableAttribute table) {
        int a = (int)VariableStack.pop();
        int b = (int)VariableStack.pop();
        VariableStack.push((a-b));
    }
}
