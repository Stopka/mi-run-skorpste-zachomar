/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConstantPoolTypes;

import java.util.Queue;
import myjava.StaticLibrary;

/**
 *
 * @author Zachy
 */
public class CONSTANT_Utf8_info extends ConstantPoolElem {
    int length;
    byte[] bytes;
    public CONSTANT_Utf8_info(Queue<Byte> queue){
        super(TagStatics.CONSTANT_Utf8);
        length = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        bytes = new byte[length];
        for(int i = 0; i < length;i++)
            bytes[i] = queue.poll();
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public String toString() {
        return new String(bytes);
    }

    @Override
    public Object GetValue() {
        return new String(bytes);
    }
    
}
