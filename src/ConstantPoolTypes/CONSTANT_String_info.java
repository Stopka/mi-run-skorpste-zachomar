/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConstantPoolTypes;

import Parser.Parser;

/**
 *
 * @author Zachy
 */
public class CONSTANT_String_info extends ConstantPoolElem{
    private int stringIndex;

    public CONSTANT_String_info(int stringIndex) {
        super(TagStatics.CONSTANT_String);
        this.stringIndex = stringIndex;
    }

    @Override
    public String toString() {
        return pool[stringIndex-1] + "";
    }

    @Override
    public Object GetValue() {
        return pool[stringIndex-1] + "";
    }
    
}
