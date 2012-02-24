package com.brweber2.transform;

import com.brweber2.ast.Statement;
import com.brweber2.run.Call;

import java.util.Arrays;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class LiteralTransformer implements StatementTransformer {
    @Override
    public List<Call> transform(Statement statement) {
        return Arrays.asList(TransformAst.transformPiece(statement.getPieces().get(0)));
    }
}
