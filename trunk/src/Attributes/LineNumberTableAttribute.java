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
public class LineNumberTableAttribute extends AttributeInfo {

    int nameIndex;
    int attributeLength;
    int lineNumberTableLength;
    LineNumber[] lineNumberTable;

    class LineNumber {

        int startPC;
        int lineNumber;

        public LineNumber(Queue<Byte> queue) {
            startPC = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
            lineNumber = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        }
    }

    public LineNumberTableAttribute(int nIndex, Queue<Byte> queue) {
        nameIndex = nIndex;
        attributeLength = (StaticLibrary.ByteToInt(queue.poll()) << 24) + (StaticLibrary.ByteToInt(queue.poll()) << 16)
                + (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        lineNumberTableLength = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        lineNumberTable = new LineNumber[lineNumberTableLength];
        for (int i = 0; i < lineNumberTable.length; i++) {
            lineNumberTable[i] = new LineNumber(queue);
        }
    }
}
