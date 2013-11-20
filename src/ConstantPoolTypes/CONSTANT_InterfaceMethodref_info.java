/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConstantPoolTypes;

/**
 *
 * @author Zachy
 */
public class CONSTANT_InterfaceMethodref_info extends ConstantPoolElem{
    private int classIndex;
    private int nameAndTypeIndex;
    
    public CONSTANT_InterfaceMethodref_info(int classIndex, int nameAndTymeIndex){
        super(TagStatics.CONSTANT_InterfaceMethodref);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTymeIndex;
    }

    @Override
    public Object GetValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
