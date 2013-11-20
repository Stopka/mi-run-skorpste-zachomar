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
public class _if extends InstructionElem implements ControlElem {

    int branch;
    int condition;
    boolean jump;

    _if(byte cond, Queue<Byte> queue, int pos) {
        super(pos);
        condition = StaticLibrary.ByteToInt(cond);
        branch = (queue.poll() << 8) + queue.poll();
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool, LocalVariableTableAttribute table) {
        int val = (int) VariableStack.pop();
        jump = CompareVal(val);
    }

    @Override
    public int JumpTo() {
        if (jump) {
            return branch;
        }
        return -1;
    }

    private boolean CompareVal(int val) {
        switch (condition) {
            case 0x99: //eq
                return val == 0;
            case 0x9a: //ne
                return val != 0;
            case 0x9b: //lt
                return val < 0;
            case 0x9c: //le
                return val <= 0;
            case 0x9d:
                return val > 0;
            case 0x9e:
                return val >= 0;
        }
        return false;
    }
}
