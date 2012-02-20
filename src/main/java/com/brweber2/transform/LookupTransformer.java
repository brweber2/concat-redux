package com.brweber2.transform;

import com.brweber2.run.Call;
import com.brweber2.ast.Statement;
import com.brweber2.call.WordCall;

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
        for (Object o : statement.getArgs()) {
            calls.add( TransformAst.transformArg(o) );
        }
        calls.add( new WordCall(statement) );
        return calls;
    }
}
