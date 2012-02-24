package com.brweber2.type;

import com.brweber2.ast.Symbol;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public interface CheckedType {

    boolean ok( CheckedType type );
    Symbol toSymbol();
}
