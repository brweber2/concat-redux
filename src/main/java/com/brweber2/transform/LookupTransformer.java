package com.brweber2.transform;

import com.brweber2.Call;
import com.brweber2.ast.Statement;
import com.brweber2.call.WordCall;
import com.brweber2.vocab.Vocabulary;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class LookupTransformer implements StatementTransformer {
    @Override
    public Call transform(Statement statement) {
        return new WordCall(statement.getName());
//        return Vocabulary.getCurrent().findWord(statement.getName());
    }
}
