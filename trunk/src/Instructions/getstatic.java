/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Attributes.LocalVariableTableAttribute;
import ConstantPoolTypes.CONSTANT_NameAndType_info;
import ConstantPoolTypes.ConstantPoolElem;
import Descriptors.FieldDescriptor;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Queue;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        CONSTANT_NameAndType_info nameandtype = (CONSTANT_NameAndType_info) constantPool[index - 1].GetValue();
        FieldDescriptor fd = nameandtype.GetFieldDescriptor();
        Class c = fd.getClazz();
        String n = fd.getField();
        Field f = null;
        try {
            f = c.getDeclaredField(fd.getField());
            Object value = f.get(c);
            VariableStack.push(value);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(getstatic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(getstatic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(getstatic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(getstatic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
