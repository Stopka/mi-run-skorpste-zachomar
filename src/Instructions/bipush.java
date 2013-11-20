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
 * VALID
 */
public class bipush extends InstructionElem {

    byte b;

    public bipush(Queue<Byte> queue, int pos) {
        super(pos);
        b = queue.poll();
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool, LocalVariableTableAttribute table) {
        VariableStack.push(StaticLibrary.ByteToInt(b));
    }
}
