package com.brweber2.call.controlflow;

import com.brweber2.ast.Block;
import com.brweber2.ast.StackEffect;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.type.BlockType;
import com.brweber2.type.CheckedType;
import com.brweber2.type.TypeStack;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class DoBlock implements Call {
    @Override
    public void invoke(Stack stack) {
        Block block = (Block) stack.pop().object;
        for (Call call : block.getInstructions()) {
            call.invoke(stack);
        }
    }

    @Override
    public StackEffect getStackEffect(TypeStack typeStack) {
        BlockType blockType = (BlockType) typeStack.peek(-1);
        StackEffect stackEffect = new StackEffect();
        stackEffect.add(blockType);
        stackEffect.addArrow();
        for (CheckedType out : blockType.getStackEffect().getOutputTypes() ) {
            stackEffect.add(out);
        }
        return stackEffect;
    }
}
