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
public class CONSTANT_Methodref_info extends ConstantPoolElem{
    private int classIndex;
    private int nameAndTypeIndex;
    
    public CONSTANT_Methodref_info(int classIndex, int nameAndTymeIndex){
        super(TagStatics.CONSTANT_Methodref);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTymeIndex;
    }

    @Override
    public String toString() {
        return pool[nameAndTypeIndex-1] + "";
    }
    public ConstantPoolElem getName(){
        return pool[nameAndTypeIndex-1];
    }
    public String getClassName(){
        return pool[classIndex-1] + "";
    }

    @Override
    public Object GetValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
