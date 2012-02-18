package com.brweber2.transform;

import com.brweber2.Call;
import com.brweber2.ast.Block;
import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Statement;
import com.brweber2.call.UserDefinedCall;
import com.brweber2.lex.Symbol;

import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class DefineTransformer implements StatementTransformer {
    static
    {
        TransformAst.getInstance().registerTranformer(new DefineTransformer());
    }

    @Override
    public String getName() {
        return "define";
    }

    @Override
    public Call transform(Statement statement) {
        // the stack should contain:
        // name stack-effect block define
        List pieces = statement.getPieces();
        if ( pieces.size() != 4 )
        {
            throw new RuntimeException("A define statement must take the form of: name stack-effect block define");
        }
        Object nameObject = pieces.get(0);
        if ( !(nameObject instanceof Symbol))
        {
            throw new RuntimeException("The name of a define word must be a symbol, not a " + nameObject);
        }
        Symbol name = (Symbol) nameObject;

        Object stackEffectObject = pieces.get(0);
        if ( !(stackEffectObject instanceof StackEffect))
        {
            throw new RuntimeException("The stack effect of a define word must be a stack effect, not a " + stackEffectObject);
        }
        StackEffect stackEffect = (StackEffect) stackEffectObject;

        Object blockObject = pieces.get(0);
        if ( !(blockObject instanceof Block))
        {
            throw new RuntimeException("The block of a define word must be a block, not a " + blockObject);
        }
        Block block = (Block) blockObject;

        return new UserDefinedCall(name, stackEffect, block );
    }
}
