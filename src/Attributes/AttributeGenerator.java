/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attributes;

import ConstantPoolTypes.CONSTANT_Utf8_info;
import ConstantPoolTypes.ConstantPoolElem;
import java.util.Queue;
import myjava.StaticLibrary;

/**
 *
 * @author Zachy
 */
public class AttributeGenerator {

    public static AttributeInfo createAttributeElem(Queue<Byte> queue, ConstantPoolElem[] pool) {
        int index = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        String name = ((CONSTANT_Utf8_info) pool[index - 1]).toString();
        switch (name) {
            case "Code":
                return new CodeAttribute(index, queue, pool);
            case "LineNumberTable":
                return new LineNumberTableAttribute(index, queue);
            case "LocalVariableTable":
                return new LocalVariableTableAttribute(index, queue);
            case "SourceFile":
                return new SourceFileAttribute(index, queue);
        }
        return new AttributeInfoUndefined(index, queue);
    }
}
