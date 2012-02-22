package com.brweber2.ast;

import com.brweber2.lex.SymbolToken;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Symbol {

    public final String symbol;

    public Symbol(String symbol) {
        this.symbol = symbol;
    }

    public Symbol(SymbolToken token) {
        this.symbol = token.symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
