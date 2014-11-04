/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Heap;

import Attributes.LocalVariableTableAttribute;
import java.util.HashMap;
import java.util.LinkedList;
import Parser.Stack;

/**
 *
 * @author stopka
 */
public class Heap {
    final int GC_CONST=100;
    
    HashMap<Integer, Object> heap;
    int next_index = 0;
    int last_gc=0;

    public Heap() {
        heap = new HashMap();
        fields = new LinkedList();
        variables = new LinkedList();
        stacks = new LinkedList();
    }

    public HeapRef store(Object o) {
        runGC();
        int i = next_index;
        next_index++;
        heap.put(i, o);
        return new HeapRef(this, i);
    }

    public Object load(int i) {
        return heap.get(i);
    }

    public Object load(HeapRef ref) {
        return heap.get(ref.getIndex());
    }

    static Heap singleton;

    public static Heap getHeap() {
        if (singleton == null) {
            singleton = new Heap();
        }
        return singleton;
    }

    private boolean isRefered(int index) {
        return isReferedInFields(index) || 
                isReferedInLocalVariables(index) ||
                isReferedInStacks(index);
    }

    private boolean isReferedInFields(int index) {
        for (ConstantPoolTypes.CONSTANT_Fieldref_info f : fields) {
            if (isReferenceTo(f.GetValue(), index)) {
                return true;
            }
        }
        return false;
    }

    private boolean isReferedInLocalVariables(int index) {
        for (LocalVariableTableAttribute.LocalVariable lv : variables) {
            if (isReferenceTo(lv.value, index)) {
                return true;
            }
        }
        return false;
    }

    private boolean isReferedInStacks(int index) {
        for (Stack s : stacks) {
            for (Object o : s) {
                if (isReferenceTo(o, index)) {
                    return true;
                }
            }
            for (Object o : s.getGuarded()) {
                if (isReferenceTo(o, index)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isReferenceTo(Object o, int index) {
        if (o instanceof HeapRef) {
            if (((HeapRef) o).getIndex() == index) {
                return true;
            }
        }
        return false;
    }
    
    public void runGC(){
        if(next_index-last_gc>GC_CONST){
            colectGarbage();
        }
    }

    private void colectGarbage() {
        for (int i = 0; i < next_index; i++) {
            if (heap.containsKey(i) && !isRefered(i)) {
                heap.remove(i);
            }
        }
    }

    private LinkedList<ConstantPoolTypes.CONSTANT_Fieldref_info> fields;

    public void registerField(ConstantPoolTypes.CONSTANT_Fieldref_info field) {
        fields.add(field);
    }

    private LinkedList<Stack> stacks;

    public void registerStack(Stack stack) {
        stacks.add(stack);
    }

    private LinkedList<LocalVariableTableAttribute.LocalVariable> variables;

    public void registerLocalVariable(LocalVariableTableAttribute.LocalVariable lv) {
        variables.add(lv);
    }

}
