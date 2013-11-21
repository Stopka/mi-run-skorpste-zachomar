/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Attributes.CodeAttribute;
import Attributes.LocalVariableTableAttribute;
import ConstantPoolTypes.CONSTANT_Fieldref_info;
import ConstantPoolTypes.CONSTANT_Methodref_info;
import ConstantPoolTypes.CONSTANT_NameAndType_info;
import ConstantPoolTypes.ConstantPoolElem;
import Descriptors.MethodDescriptor;
import Infos.MethodInfo;
import Parser.Parser;
import java.io.BufferedReader;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import myjava.StaticLibrary;

/**
 *
 * @author Zachy invokevirtual indexbyte1 indexbyte2
 */
public class invokevirtual extends InstructionElem {

    int index;

    public invokevirtual(Queue<Byte> queue, int pos) {
        super(pos);
        index = (StaticLibrary.ByteToInt(queue.poll()) << 8) + StaticLibrary.ByteToInt(queue.poll());
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool, LocalVariableTableAttribute table) {
        String function = constantPool[index - 1].toString();
        CONSTANT_Methodref_info i = (CONSTANT_Methodref_info) constantPool[index - 1];
        MethodInfo info = Parser.instance.getMethodByName(i.getName().toString(), i.getClassName());
        if (info != null) {
            CodeAttribute attr = info.GetCodeAttribute(constantPool);
            CONSTANT_NameAndType_info nt = (CONSTANT_NameAndType_info) i.getName();
            Class[] arr = nt.GetMethodDescriptor().getInput();
            attr.AssignLocalVariables(VariableStack, arr);
            attr.ExecuteCode();
            throw new UnsupportedOperationException("Nevyresene navratove hodnoty");
            /*  if (attr.HasReturnValue()) {
             VariableStack.push(attr.getReturnValue());
             }*/
        } else {
            try {
                CONSTANT_NameAndType_info nt = (CONSTANT_NameAndType_info) i.getName();
                MethodDescriptor desc = nt.GetMethodDescriptor();
                Class c = StaticLibrary.LoadClass(i.getClassName());
                List<Object> args = new LinkedList<>();
                Class[] classes = desc.getInput();
                Class out = desc.getOutput();
                for (int j = 0; j < classes.length; j++) {
                    args.add(VariableStack.pop());
                }
                Method m = c.getDeclaredMethod(i.getName().toString(), classes);
                Object o = VariableStack.pop();
                Object retval = null;
                try {
                    retval = m.invoke(o, args.toArray());
                } catch (Exception e) {
                    retval = m.invoke(System.out, args.toArray());
                }
                if (out != null) {
                    VariableStack.push(retval);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
