package com.brweber2.lex;

import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Symbol implements LexToken {
    
    public final String symbol;
    private int lineNumber;

    public Symbol(String symbol)
    {
        this.symbol = symbol;
        this.lineNumber = -1;
    }

    public Symbol(String symbol, int lineNumber)
    {
        this.symbol = symbol;
        this.lineNumber = lineNumber;
    }
    
    public Symbol(List<Integer> symbol, int lineNumber) {
        StringBuilder str = new StringBuilder();
        for (int i : symbol) {
            str.append((char)i);
        }
        this.symbol = str.toString();
        this.lineNumber = lineNumber;
    }

    @Override
    public String toString() {
        return "Symbol{" +
                "symbol='" + symbol + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Symbol symbol1 = (Symbol) o;

        if (!symbol.equals(symbol1.symbol)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return symbol.hashCode();
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
