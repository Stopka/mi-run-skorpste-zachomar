/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Attributes.StackMapFrames;

import java.util.Queue;
import myjava.StaticLibrary;

/**
 *
 * @author Zachy
 */
public class StackMapFrameGenerator {

    public static StackMapFrame createStackMapFrame(Queue<Byte> queue) {
        byte bajt = queue.poll();
        int b = StaticLibrary.ByteToInt(bajt);
        if (b < 64) {
            return new same_frame(bajt);
        } else if (b <= 64 && b < 128) {
            return null;
        }
        return null;
    }
}
