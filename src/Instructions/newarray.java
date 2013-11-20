/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Attributes.LocalVariableTableAttribute;
import ConstantPoolTypes.ConstantPoolElem;
import java.lang.reflect.Array;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Zachy
 * VALID
 */
public class newarray extends InstructionElem {

    byte atype;

    public newarray(Queue<Byte> queue, int pos) {
        super(pos);
        atype = queue.poll();
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool, LocalVariableTableAttribute table) {
        int count = (int) VariableStack.pop();
        VariableStack.add(createArr(count));
    }

    public Object createArr(int length) {
        switch (atype) {
            case 4:
                return new boolean[length];
            case 5:
                return new char[length];
            case 6:
                return new float[length];
            case 7:
                return new double[length];
            case 8:
                return new byte[length];
            case 9:
                return new short[length];
            case 10:
                return new int[length];
            case 11:
                return new long[length];
        }
        return null;
    }
}
