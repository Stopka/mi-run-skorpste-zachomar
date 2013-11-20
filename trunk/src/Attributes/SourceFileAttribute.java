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
public class SourceFileAttribute extends AttributeInfo {

    int nameIndex;
    int attributeLength;
    int sourceFileIndex;

    public SourceFileAttribute(int nameIndex, Queue<Byte> queue) {
        this.nameIndex = nameIndex;
        attributeLength = (StaticLibrary.ByteToInt(queue.poll()) << 24) + (StaticLibrary.ByteToInt(queue.poll()) << 16)
                + (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        sourceFileIndex = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
    }
}
