package com.brweber2.lex;

import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Symbol implements LexToken {
    
    public final String symbol;
    
    public Symbol(String symbol)
    {
        this.symbol = symbol;
    }
    
    public Symbol(List<Integer> symbol) {
        StringBuilder str = new StringBuilder();
        for (int i : symbol) {
            str.append((char)i);
        }
        this.symbol = str.toString();
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
}
