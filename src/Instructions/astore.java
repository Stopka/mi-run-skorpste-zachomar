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
 * VALID
 */
public class astore extends InstructionElem {
    byte index;

    public astore(Queue<Byte> queue, int pos) {
        super(pos);
        index = queue.poll();
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool, LocalVariableTableAttribute table) {
        Object o =  VariableStack.pop();
        table.getLocalVariableTable()[index].value = o;
    }
}
