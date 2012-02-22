package com.brweber2.ast;

import com.brweber2.lex.LexToken;
import com.brweber2.lex.NumberToken;
import com.brweber2.lex.Token;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class NumberLiteral {
    private Number nbr;

    public NumberLiteral(NumberToken token)
    {
        this.nbr = token.number;
    }
    
    public NumberLiteral(Number nbr) {
        this.nbr = nbr;
    }

    public Number getNbr() {
        return nbr;
    }

    @Override
    public String toString() {
        return nbr.toString();
    }
}
