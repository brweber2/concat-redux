package com.brweber2.lex;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class StringToken implements LexToken {
    
    public final String str;
    private int lineNumber;

    public StringToken(String str, int lineNumber) {
        this.str = str;
        this.lineNumber = lineNumber;
    }

    @Override
    public LexToken setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
        return this;
    }

    @Override
    public int getLineNumber() {
        return lineNumber;
    }
}
