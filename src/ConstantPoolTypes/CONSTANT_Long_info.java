/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConstantPoolTypes;

/**
 *
 * @author Zachy
 */
public class CONSTANT_Long_info extends ConstantPoolElem {

    int high_bites;
    int low_bites;
    long value;

    public CONSTANT_Long_info(int high_bites, int low_bites) {
        super(TagStatics.CONSTANT_Long);
        this.high_bites = high_bites;
        this.low_bites = low_bites;
        value = (high_bites * 2 ^ 32) + low_bites;
    }

    @Override
    public Object GetValue() {
        return value;
    }
}
