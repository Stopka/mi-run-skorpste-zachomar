/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Attributes.LocalVariableTableAttribute;
import ConstantPoolTypes.ConstantPoolElem;
import java.util.Queue;
import java.util.Stack;
import myjava.StaticLibrary;

/**
 *
 * @author Zachy
 */
public class ifnull extends ControlElem {

    int branch;
    boolean jump = false;

    public ifnull(Queue<Byte> queue, int pos) {
        super(pos);
        branch = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool, LocalVariableTableAttribute table) {
        Object o = VariableStack.pop();
        if (o == null) {
            jump = true;
        }
    }

    @Override
    public int JumpTo() {
        return branch;
    }

    @Override
    public boolean isToJump() {
        return jump;
    }
    
    
}
