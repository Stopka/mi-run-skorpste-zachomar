/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Attributes.LocalVariableTableAttribute;
import ConstantPoolTypes.ConstantPoolElem;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Zachy
 */
public class ifnull extends InstructionElem {

    int branch;

    public ifnull(Queue<Byte> queue, int pos) {
        super(pos);
        branch = (queue.poll() << 8) + queue.poll();
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool, LocalVariableTableAttribute table) {
        System.out.println("sdfds");
        throw new UnsupportedOperationException();
    }
}
