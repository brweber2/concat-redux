package com.brweber2.transform;

import com.brweber2.run.Call;
import com.brweber2.ast.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class LookupTransformer implements StatementTransformer {
    @Override
    public List<Call> transform(Statement statement) {
        List<Call> calls = new ArrayList<Call>();
        for (Object o : statement.getPieces()) {
            calls.add( TransformAst.transformPiece(o) );
        }
        return calls;
    }
}
