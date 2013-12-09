/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attributes;

import java.util.Queue;
import myjava.StaticLibrary;

/**
 *
 * @author Zachy LocalVariableTable_attribute { u2 attribute_name_index; u4
 * attribute_length; u2 local_variable_table_length; { u2 start_pc; u2 length;
 * u2 name_index; u2 descriptor_index; u2 index; }
 * local_variable_table[local_variable_table_length]; }
 */
public class LocalVariableTableAttribute extends AttributeInfo {

    int nameIndex;
    int attrLength;
    int localVariableTableLength;
    LocalVariable[] localVariableTable;

    public class LocalVariable {

        public int startPC;
        public int length;
        public int nameIndex;
        public int descriptorIndex;
        public int index;
        public Object value;

        public LocalVariable(Queue<Byte> queue) {
            value = null;
            startPC = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
            length = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
            nameIndex = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
            descriptorIndex = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
            index = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        }
    }

    public LocalVariableTableAttribute(int nameIndex, Queue<Byte> queue) {
        this.nameIndex = nameIndex;
        attrLength = (StaticLibrary.ByteToInt(queue.poll()) << 24) + (StaticLibrary.ByteToInt(queue.poll()) << 16)
                + (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        localVariableTableLength = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        localVariableTable = new LocalVariable[localVariableTableLength];
        for (int i = 0; i < localVariableTable.length; i++) {
            localVariableTable[i] = new LocalVariable(queue);
        }
    }

    public LocalVariable[] getLocalVariableTable() {
        return localVariableTable;
    }
    
    public Object getLocalVariable(int index){
        for(LocalVariable var:localVariableTable){
            if(var.index==index){
                return var.value;
            }
        }
        return null;
    }
    
    public void setLocalVariable(int index,Object value){
        for(LocalVariable var:localVariableTable){
            if(var.index==index){
                 var.value=value;
            }
        }
    }
}
