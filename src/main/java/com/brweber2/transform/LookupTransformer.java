package com.brweber2.transform;

import com.brweber2.Call;
import com.brweber2.ast.Statement;
import com.brweber2.call.WordCall;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class LookupTransformer implements StatementTransformer {
    @Override
    public Call transform(Statement statement) {
        return new WordCall(statement);
    }
}
