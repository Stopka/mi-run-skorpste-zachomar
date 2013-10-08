/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package runtimeknapsack;

/**
 *
 * @author Zachy
 */
public class Combination {

    private Integer n, r;
    private Integer[] indexArr;//Array()
    private Boolean hasNext;

    public Combination(Integer n, Integer r) {
        this.n = n;
        this.r = r;
        indexArr = new Integer[r];/*TOTO BUDE new Array(r)*/
        for (Integer i = 0; i < r; i++) {
            indexArr[i] = i;
        }
        hasNext = Boolean.TRUE;
    }

    public Integer[] getNextConfiguration(){
        if(!hasNext) return null;
        Integer[] retVal = new Integer[n];
        for (Integer i = 0; i < n; i++) {
            retVal[i] = 0;
        }
        for (Integer j = 0; j < r; j++) {
            retVal[indexArr[j]] = 1;
        }
        moveIndex();
        return retVal;
    }
    

    private void moveIndex() {
        int i = rightmostIndexBelowMax();
        if (i >= 0) {
            indexArr[i] = indexArr[i] + 1;
            for (int j = i + 1; j < r; j++) {
                indexArr[j] = indexArr[j - 1] + 1;
            }
        } else {
            hasNext = false;
        }
    }

    private Integer rightmostIndexBelowMax() {
        for (Integer i = r - 1; i >= 0; i--) {
            if (indexArr[i] < n - r + i) {
                return i;
            }
        }
        return -1;
    }
}
