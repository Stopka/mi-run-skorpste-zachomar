/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Attributes.LocalVariableTableAttribute;
import ConstantPoolTypes.ConstantPoolElem;
import java.util.Stack;
import myjava.Convertor;

/**
 *
 * @author Zachy Add int Both value1 and value2 must be of type int. The values
 * are popped from the operand stack. The int result is value1 + value2. The
 * result is pushed onto the operand stack.
 *
 * The result is the 32 low-order bits of the true mathematical result in a
 * sufficiently wide two's-complement format, represented as a value of type
 * int. If overflow occurs, then the sign of the result may not be the same as
 * the sign of the mathematical sum of the two values.
 *
 * Despite the fact that overflow may occur, execution of an iadd instruction
 * never throws a run-time exception.
 */
public class iadd extends InstructionElem {

    public iadd(int pos) {
        super(pos);
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool,
    LocalVariableTableAttribute table) {
        int a = Convertor.toInt(VariableStack.pop());
        int b = Convertor.toInt(VariableStack.pop());
        VariableStack.push((a+b));
    }
}
