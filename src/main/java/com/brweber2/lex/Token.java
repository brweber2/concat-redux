package com.brweber2.lex;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public enum Token implements LexToken {
    ARROW,
    COLON,
    COMMA,
    BRACE_OPEN,
    BRACE_CLOSE,
    BRACKET_OPEN,
    BRACKET_CLOSE,
    DOT,
    EOF,
    PAREN_OPEN,
    PAREN_CLOSE,
    DOUBLE_QUOTE,
    ZERO,
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE;

    private int lineNumber;
    
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
