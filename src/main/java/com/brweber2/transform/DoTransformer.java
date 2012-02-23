package com.brweber2.transform;

import com.brweber2.ast.Block;
import com.brweber2.ast.Statement;
import com.brweber2.call.literal.BlockLiteral;
import com.brweber2.call.DoCall;
import com.brweber2.run.Call;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class DoTransformer implements StatementTransformer {
    @Override
    public List<Call> transform(Statement statement) {
        List<Call> calls = new ArrayList<Call>();
        List pieces = statement.getArgs();
        if ( pieces.size() > 1 )
        {
            for ( int i = 0; i < pieces.size() -2; i++ ) {
                calls.add( TransformAst.transformPiece(pieces.get(i)));
            }
        }
        if ( pieces.isEmpty() )
        {
            calls.add(new DoCall(null));
            return calls;
        }
        Block block = (Block) pieces.get(pieces.size()-1);
        calls.add(new BlockLiteral(block));
        calls.add( new DoCall(block) );
        return calls;
    }
}
