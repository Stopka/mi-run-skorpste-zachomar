/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Attributes.LocalVariableTableAttribute;
import ConstantPoolTypes.ConstantPoolElem;
import java.util.Queue;
import java.util.Stack;
import myjava.Convertor;
import myjava.StaticLibrary;

/**
 *
 * @author Zachy
 */
public class iinc extends InstructionElem{
int index;
byte cnst;
    public iinc(Queue<Byte> queue, int pos){
        super(pos);
        index = StaticLibrary.ByteToInt(queue.poll());
        cnst = queue.poll();
    }
    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool, LocalVariableTableAttribute table) {
      int val = Convertor.toInt(table.getLocalVariable(index));
      val += cnst;
      table.setLocalVariable(index, val);
    }
    
}
