package com.brweber2.lex;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class NumberToken implements LexToken {

    private int lineNumber;
    public final Number number;

    public NumberToken(int lineNumber, Number number) {
        this.lineNumber = lineNumber;
        this.number = number;
    }
    
    public NumberToken( List<Integer> beforeDot, List<Integer> afterDot, int lineNumber )
    {
        StringBuilder str = new StringBuilder();
        for (Integer integer : beforeDot) {
            str.append(integer);
        }
        if ( !afterDot.isEmpty() )
        {
            str.append(".");
            for (Integer integer : afterDot) {
                str.append(integer);
            }
            this.number = new BigDecimal(str.toString());
        }
        else
        {
            BigInteger bi = new BigInteger(str.toString());
            if ( isInt(bi) )
            {
                this.number = bi.intValue();
            }
            else if ( isLong(bi) )
            {
                this.number = bi.longValue();
            }
            else
            {
                this.number = new BigInteger(str.toString());
            }
        }
        this.lineNumber = lineNumber;
    }
    
    private boolean isInt( BigInteger bi )
    {
        return ( bi.compareTo(new BigInteger("" + Integer.MIN_VALUE)) >= 0
             && bi.compareTo(new BigInteger("" + Integer.MAX_VALUE)) <= 0 );
    }

    private boolean isLong( BigInteger bi )
    {
        return ( bi.compareTo(new BigInteger("" + Long.MIN_VALUE)) >= 0
                && bi.compareTo(new BigInteger("" + Long.MAX_VALUE)) <= 0 );
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
