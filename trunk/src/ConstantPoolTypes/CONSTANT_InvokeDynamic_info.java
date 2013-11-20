/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConstantPoolTypes;

/**
 *
 * @author Zachy
 */
public class CONSTANT_InvokeDynamic_info extends ConstantPoolElem{
    int bootstrapMethodAttrIndex;
    int nameAndTypeIndex;

    public CONSTANT_InvokeDynamic_info(int bootstrapMethodAttrIndex, int nameAndTypeIndex) {
        super(TagStatics.CONSTANT_InvokeDynamic);
        this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    @Override
    public Object GetValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
