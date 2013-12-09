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
 * Return void from method
 */
public class _return extends ControlElem {
    public _return(int pos){
        super(pos);
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool,
    LocalVariableTableAttribute table) {
        
    }

    @Override
    public int JumpTo() {
        return -1;
    }

    @Override
    public boolean isToJump() {
        return true;
    }

    @Override
    public boolean isFinal() {
        return true;
    }
    
    
}
