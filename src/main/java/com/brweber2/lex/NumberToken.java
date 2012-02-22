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
            this.number = new BigInteger(str.toString());
        }
        this.lineNumber = lineNumber;
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
