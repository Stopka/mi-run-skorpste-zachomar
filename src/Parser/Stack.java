/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Parser;

/**
 *
 * @author stopka
 */
public class Stack extends java.util.Stack<Object>{

    public Stack() {
        super();
        Heap.Heap.getHeap().registerStack(this);
    }
    
    
    Object[] last_popped=new Object[5];
    public Object pop() {
        for(int i=1;i<last_popped.length;i++){
            last_popped[i-1]=last_popped[i];
        }
        last_popped[last_popped.length-1]=super.pop();
        return last_popped[last_popped.length-1];
    }
    
    public Object[] getGuarded(){
        return last_popped;
    }
    
}
