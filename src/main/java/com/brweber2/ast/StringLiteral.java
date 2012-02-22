package com.brweber2.ast;

import com.brweber2.lex.StringToken;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class StringLiteral {
    private String str;

    public StringLiteral(StringToken token)
    {
        this.str = token.str;
    }
    
    public StringLiteral(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    @Override
    public String toString() {
        return '"' + str + '"';
    }
}
