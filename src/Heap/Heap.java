/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Heap;

import java.util.ArrayList;

/**
 *
 * @author stopka
 */
public class Heap {
    ArrayList<Object> heap;

    public Heap() {
        heap=new ArrayList<Object>();
    }
    
    public HeapRef store(Object o){
        int i=heap.size();
        heap.add(i, o);
        return new HeapRef(this, i);
    }
    
    public Object load(int i){
        return heap.get(i);
    }
    
    public Object load(HeapRef ref){
        return heap.get(ref.getIndex());
    }
    
    static Heap singleton;
    
    public static Heap getHeap(){
        if(singleton==null){
            singleton=new Heap();
        }
        return singleton;
    }
    
}
