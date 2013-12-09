/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Heap;

/**
 *
 * @author stopka
 */
public class HeapRef {
    int index;
    Heap heap;

    public HeapRef(Heap heap,int index) {
        this.heap=heap;
        this.index=index;
    }
    
    public int getIndex(){
        return index;
    }
    
    public String toString(){
        return "HeapRef "+index;
    }
    
    public Object get(){
        return heap.load(this);
    }
    
}
