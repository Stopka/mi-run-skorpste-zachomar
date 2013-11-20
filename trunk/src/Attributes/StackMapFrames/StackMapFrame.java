/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attributes.StackMapFrames;

import java.util.Queue;

/**
 *
 * @author Zachy
 */
public abstract class StackMapFrame {
    byte frameType;
    public StackMapFrame(byte b){
        frameType = b;
    }
}
