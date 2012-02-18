package com.brweber2.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Block {
    
    private List<Statement> statements = new ArrayList<Statement>();
    
    public void add(Statement statement) {
        statements.add( statement );
    }

    public List<Statement> getStatements() {
        return statements;
    }
}
