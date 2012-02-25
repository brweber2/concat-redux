package com.brweber2.type;

import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Symbol;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class StackEffectType implements CheckedType {

    private StackEffect stackEffect;

    public StackEffectType(StackEffect stackEffect) {
        this.stackEffect = stackEffect;
    }

    @Override
    public boolean ok(CheckedType type) {
        if ( type instanceof StackEffectType )
        {
            StackEffectType other = (StackEffectType) type;
            return StaticTypeChecker.checkStackEffects( stackEffect, other.getStackEffect() );
        }
        else if ( type instanceof JavaType )
        {
            JavaType javaType = (JavaType) type;
            return javaType.ok(new JavaType(stackEffect.getClass()));
        }
        return false;
    }

    @Override
    public Symbol toSymbol() {
        return new Symbol(StackEffectType.class.getName());
    }

    public StackEffect getStackEffect()
    {
        return stackEffect;
    }
}
