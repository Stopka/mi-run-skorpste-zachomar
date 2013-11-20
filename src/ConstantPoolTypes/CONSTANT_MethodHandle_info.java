/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConstantPoolTypes;

/**
 *
 * @author Zachy
 */
public class CONSTANT_MethodHandle_info extends ConstantPoolElem{
    byte referenceKind;
    int referenceIndex;

    public CONSTANT_MethodHandle_info(byte referenceKind, int referenceIndex) {
        super(TagStatics.CONSTANT_MethodHandle);
        this.referenceKind = referenceKind;
        this.referenceIndex = referenceIndex;
    }

    @Override
    public Object GetValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
