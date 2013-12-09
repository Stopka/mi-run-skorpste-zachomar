/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConstantPoolTypes;

import Attributes.CodeAttribute;
import Infos.MethodInfo;
import Parser.Parser;

/**
 *
 * @author Zachy
 */
public class CONSTANT_Fieldref_info extends ConstantPoolElem {

    private int classIndex;
    private int nameAndTypeIndex;
    private Object value;
    private boolean inited=false;

    public CONSTANT_Fieldref_info(int classIndex, int nameAndTymeIndex) {
        super(TagStatics.CONSTANT_Fieldref);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTymeIndex;
    }

    @Override
    public String toString() {
        String s = pool[nameAndTypeIndex - 1] + "";
        return s;
    }

    @Override
    public Object GetValue() {
        return pool[nameAndTypeIndex - 1];
    }
    
    public void storeValue(Object value){
        this.value=value;
        this.inited=true;
    }
    
    public Object loadValue(){
        if(!inited){
            String class_name=this.pool[classIndex-1].toString();
            MethodInfo info = Parser.instance.getMethodByName("<clinit>", class_name);
            if (info != null) {
                CodeAttribute attr = info.GetCodeAttribute(pool);
                attr.ExecuteCode();
            }
            inited=true;
        }
        return value;
    }
    
    public boolean isInited(){
        return inited;
    }
    
    public void setInited(){
        inited=true;
    }
}
