/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myjava;

import Parser.Parser;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Zachy
 */
public class MyJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Parser.Initialize(args[0]);
        Parser i = Parser.instance;
        if (i == null) {
            return;
        }
        i.Read();
        i.ReadOtherClasses(args);
        i.Run();
    }
}
