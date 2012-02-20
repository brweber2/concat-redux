package com.brweber2;

import com.brweber2.lex.Symbol;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public interface CheckedType {

    boolean ok( CheckedType type );
    Symbol toSymbol();
}
