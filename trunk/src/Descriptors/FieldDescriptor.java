/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Descriptors;

import ConstantPoolTypes.ConstantPoolElem;
import myjava.StaticLibrary;

/**
 *
 * @author Zachy
 */
public class FieldDescriptor {

    String field;
    Class clazz;

    public String getField() {
        return field;
    }

    public Class getClazz() {
        return clazz;
    }

    public FieldDescriptor(String className, String fieldN) {
        field = fieldN;
        try{
            clazz = StaticLibrary.LoadClass(className.substring(1, className.length() - 1));
        }catch(Exception e){}
    }
    
    private Class ResolveString(char s) {
        if (s == 'B') {
            return byte.class;
        }
        if (s == 'C') {
            return char.class;
        }
        if (s == 'D') {
            return double.class;
        }
        if (s == 'I') {
            return int.class;
        }
        if (s == 'F') {
            return float.class;
        }
        if (s == 'J') {
            return long.class;
        }
        if (s == 'S') {
            return short.class;
        }
        if (s == 'Z') {
            return boolean.class;
        }
        /*if (s == '[') {
            throw new UnsupportedOperationException("Pole jeste neumime");
        } */
        if (s == 'V') {
            return void.class;
        }
        return null;
    }
}
