package com.brweber2.lex;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public interface LexToken {
    LexToken setLineNumber(int lineNumber);
    int getLineNumber();
}
