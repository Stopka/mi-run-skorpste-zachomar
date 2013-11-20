/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Infos;

import Attributes.AttributeGenerator;
import Attributes.AttributeInfo;
import Attributes.AttributeInfoUndefined;
import ConstantPoolTypes.ConstantPoolElem;
import Parser.Parser;
import java.text.AttributedCharacterIterator;
import java.util.Queue;
import myjava.StaticLibrary;

/**
 *
 * @author Zachy
 */
public class FieldInfo {

    private int accessFlags;
    private int nameIndex;
    private int descriptorIndex;
    private int attributesCnt;
    private AttributeInfo[] attributes;
    private ConstantPoolElem[] pool;

    public FieldInfo(Queue<Byte> queue) {
        accessFlags = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        nameIndex = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        descriptorIndex = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        attributesCnt = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
        for (int i = 0; i < attributesCnt; i++) {
            attributes[i] = AttributeGenerator.createAttributeElem(queue,pool);
        }
    }
    
}
