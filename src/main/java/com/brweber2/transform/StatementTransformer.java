package com.brweber2.transform;

import com.brweber2.run.Call;
import com.brweber2.ast.Statement;

import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public interface StatementTransformer {
    List<Call> transform(Statement statement);
}
