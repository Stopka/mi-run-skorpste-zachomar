/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attributes;

import Parser.Parser;
import java.util.Queue;
import myjava.StaticLibrary;

/**
 *
 * @author Zachy
 */
public class AttributeInfoUndefined extends AttributeInfo {

    private int attributeNameIndex;
    private int attributeLength;
    private byte[] info;

    public AttributeInfoUndefined(int attrNIndex, Queue<Byte> queue) {
        attributeNameIndex = attrNIndex;
        attributeLength = (StaticLibrary.ByteToInt(queue.poll()) << 24) + (StaticLibrary.ByteToInt(queue.poll()) << 16)
                + (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        info = new byte[attributeLength];
        for (int i = 0; i < info.length; i++) {
            info[i] = queue.poll();
        }
    }

    public void Print() {
        System.out.println(new String(info));
    }

    public int getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public int getAttributeLength() {
        return attributeLength;
    }

    public byte[] getInfo() {
        return info;
    }
}
