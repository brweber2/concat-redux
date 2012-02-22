package com.brweber2.ast;

import com.brweber2.lex.VarToken;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Var {
    
    public String var;
    
    public Var( VarToken token)
    {
        this.var = token.var;
    }

    @Override
    public String toString() {
        return "@" + var;
    }
}
