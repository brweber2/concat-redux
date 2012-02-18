package com.brweber2.transform;

import com.brweber2.Call;
import com.brweber2.ast.Statement;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public interface StatementTransformer {
    String getName();
    Call transform(Statement statement);
}
