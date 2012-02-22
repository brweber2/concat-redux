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

    public NumberLiteral(List<LexToken> numberTokens) {
        StringBuilder str = new StringBuilder();
        for (LexToken numberToken : numberTokens) {
            if ( numberToken == Token.DOT )
            {
                str.append(".");
            }
            else if ( numberToken == Token.ZERO )
            {
                str.append("0");
            }
            else if ( numberToken == Token.ONE )
            {
                str.append("1");
            }
            else if ( numberToken == Token.TWO )
            {
                str.append("2");
            }
            else if ( numberToken == Token.THREE )
            {
                str.append("3");
            }
            else if ( numberToken == Token.FOUR )
            {
                str.append("4");
            }
            else if ( numberToken == Token.FIVE )
            {
                str.append("5");
            }
            else if ( numberToken == Token.SIX )
            {
                str.append("6");
            }
            else if ( numberToken == Token.SEVEN )
            {
                str.append("7");
            }
            else if ( numberToken == Token.EIGHT )
            {
                str.append("8");
            }
            else if ( numberToken == Token.NINE )
            {
                str.append("9");
            }
        }
        String n = str.toString();
        if ( n.contains("." ) )
        {
            nbr = new BigDecimal(n);
        }
        else
        {
            nbr = new BigInteger(n);
        }
    }

    public Number getNbr() {
        return nbr;
    }
}
