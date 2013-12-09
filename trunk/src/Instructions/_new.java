/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Attributes.LocalVariableTableAttribute;
import ConstantPoolTypes.ConstantPoolElem;
import Heap.Heap;
import java.util.Queue;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import myjava.StaticLibrary;

/**
 *
 * @author Zachy
 */
public class _new extends InstructionElem {

    int index;

    public _new(Queue<Byte> queue, int pos) {
        super(pos);
        index = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool, LocalVariableTableAttribute table) {
        try {
            ConstantPoolElem e = constantPool[index -1];
            Object o = e.GetValue();
            Heap h=Heap.getHeap();
            VariableStack.push(h.store(o));
        } catch (Exception e) {
            System.err.println("Instruction _new ERR: ");
            e.printStackTrace();
        }
    }
}
