/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myjava;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zachy
 */
public class StaticLibrary {

    public static int ByteToInt(byte b) {
        return b & 0xFF;
    }

    public static Class LoadClass(String name) {
        name = name.replace('/', '.');
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException ex) {
        }
        return null;
    }
}
