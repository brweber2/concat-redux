package com.brweber2.call.controlflow;

import com.brweber2.ast.Block;
import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Symbol;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.type.CheckedType;
import com.brweber2.type.StaticTypeChecker;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class DoCall implements Call {
    
    public Block block;

    public DoCall(Block block) {
        this.block = block;
    }

    @Override
    public void invoke(Stack stack) {
        if ( block == null )
        {
            block = (Block) stack.pop().object;
        }
        else
        {
            stack.pop();
        }
        for (Call call : block.getInstructions()) {
            call.invoke(stack);
        }
    }

    @Override
    public StackEffect getStackEffect() {
        StackEffect stackEffect = new StackEffect();
        stackEffect.add(new Symbol(Block.class.getName()));
        stackEffect.addArrow();
        List<Symbol> outs = getOuts(block);
        for (Symbol s : outs) {
            stackEffect.add(s);
        }
        return stackEffect;
    }

    private List<Symbol> getOuts(Block block) {
        List<CheckedType> typeStack = new ArrayList<CheckedType>();
        if ( block != null )
        {
            for (Call call : block.getInstructions()) {
                StaticTypeChecker.checkCall(typeStack,call);
            }
        }
        List<Symbol> symbols = new ArrayList<Symbol>();
        for (CheckedType checkedType : typeStack) {
            symbols.add(checkedType.toSymbol());
        }
        return symbols;
    }
}
