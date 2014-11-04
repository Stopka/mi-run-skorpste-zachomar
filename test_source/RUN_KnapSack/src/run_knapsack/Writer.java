/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run_knapsack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author stopka
 */
public class Writer {

    FileOutputStream fileOutput;

    public Writer(String file_name) {
        try {
            fileOutput = new FileOutputStream(file_name);
        } catch (FileNotFoundException ex) {

        }
    }

    public void writeInt(int num) {
        try {
            fileOutput.write(String.valueOf(num).getBytes());
        } catch (IOException ex) {

        }
    }

    public void writeChar(char ch) {
        try {
            fileOutput.write((byte) ch);
        } catch (IOException ex) {
        }
    }
}
