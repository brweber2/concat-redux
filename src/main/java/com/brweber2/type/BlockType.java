package com.brweber2.type;

import com.brweber2.ast.Block;
import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Symbol;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class BlockType implements CheckedType {
    
    private Block block;

    public BlockType(Block block) {
        this.block = block;
    }

    @Override
    public boolean ok(CheckedType type) {
        if ( type instanceof BlockType )
        {
            BlockType other = (BlockType) type;
            return StaticTypeChecker.checkStackEffects( block.getStackEffect(), other.block.getStackEffect() );
        }
        else if ( type instanceof JavaType )
        {
            JavaType javaType = (JavaType) type;
            return javaType.ok(new JavaType(block.getClass()));
        }
        return false;
    }

    @Override
    public Symbol toSymbol() {
        return new Symbol(BlockType.class.getName());
    }

    public StackEffect getStackEffect()
    {
        return block.getStackEffect();
    }
}
