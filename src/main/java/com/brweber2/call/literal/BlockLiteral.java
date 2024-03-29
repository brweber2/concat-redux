package com.brweber2.call.literal;

import com.brweber2.ast.Block;
import com.brweber2.ast.StackEffect;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.type.BlockType;
import com.brweber2.type.CheckedType;
import com.brweber2.type.JavaType;
import com.brweber2.type.TypeStack;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class BlockLiteral implements Call {
    
    private Block block;
    private CheckedType type;

    public BlockLiteral(Block block) {
        this.block = block;
        this.type = new BlockType(block);
    }

    @Override
    public void invoke(Stack stack) {
        stack.push( type, block );
    }

    @Override
    public StackEffect getStackEffect(TypeStack typeStack) {
        StackEffect se = new StackEffect();
        se.addArrow();
        se.add( type );
        return se;
    }
}
