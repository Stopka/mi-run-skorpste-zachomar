/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConstantPoolTypes;

import Heap.InbuiltObject;
import Heap.Instance;
import Parser.Parser;
import myjava.StaticLibrary;

/**
 *
 * @author Zachy
 */
public class CONSTANT_Class extends ConstantPoolElem {

    private int nameIndex;

    public CONSTANT_Class(int nameIndex) {
        super(TagStatics.CONSTANT_Class);
        this.nameIndex = nameIndex;
    }

    @Override
    public String toString() {
        return pool[nameIndex - 1] + "";
    }

    @Override
    public Object GetValue() {
        InbuiltObject ibo=InbuiltObject.prepare(pool[nameIndex - 1].toString());
        if(ibo!=null){
            return ibo;
        }
        return new Instance(this);
    }
}
