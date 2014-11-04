/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run_knapsack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author stopka
 */
public class Reader {
    FileInputStream fileInput;
    static final char[] ints={'0','1','2','3','4','5','6','7','8','9'};

    public Reader(String file_name) {
        try {
            fileInput = new FileInputStream(file_name);
        } catch (FileNotFoundException ex) {

        }
    }

    public int readInt() {
        String result = "";
        try {
            int ch = fileInput.read();
            while (isInt(ch)) {
                result += (char) ch;
                ch = fileInput.read();
            }
        } catch (IOException e) {
        }
        return Integer.parseInt(result);
    }
    
    private boolean isInt(int ch){
        for(char i:ints){
            if(i==(char)ch){
                return true;
            }
        }
        return false;
    }

}
