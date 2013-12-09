/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Heap;

import ConstantPoolTypes.CONSTANT_Class;
import ConstantPoolTypes.CONSTANT_NameAndType_info;
import ConstantPoolTypes.ConstantPoolElem;
import Descriptors.FieldDescriptor;
import java.util.HashMap;

/**
 *
 * @author stopka
 */
public class Instance {
    HashMap<String, Object> fields;
    ConstantPoolTypes.CONSTANT_Class class_source;

    public Instance(CONSTANT_Class class_source) {
        this.fields = new HashMap();
        this.class_source = class_source;
    }
    
    public void put(FieldDescriptor fd,Object value){
        fields.put(fd.getField(), value);
    }
    
    public Object get(FieldDescriptor fd){
        return fields.get(fd.getField());
    }
    
    public String toString(){
        return class_source.toString();
    }
    
}
