/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Infos;

import Attributes.AttributeGenerator;
import Attributes.AttributeInfo;
import Attributes.AttributeInfoUndefined;
import Attributes.CodeAttribute;
import ConstantPoolTypes.CONSTANT_Utf8_info;
import ConstantPoolTypes.ConstantPoolElem;
import Parser.Parser;
import java.util.Queue;
import myjava.StaticLibrary;

/**
 *
 * @author Zachy
 */
public class MethodInfo {

    private int accessFlags;
    private int nameIndex;
    private int descriptorIndex;
    private int attributesCnt;
    private AttributeInfo[] attributes;
    private ConstantPoolElem[] pool;

    public MethodInfo(Queue<Byte> queue, ConstantPoolElem[] pool) {
        this.pool = pool;
        accessFlags = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        nameIndex = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        descriptorIndex = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        attributesCnt = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        attributes = new AttributeInfo[attributesCnt];
        for (int i = 0; i < attributesCnt; i++) {
            attributes[i] = AttributeGenerator.createAttributeElem(queue, pool);
        }
    }

    public String GetName(ConstantPoolElem[] pool) {
        CONSTANT_Utf8_info name = (CONSTANT_Utf8_info) pool[nameIndex - 1];
        return new String(name.getBytes());
    }

    public CodeAttribute GetCodeAttribute(ConstantPoolElem[] pool) {
        for (AttributeInfo info : attributes) {
            if (info.getClass() == CodeAttribute.class) {
                return (CodeAttribute)info;
            }
        }
        return null;
    }

    public String GetDescriptor(ConstantPoolElem[] pool) {
        CONSTANT_Utf8_info name = (CONSTANT_Utf8_info) pool[descriptorIndex - 1];
        return new String(name.getBytes());
    }
}
