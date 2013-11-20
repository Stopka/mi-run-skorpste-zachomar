/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConstantPoolTypes;

import static ConstantPoolTypes.TagStatics.*;
import java.util.Queue;
import myjava.StaticLibrary;

/**
 *
 * @author Zachy
 */
public class ConstantGenerator {
    private static int cnt = 1;
    public static ConstantPoolElem createConstantPoolElem(Queue<Byte> queue) {
        int tagstatic = queue.poll();
        int cindex;
        int ntindex;
        int sindex;
        int bytes;
        int h_bytes;
        int l_bytes;
        int nindex;
        int dindex;
        int rindex;
        int bmai;
        byte rkind;
        switch (tagstatic) {
            case CONSTANT_Class:
                nindex = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
                return new CONSTANT_Class(nindex);
            case CONSTANT_Fieldref:
                cindex = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
                ntindex = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
                return new CONSTANT_Fieldref_info(cindex, ntindex);
            case CONSTANT_Methodref:
                cindex = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
                ntindex = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
                return new CONSTANT_Methodref_info(cindex, ntindex);
            case CONSTANT_InterfaceMethodref:
                cindex = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
                ntindex = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
                return new CONSTANT_InterfaceMethodref_info(cindex, ntindex);
            case CONSTANT_String:
                sindex = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
                return new CONSTANT_String_info(sindex);
            case CONSTANT_Integer:
                bytes = (StaticLibrary.ByteToInt(queue.poll()) << 24) + (StaticLibrary.ByteToInt(queue.poll()) << 16)
                        + (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
                return new CONSTANT_Integer_info(bytes);
            case CONSTANT_Float:
                bytes = (StaticLibrary.ByteToInt(queue.poll()) << 24) + (StaticLibrary.ByteToInt(queue.poll()) << 16)
                        + (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
                return new CONSTANT_Float_info(bytes);
            case CONSTANT_Long:
                h_bytes = (StaticLibrary.ByteToInt(queue.poll()) << 24) + (StaticLibrary.ByteToInt(queue.poll()) << 16)
                        + (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
                l_bytes = (StaticLibrary.ByteToInt(queue.poll()) << 24) + (StaticLibrary.ByteToInt(queue.poll()) << 16)
                        + (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
                return new CONSTANT_Long_info(h_bytes, l_bytes);
            case CONSTANT_Double:
                h_bytes = (StaticLibrary.ByteToInt(queue.poll()) << 24) + (StaticLibrary.ByteToInt(queue.poll()) << 16)
                        + (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
                l_bytes = (StaticLibrary.ByteToInt(queue.poll()) << 24) + (StaticLibrary.ByteToInt(queue.poll()) << 16)
                        + (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
                return new CONSTANT_Double_info(h_bytes, l_bytes);
            case CONSTANT_NameAndType:
                nindex = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
                dindex = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
                return new CONSTANT_NameAndType_info(nindex, dindex);
            case CONSTANT_Utf8:
                return new CONSTANT_Utf8_info(queue);
            case CONSTANT_MethodHandle:
                rkind = queue.poll();
                rindex = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
                return new CONSTANT_MethodHandle_info(rkind, rindex);
            case CONSTANT_MethodType:
                dindex = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
                return new CONSTANT_MethodType_info(dindex);
            case CONSTANT_InvokeDynamic:
                bmai = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
                ntindex = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
                return new CONSTANT_InvokeDynamic_info(bmai, ntindex);
        }
        return null;
    }
}
