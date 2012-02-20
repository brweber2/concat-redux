package com.brweber2.transform;

import com.brweber2.Call;
import com.brweber2.ast.Block;
import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Statement;
import com.brweber2.call.DefineCall;
import com.brweber2.lex.Symbol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class DefineTransformer implements StatementTransformer {

    @Override
    public List<Call> transform(Statement statement) {
        List<Call> calls = new ArrayList<Call>();
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

        Object stackEffectObject = pieces.get(1);
        if ( !(stackEffectObject instanceof StackEffect))
        {
            throw new RuntimeException("The stack effect of a define word must be a stack effect, not a " + stackEffectObject);
        }
        StackEffect stackEffect = (StackEffect) stackEffectObject;

        Object blockObject = pieces.get(2);
        if ( !(blockObject instanceof Block))
        {
            throw new RuntimeException("The block of a define word must be a block, not a " + blockObject);
        }
        Block block = (Block) blockObject;

        calls.add(TransformAst.transformArg(block));
        calls.add(TransformAst.transformArg(stackEffect));
        calls.add(TransformAst.transformArg(name));
        calls.add(new DefineCall(name, stackEffect, block) );
        return calls;
    }

}
