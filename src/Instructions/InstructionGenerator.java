/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Attributes.CodeAttribute;
import ConstantPoolTypes.ConstantPoolElem;
import com.sun.org.apache.bcel.internal.generic.IASTORE;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Zachy
 */
public class InstructionGenerator {

    public static InstructionElem[] CreateInstructionElems(byte[] arr, CodeAttribute attr) {
        Queue<Byte> queue = new LinkedList<>();
        int length = arr.length;
        for (int i = 0; i < arr.length; i++) {
            queue.add(arr[i]);
        }
        List<InstructionElem> list = new LinkedList<>();
        while (!queue.isEmpty()) {
            list.add(CreateInstructionElem(queue, length, attr));
        }
        return list.toArray(new InstructionElem[0]);
    }

    private static InstructionElem CreateInstructionElem(Queue<Byte> queue, int initialLength, CodeAttribute attr) {
        int currentPosition = initialLength - queue.size();
        byte bajt = queue.poll();
        int b = bajt & 0xff;
        if (b == 0x0) {
            return new nop(currentPosition);
        } else if (b == 0x1) {
            return new aconst_null(currentPosition);
        } else if (b > 0x1 && b <= 0x8) {
            return new iconst_((byte) b, currentPosition);
        } else if (b > 0x8 && b < 0xb) {
            System.out.println(bajt);
            System.out.println(b); //TO DEFINE----------------------------
            return null;
        } else if (b >= 0xb && b < 0xe) {
            return new fconst_(bajt, currentPosition);
        } else if (b == 0xe || b == 0xf) {
            return new dconst_(bajt, currentPosition);
        } else if (b == 0x10) {
            return new bipush(queue, currentPosition);
        } else if (b == 0x11) {
            System.out.println(bajt);
            System.out.println(b); //TO DEFINE----------------------------
            return null;
        } else if (b == 0x12) {
            return new ldc(queue, b);
        } else if (b == 0x13) {
            System.out.println(bajt);
            System.out.println(b); //TO DEFINE----------------------------
            return null;
        } else if (b == 0x14) {
            return new ldc2_w(queue, currentPosition);
        } else if (b > 0x14 && b < 0x19) {
            System.out.println(bajt);
            System.out.println(b); //TO DEFINE----------------------------
            return null;
        } else if (b == 0x19) {
            return new aload(queue, currentPosition);
        } else if (b >= 0x1a && b < 0x1e) {
            return new iload_(b, currentPosition);
        } else if (b >= 0x1e && b < 0x22) {
            System.out.println(bajt);
            System.out.println(b); //TO DEFINE----------------------------
            return null;
        } else if (b >= 0x22 && b < 0x26) {
            return new fload_(bajt, currentPosition);
        } else if (b >= 0x26 && b < 0x2a) {
            return new dload_(bajt, currentPosition);
        } else if (b >= 0x2a && b < 0x2e) {
            return new aload_(bajt, currentPosition);
        } else if (b == 0x2e) {
            return new iaload(currentPosition);
        } else if (b >= 0x2f && b < 0x3a) {
            System.out.println(bajt);
            System.out.println(b); //TO DEFINE----------------------------
            return null;
        } else if (b == 0x3a) {
            return new astore(queue, currentPosition);
        } else if (b >= 0x3b && b < 0x3f) {
            return new istore_((byte) b, currentPosition);
        } else if (b >= 0x3f && b < 0x43) {
            System.out.println(bajt);
            System.out.println(b); //TO DEFINE----------------------------
            return null;
        } else if (b >= 0x43 && b < 0x47) {
            return new fstore_(bajt, currentPosition);
        } else if (b >= 0x47 && b < 0x4b) {
            System.out.println(bajt);
            System.out.println(b); //TO DEFINE----------------------------
            return null;
        } else if (b >= 0x4b && b < 0x4f) {
            return new astore_(bajt, currentPosition);
        } else if (b == 0x4f) {
            return new iastore(currentPosition);
        } else if (b >= 0x50 && b < 0x57) {
            System.out.println(bajt);
            System.out.println(b); //TO DEFINE----------------------------
            return null;
        } else if (b == 0x57) {
            return new pop(currentPosition);
        } else if (b == 0x58) {
            System.out.println(bajt);
            System.out.println(b); //TO DEFINE----------------------------
            return null;
        } else if (b == 0x59) {
            return new dup(currentPosition);
        } else if (b >= 0x5a && b < 0x60) {
            System.out.println(bajt);
            System.out.println(b); //TO DEFINE----------------------------
            return null;
        } else if (b == 0x60) {
            return new iadd(currentPosition);
        } else if (b >= 0x61 && b < 0x64) {
            System.out.println(bajt);
            System.out.println(b); //TO DEFINE----------------------------
            return null;
        } else if (b == 0x64) {
            return new isub(currentPosition);
        } else if (b >= 0x65 && b < 0x84) {
            System.out.println(bajt);
            System.out.println(b); //TO DEFINE----------------------------
            return null;
        } else if (b == 0x84) {
            return new iinc(queue, currentPosition);
        } else if (b >= 0x85 && b < 0x95) {
            System.out.println(bajt);
            System.out.println(b); //TO DEFINE----------------------------
            return null;
        } else if (b == 0x95 || b == 0x96) {
            return new fcmp(bajt, currentPosition);
        } else if (b == 0x97 || b == 0x98) {
            return new dcmp(bajt, currentPosition);
        } else if (b >= 0x99 && b < 0x9f) {
            return new _if(bajt, queue, currentPosition);
        } else if (b >= 0x9f && b < 0xa2) {
            System.out.println(bajt);
            System.out.println(b); //TO DEFINE----------------------------
            return null;
        } else if (b == 0xa2) {
            return new if_icmp(bajt, queue, currentPosition);
        } else if (b >= 0xa3 && b < 0xa7) {
            System.out.println(bajt);
            System.out.println(b); //TO DEFINE----------------------------
            return null;
        } else if (b == 0xa7) {
            return new _goto(queue, currentPosition);
        } else if (b >= 0xa8 && b < 0xac) {
            System.out.println(bajt);
            System.out.println(b); //TO DEFINE----------------------------
            return null;
        } else if (b == 0xac) {
            return new ireturn(currentPosition, attr);
        } else if (b >= 0xad && b < 0xb0) {
            System.out.println(bajt);
            System.out.println(b); //TO DEFINE----------------------------
            return null;
        } else if (b == 0xb0) {
            return new areturn(currentPosition, attr);
        } else if (b == 0xb1) {
            return new _return(currentPosition);
        } else if (b == 0xb2) {
            return new getstatic(queue, currentPosition);
        } else if (b == 0xb3) {
            System.out.println(bajt);
            System.out.println(b);
            return null;
        } else if (b == 0xb4) {
            return new getfield(queue, currentPosition);
        } else if (b == 0xb5) {
            return new putfield(queue, currentPosition);
        } else if (b == 0xb6) {
            return new invokevirtual(queue, currentPosition);
        } else if (b == 0xb7) {
            return new invokespecial(currentPosition, queue);
        } else if (b == 0xb8) {
            return new invokestatic(queue, currentPosition);
        } else if (b >= 0xb9 && b < 0xbb) {
            System.out.println(bajt);
            System.out.println(b);
            return null;
        } else if (b == 0xbb) {
            return new _new(queue, currentPosition);
        } else if (b == 0xbc) {
            return new newarray(queue, currentPosition);
        } else if (b == 0xbd) {
            System.out.println(bajt);
            System.out.println(b);
            return null;
        } else if (b == 0xbe) {
            return new arraylength(currentPosition);
        } else if (b >= 0xbf && b < 0xc6) {
            System.out.println(bajt);
            System.out.println(b);
            return null;
        } else if (b == 0xc6) {
            return new ifnull(queue, currentPosition);
        } else {
            System.out.println(bajt);
            System.out.println(b);
            return null;
        }
    }
}
