package com.brweber2.lex;

import java.util.Iterator;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class TokenStream implements Iterable<LexToken> {
    
    private List<LexToken> tokens;
    private Iterator<LexToken> iterator;

    public TokenStream(List<LexToken> tokens) {
        this.tokens = tokens;
        iterator = tokens.iterator();
    }

    public LexToken getNextToken()
    {
        if ( iterator.hasNext() )
        {
            return iterator.next();
        }
        return Token.EOF;
    }

    public void reset()
    {
        this.iterator = tokens.iterator();
    }

    @Override
    public Iterator<LexToken> iterator() {
        return iterator;
    }
}
