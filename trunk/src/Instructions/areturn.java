/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Attributes.CodeAttribute;
import Attributes.LocalVariableTableAttribute;
import ConstantPoolTypes.ConstantPoolElem;
import java.util.Stack;

/**
 *
 * @author Zachy
 */
public class areturn extends _return {

    Object retVal;
    CodeAttribute parent;

    public areturn(int pos, CodeAttribute parent) {
        super(pos);
        this.parent = parent;
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool, LocalVariableTableAttribute table) {
        retVal = VariableStack.pop();
        parent.setReturnValue(retVal);
    }
}
