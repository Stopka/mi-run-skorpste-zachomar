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
public class CONSTANT_Fieldref_info extends ConstantPoolElem{
    private int classIndex;
    private int nameAndTypeIndex;
    
    public CONSTANT_Fieldref_info(int classIndex, int nameAndTymeIndex){
        super(TagStatics.CONSTANT_Fieldref);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTymeIndex;
    }

    @Override
    public String toString() {
        String s = pool[classIndex-1] + "";
        return s;
    }

    @Override
    public Object GetValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
