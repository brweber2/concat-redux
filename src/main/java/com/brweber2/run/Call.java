package com.brweber2.run;

import com.brweber2.ast.StackEffect;
import com.brweber2.type.TypeStack;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public interface Call {
    public void invoke(Stack stack);
    public StackEffect getStackEffect(TypeStack typeStack);
}
