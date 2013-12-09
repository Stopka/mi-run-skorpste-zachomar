/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Instructions;

/**
 *
 * @author Zachy
 */
public abstract class ControlElem extends InstructionElem{

    public ControlElem(int pos) {
        super(pos);
    }
    public abstract int JumpTo();
    
    public abstract boolean isToJump();
    
    public boolean isFinal(){
        return false;
    }
}
