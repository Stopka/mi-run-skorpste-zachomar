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
 * @author Zachy Load reference from local variable The <n> must be an index
 * into the local variable array VALID
 */
public class aload extends InstructionElem {

    byte index;

    public aload(Queue<Byte> queue, int pos) {
        super(pos);
        index = queue.poll();
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool,
            LocalVariableTableAttribute table) {
        VariableStack.push(table.getLocalVariable(index));
    }
}
