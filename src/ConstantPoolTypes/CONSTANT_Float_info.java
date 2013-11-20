/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConstantPoolTypes;

/**
 *
 * @author Zachy
 */
public class CONSTANT_Float_info extends ConstantPoolElem {

    int bytes;

    public CONSTANT_Float_info(int bytes) {
        super(TagStatics.CONSTANT_Float);
        this.bytes = bytes;
    }

    @Override
    public Object GetValue() {
        return bytes;
    }
}