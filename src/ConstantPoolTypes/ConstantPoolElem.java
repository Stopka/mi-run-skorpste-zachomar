/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ConstantPoolTypes;

import sun.reflect.ConstantPool;

/**
 *
 * @author Zachy
 */
public abstract class ConstantPoolElem {
    private byte id;
    protected ConstantPoolElem [] pool;
    public byte getId() {
        return id;
    }

    public ConstantPoolElem(byte id){
        this.id = id;
    }
    public abstract Object GetValue();
    public void AssignConstantPool(ConstantPoolElem[] pool){
        this.pool = pool;
    }
}
