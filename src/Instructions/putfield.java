/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Attributes.LocalVariableTableAttribute;
import ConstantPoolTypes.CONSTANT_NameAndType_info;
import ConstantPoolTypes.CONSTANT_Utf8_info;
import ConstantPoolTypes.ConstantPoolElem;
import Descriptors.FieldDescriptor;
import Heap.Instance;
import java.util.Queue;
import java.util.Stack;
import myjava.StaticLibrary;

/**
 *
 * @author Zachy set field in Object
 */
public class putfield extends InstructionElem {

    int index;

    public putfield(Queue<Byte> queue, int pos) {
        super(pos);
        index = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool, LocalVariableTableAttribute table) {
        Object val = VariableStack.pop();
        Instance ref = (Instance)((Heap.HeapRef)VariableStack.pop()).get();
        
        ConstantPoolElem e = constantPool[index - 1];
        CONSTANT_NameAndType_info info = (CONSTANT_NameAndType_info) e.GetValue();
        FieldDescriptor fd = info.GetFieldDescriptor();
        
        ref.put(fd,val);
    }

    private void assign(CONSTANT_Utf8_info info, Object value, Object oref) {
    }
}
