/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Descriptors;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;
import myjava.StaticLibrary;

/**
 *
 * @author Zachy
 */
public class MethodDescriptor {

    Class[] input;
    Class output;

    public MethodDescriptor(String s) {
        String[] arr = s.split("[)]");
        String istr = arr[0].substring(1);
        List<Class> classes = new LinkedList<>();
        for (int i = 0; i < istr.length(); i++) {
            Class c = ResolveString(istr.charAt(i));
            if (c != null) {
                classes.add(c);
            } else {
                for (int j = i; j < istr.length(); j++) {
                    if (istr.charAt(j) == ';') {
                        classes.add(StaticLibrary.LoadClass(istr.substring(i+1, j)));
                        i = j;
                        break;
                    }
                }

            }
        }
        input = classes.toArray(new Class[0]);
    }

    public Class[] getInput() {
        return input;
    }

    public Class getOutput() {
        throw new UnsupportedOperationException();
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
        if (s == '[') {
            return Array.class;
        }
        return null;
    }
}
