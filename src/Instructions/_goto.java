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
public class _goto extends InstructionElem implements ControlElem {

    short index;

    public _goto(Queue<Byte> queue, int pos) {
        super(pos);
        index =  (short) ((StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll()));
       // index = index & 0xFFFF; //because of 65536 equals 0!
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool, LocalVariableTableAttribute table) {
        //You need to JUMP
    }

    @Override
    public int JumpTo() {
        return index;
    }
}
