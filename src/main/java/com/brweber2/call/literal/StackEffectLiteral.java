package com.brweber2.call.literal;

import com.brweber2.ast.StackEffect;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.type.JavaType;
import com.brweber2.type.StackEffectType;
import com.brweber2.type.TypeStack;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class StackEffectLiteral implements Call {
    
    private StackEffect stackEffect;
    private StackEffectType type;

    public StackEffectLiteral(StackEffect stackEffect) {
        this.stackEffect = stackEffect;
        this.type = new StackEffectType(stackEffect);
    }

    @Override
    public void invoke(Stack stack) {
        stack.push(type,stackEffect);
    }

    @Override
    public StackEffect getStackEffect(TypeStack typeStack) {
        StackEffect stackEffect1 = new StackEffect();
        stackEffect1.addArrow();
        stackEffect1.add(type);
        return stackEffect1;
    }
}
