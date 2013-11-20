/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Attributes.CodeAttribute;
import Attributes.LocalVariableTableAttribute;
import ConstantPoolTypes.CONSTANT_Methodref_info;
import ConstantPoolTypes.ConstantPoolElem;
import Infos.MethodInfo;
import Parser.Parser;
import java.lang.reflect.Method;
import java.util.Queue;
import java.util.Stack;
import myjava.StaticLibrary;

/**
 *
 * @author Zachy
 */
public class invokestatic extends InstructionElem {

    int index;

    public invokestatic(Queue<Byte> queue, int pos) {
        super(pos);
        index = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool, LocalVariableTableAttribute table) {

        CONSTANT_Methodref_info i = (CONSTANT_Methodref_info) constantPool[index - 1];
        MethodInfo info = Parser.instance.getMethodByName(i.getName().toString(), i.getClassName());
        if (info != null) {
            CodeAttribute attr = info.GetCodeAttribute(constantPool);
            attr.AssignLocalVariables(VariableStack);
            attr.ExecuteCode();
            if (attr.HasReturnValue()) {
                VariableStack.push(attr.getReturnValue());
            }
        } else {
            try {
                Class c = StaticLibrary.LoadClass(i.getClassName());
                Class[] classes = new Class[VariableStack.size()];
                int ind = 0;
                Object[] args = new Object[VariableStack.size()];
                while (!VariableStack.empty()) {
                    classes[ind] = VariableStack.peek().getClass();
                    args[ind] = VariableStack.pop();
                    ind++;
                }
                Method m = c.getMethod(i.getName().toString(), classes);
                m.invoke(c, args);
            } catch (Exception e) {
            }
        }
    }
}
