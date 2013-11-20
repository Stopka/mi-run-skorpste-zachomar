/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Attributes.LocalVariableTableAttribute;
import ConstantPoolTypes.ConstantPoolElem;
import java.io.PrintStream;
import java.util.Queue;
import java.util.Stack;
import myjava.StaticLibrary;

/**
 *
 * @author Zachy getstatic indexbyte1 indexbyte2 The unsigned indexbyte1 and
 * indexbyte2 are used to construct an index into the run-time constant pool of
 * the current class (§2.6), where the value of the index is (indexbyte1 << 8) |
 * indexbyte2. The run-time constant pool item at that index must be a symbolic
 * reference to a field (§5.1), which gives the name and descriptor of the field
 * as well as a symbolic reference to the class or interface in which the field
 * is to be found. The referenced field is resolved (§5.4.3.2).
 *
 * On successful resolution of the field, the class or interface that declared
 * the resolved field is initialized (§5.5) if that class or interface has not
 * already been initialized.
 *
 * The value of the class or interface field is fetched and pushed onto the
 * operand stack.
 */
public class getstatic extends InstructionElem {

    int index;

    public getstatic(Queue<Byte> queue, int pos) {
        super(pos);
        index = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool, LocalVariableTableAttribute table) {
        VariableStack.push(constantPool[index-1]);
    }
}
