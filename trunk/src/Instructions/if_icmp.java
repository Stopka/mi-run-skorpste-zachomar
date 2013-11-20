/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Attributes.LocalVariableTableAttribute;
import ConstantPoolTypes.ConstantPoolElem;
import java.util.Queue;
import java.util.Stack;
import myjava.StaticLibrary;

/**
 *
 * @author Zachy
 */
public class if_icmp extends InstructionElem implements ControlElem {

    int condition;
    int branch;
    boolean jump;

    public if_icmp(byte cond, Queue<Byte> queue, int pos) {
        super(pos);
        condition = StaticLibrary.ByteToInt(cond);
        branch = (queue.poll() << 8) + queue.poll();
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool, LocalVariableTableAttribute table) {

        int value2 = (int) VariableStack.pop();
        int value1 = (int) VariableStack.pop();
        jump = CompareVals(value1, value2);
    }

    private boolean CompareVals(int value1, int value2) {
        switch (condition) {
            case 0x9f: //eq
                return value1 == value2;
            case 0xa0: //ne
                return value1 != value2;
            case 0xa1: //gt
                return value1 > value2;
            case 0xa2: //ge
                return value1 >= value2;
            case 0xa3: //lt
                return value1 < value2;
            case 0xa4: //le
                return value1 <= value2;
        }
        return false;
    }

    @Override
    public int JumpTo() {
        if (jump) {
            return branch;
        }
        return -1;
    }
}
