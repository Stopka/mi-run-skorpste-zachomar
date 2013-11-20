/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Attributes.CodeAttribute;
import Attributes.LocalVariableTableAttribute;
import ConstantPoolTypes.CONSTANT_Fieldref_info;
import ConstantPoolTypes.CONSTANT_Methodref_info;
import ConstantPoolTypes.ConstantPoolElem;
import Infos.MethodInfo;
import Parser.Parser;
import java.io.PrintStream;
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
            attr.AssignLocalVariables(VariableStack);
            attr.ExecuteCode();
            if (attr.HasReturnValue()) {
                VariableStack.push(attr.getReturnValue());
            }
        } else {
            try {
                Class c = StaticLibrary.LoadClass(i.getClassName());
                List<Object> args = new LinkedList<>();
                List<Class> classes = new LinkedList<>();
                CONSTANT_Fieldref_info met = null;
                while (!VariableStack.empty()) {
                    ConstantPoolElem o = (ConstantPoolElem) VariableStack.pop();
                    if (o instanceof CONSTANT_Fieldref_info) {
                        met = (CONSTANT_Fieldref_info) o;
                        break;
                    } else {
                        args.add(o.GetValue());
                        classes.add(o.GetValue().getClass());
                    }
                    break;
                }
                Method m = c.getDeclaredMethod(i.getName().toString(), classes.toArray(new Class[0]));
                Object o = null;
                try{
                   o= c.newInstance();
                }catch(InstantiationException e){
                    o = System.out;
                }
                m.invoke(o, args.toArray());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
