/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Attributes.CodeAttribute;
import Attributes.LocalVariableTableAttribute;
import ConstantPoolTypes.CONSTANT_Methodref_info;
import ConstantPoolTypes.CONSTANT_NameAndType_info;
import ConstantPoolTypes.ConstantPoolElem;
import Descriptors.MethodDescriptor;
import Infos.MethodInfo;
import Parser.Parser;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import myjava.Convertor;
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
            CONSTANT_NameAndType_info nt = (CONSTANT_NameAndType_info) i.getName();
            Class[] arr = nt.GetMethodDescriptor().getInput();
            attr.AssignLocalVariables(VariableStack, arr);
            attr.ExecuteCode();
            if (attr.HasReturnValue()) {
                VariableStack.push(attr.getReturnValue());
            }
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
                Method m = c.getMethod(i.getName().toString(), classes);
                Object retval = null;
                boolean hasretval=!out.equals(Void.class);//TODO Co když vrací void?
                try {
                    retval = m.invoke(c, args.toArray());
                } catch (Exception e) {
                    System.out.println(e);
                    System.err.println(args.toArray());
                }
                if (hasretval) {
                    VariableStack.push(Convertor.toHeapedObject(retval));
                }
            } catch (Exception e) {
            }
        }
    }
}
