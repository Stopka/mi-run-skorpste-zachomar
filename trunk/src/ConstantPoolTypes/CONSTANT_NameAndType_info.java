/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConstantPoolTypes;

import Descriptors.MethodDescriptor;
import Parser.Parser;

/**
 *
 * @author Zachy
 */
public class CONSTANT_NameAndType_info extends ConstantPoolElem{
    int name_index;
    int descriptor_index;

    public CONSTANT_NameAndType_info(int name_index, int descriptor_index) {
        super(TagStatics.CONSTANT_NameAndType);
        this.name_index = name_index;
        this.descriptor_index = descriptor_index;
    }

    @Override
    public String toString() {
        return pool[name_index-1] + "";
    }

    @Override
    public Object GetValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public MethodDescriptor GetDescriptor(){
        return new MethodDescriptor(pool[descriptor_index -1].toString());
    }
}
