/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package myjava;

import Heap.Heap;
import Heap.InbuiltObject;
import java.util.Arrays;

/**
 *
 * @author stopka
 */
public class Convertor {
    public static int toInt(Object o){
        if(o instanceof Character){
            return (int) ((Character)o).charValue();
        }
        if(o instanceof Long){
            return (int) ((Long)o).longValue();
        }
        if(o instanceof Boolean){
            return (((Boolean)o).booleanValue())?1:0;
        }
        return (int)o;
    }
    
     public static long toLong(Object o){
        if(o instanceof Character){
            return (long) ((Character)o).charValue();
        }
        if(o instanceof Integer){
            return (long) ((Integer)o).intValue();
        }
        
        return (long)o;
    }

    public static Object toHeapedObject(Object o) {
        if(o instanceof byte[]){
            return o;
        }
        if(o instanceof String){
            return o;
        }
        if(o instanceof Integer){
            return o;
        }
        if(o instanceof Long){
            return o;
        }
        if(o instanceof Character){
            return o;
        }
        return Heap.getHeap().store(new InbuiltObject(o));
    }
    
    public static boolean toBoolean(Object o) {
        if(o instanceof Character){
            return ((Character)o).charValue()>0;
        }
        if(o instanceof Long){
            return ((Long)o).longValue()>0;
        }
        if(o instanceof Integer){
            return ((Integer)o).intValue()>0;
        }
        return (boolean)o;
    }
    
}
