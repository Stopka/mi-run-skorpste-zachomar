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
        clazz = StaticLibrary.LoadClass(className.substring(1, className.length() - 1));
        if (clazz.getName() == "java.io.PrintStream") {
            clazz = System.class;
        }
    }
}
