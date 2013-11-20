/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConstantPoolTypes;

/**
 *
 * @author Zachy
 */
public class CONSTANT_MethodType_info extends ConstantPoolElem{
    int descriptor_index;

    public CONSTANT_MethodType_info(int descriptor_index) {
        super(TagStatics.CONSTANT_MethodType);
        this.descriptor_index = descriptor_index;
    }

    @Override
    public Object GetValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
