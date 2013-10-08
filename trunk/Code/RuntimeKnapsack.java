/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runtimeknapsack;



/**
 *
 * @author Zachy
 */
public class RuntimeKnapsack {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Integer nItems = new Integer(10);
        Integer rSelects = new Integer(5);
        Combination combo = new Combination(nItems, rSelects);
        Integer[] arr;
        arr = combo.getNextConfiguration();
    }
}
