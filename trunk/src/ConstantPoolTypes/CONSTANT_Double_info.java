/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConstantPoolTypes;

/**
 *
 * @author Zachy
 */
public class CONSTANT_Double_info extends ConstantPoolElem {

    int high_bites;
    int low_bites;
    double value;

    public CONSTANT_Double_info(int high_bites, int low_bites) {
        super(TagStatics.CONSTANT_Double);
        this.high_bites = high_bites;
        this.low_bites = low_bites;
        long v = ((long) high_bites << 32) + low_bites;
        calcVal(v);
    }

    private void calcVal(long v) {
        if (v == 0x7ff0000000000000L) {
            value = Double.POSITIVE_INFINITY;
        } else if (v == 0xfff0000000000000L) {
            value = Double.NEGATIVE_INFINITY;
        } else if ((v >= 0x7ff0000000000001L && v <= 0x7fffffffffffffffL)
                || (v >= 0xfff0000000000001L && v <= 0xffffffffffffffffL)) {
            value = Double.NaN;
        } else {
            int s = ((v >> 63) == 0) ? 1 : -1;
            int e = (int) ((v >> 52) & 0x7ffL);
            long m = (e == 0)
                    ? (v & 0xfffffffffffffL) << 1
                    : (v & 0xfffffffffffffL) | 0x10000000000000L;
            value = s * m * Math.pow(2, e -1075);
        }
    }

    @Override
    public Object GetValue() {
        return value;
    }
}
