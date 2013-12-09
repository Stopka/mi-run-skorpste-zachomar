/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Heap;

import myjava.StaticLibrary;

/**
 *
 * @author stopka
 */
public class InbuiltObject {
    Object instance;
    Class c;

    public InbuiltObject(Class c) {
        this.c=c;
    }
    
    
    
    public static InbuiltObject prepare(String class_name){
        Class c=StaticLibrary.LoadClass(class_name);
        if(c==null){
            return null;
        }
        return new InbuiltObject(c);
    }

    public InbuiltObject(Object o) {
        this.instance=o;
    }
    
    public Class getC(){
        return c;
    }
    
    public void setInstance(Object o){
        instance=o;
    }
    
    
    public Object getInstance(){
        return instance;
    }
    
    public String toString(){
        return c.getCanonicalName();
    }
    
}
