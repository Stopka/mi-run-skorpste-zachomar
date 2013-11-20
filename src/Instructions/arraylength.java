/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

import Attributes.LocalVariableTableAttribute;
import ConstantPoolTypes.ConstantPoolElem;
import com.sun.org.apache.xpath.internal.VariableStack;
import java.lang.reflect.Array;
import java.util.Stack;

/**
 *
 * @author Zachy
 */
public class arraylength extends InstructionElem {

    public arraylength(int pos) {
        super(pos);
    }

    @Override
    public void ExcecuteInstruction(Stack<Object> VariableStack, ConstantPoolElem[] constantPool, LocalVariableTableAttribute table) {
        Object o = VariableStack.pop();
        if (o == null) {
            throw new NullPointerException();
        }
        VariableStack.push(getArrayLength(o));
    }

    int getArrayLength(Object o) {
        //BECAUSE OF STANDARD TYPES 
        if (o instanceof Object[]) {
            return ((Object[]) o).length;
        }else{
            if(o instanceof int[])
                return ((int[]) o).length;
            if(o instanceof boolean[])
                return ((boolean[]) o).length;
            if(o instanceof char[])
                return ((char[]) o).length;
            if(o instanceof float[])
                return ((float[]) o).length;
            if(o instanceof double[])
                return ((double[]) o).length;
            if(o instanceof short[])
                return ((short[]) o).length;
            if(o instanceof byte[])
                return ((byte[]) o).length;
            if(o instanceof long[])
                return ((long[]) o).length;
        }
        return 0;
    }
}
