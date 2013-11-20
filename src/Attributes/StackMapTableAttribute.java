/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attributes;

import java.util.Queue;
import myjava.StaticLibrary;

/**
 *
 * @author Zachy
 */
public class StackMapTableAttribute extends AttributeInfo{
    int attributeNameIndex;
    int attributeLength;
    int numberOfEntries;
    
    
    public StackMapTableAttribute(int attrNIndex, Queue<Byte> queue){
        attributeLength = (StaticLibrary.ByteToInt(queue.poll()) << 24) + (StaticLibrary.ByteToInt(queue.poll()) << 16)
                + (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        numberOfEntries = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        throw new UnsupportedOperationException();
    }
}
