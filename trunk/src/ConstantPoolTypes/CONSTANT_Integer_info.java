/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConstantPoolTypes;

/**
 *
 * @author Zachy
 */
public class CONSTANT_Integer_info extends ConstantPoolElem {

    int bytes;

    public CONSTANT_Integer_info(int bytes) {
        super(TagStatics.CONSTANT_Integer);
        this.bytes = bytes;
    }

    @Override
    public Object GetValue() {
        return bytes;
    }
}
