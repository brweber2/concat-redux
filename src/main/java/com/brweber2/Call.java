package com.brweber2;

import com.brweber2.ast.StackEffect;

import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public interface Call {
    public void invoke(Stack stack);
    public StackEffect getStackEffect();
    public Instructions getInstructions();
}
