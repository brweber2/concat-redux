package com.brweber2.lex;

import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Lexer {
    
    public static int READ_AHEAD = 1;
    
    LineNumberReader reader;

    
    public Lexer(LineNumberReader reader) {
        if ( !reader.markSupported() )
        {
            throw new RuntimeException("This lexer requires a reader that supports marks.");
        }
        this.reader = reader;
    }

    public TokenStream lex()
    {
        return new TokenStream(read());
    }
    
    public List<LexToken> read()
    {
        try {
            List<LexToken> tokens = new ArrayList<LexToken>();
            LexToken next = null;
            while ( next != Token.EOF )
            {
                next = next();
                tokens.add( next );
            }
            return tokens;
        } catch (IOException e) {
            throw new RuntimeException("Unable to read tokens.", e );
        }
    }

    // colon :
    // symbol
    // lazy eval { }
    // list [ a, b, c ]
    // stack effect def ( ? -> ? )
    // done dot .
    // named var @name
    public LexToken next() throws IOException {
        // remove any leading whitespace
        if ( whitespaceNext() )
        {
            stripWhitespace();
        }
        // read the next token
        if ( colonNext() )
        {
            return readColon();
        }
        else if ( commaNext() )
        {
            return readComma();
        }
        else if ( lazyEvalNext() )
        {
            return readLazyEval();
        }
        else if ( lazyEvalEndNext() )
        {
            return readLazyEvalEnd();
        }
        else if ( listNext() )
        {
            return readList();
        }
        else if ( listEndNext() )
        {
            return readListEnd();
        }
        else if ( stackEffectNext() )
        {
            return readStackEffect();
        }
        else if ( arrowNext() )
        {
            return readArrow();
        }
        else if ( stackEffectEndNext() )
        {
            return readStackEffectEnd();
        }
        else if ( dotNext() )
        {
            return readDot();
        }
        else if ( namedVarNext() )
        {
            return readNamedVar();
        }
        else if ( eofNext() )
        {
            return Token.EOF;
        }
        else
        {
            return readSymbol();
        }
    }

    private LexToken readSymbol() throws IOException {
        if ( eofNext() )
        {
            throw new RuntimeException("Encountered unexpected EOF while reading symbol at " + reader.getLineNumber() + ".");
        }
        List<Integer> symbol = new ArrayList<Integer>();
        reader.mark(READ_AHEAD);
        int c = reader.read();
        while ( isSymbolChar(c) )
        {
            symbol.add(c);
            if ( eofNext() )
            {
                break;
            }
            reader.mark(READ_AHEAD);
            c = reader.read();
        }
        reader.reset();
        return new Symbol(symbol,reader.getLineNumber());
    }

    private boolean isSymbolChar(int c) {
        return (c > 32 && c < 44) || (c > 44 && c < 93)|| (c > 93 && c < 127);
                
//                (c >= 46 && c <= 57) ||  // . / or number
//                (c >=65 && c <= 90) ||   // upper case letter
//                (c >= 97 && c <= 122);   // lower case letter
    }

    private LexToken readNamedVar() throws IOException {
        reader.read(); // discard the '@'
        return new Var( (Symbol) readSymbol(), reader.getLineNumber() );
    }

    private LexToken readArrow() throws IOException {
        reader.read();
        reader.read();
        return Token.ARROW.setLineNumber(reader.getLineNumber());
    }

    private LexToken readStackEffectEnd() throws IOException {
        reader.read();
        return Token.PAREN_CLOSE.setLineNumber(reader.getLineNumber());
    }

    private LexToken readStackEffect() throws IOException {
        reader.read();
        return Token.PAREN_OPEN.setLineNumber(reader.getLineNumber());
    }

    private LexToken readListEnd() throws IOException {
        reader.read();
        return Token.BRACKET_CLOSE.setLineNumber(reader.getLineNumber());
    }

    private LexToken readList() throws IOException {
        reader.read();
        return Token.BRACKET_OPEN.setLineNumber(reader.getLineNumber());
    }

    private LexToken readLazyEvalEnd() throws IOException {
        reader.read();
        return Token.BRACE_CLOSE.setLineNumber(reader.getLineNumber());
    }

    private LexToken readLazyEval() throws IOException {
        reader.read();
        return Token.BRACE_OPEN.setLineNumber(reader.getLineNumber());
    }

    private LexToken readComma() throws IOException {
        reader.read();
        return Token.COMMA.setLineNumber(reader.getLineNumber());
    }

    private LexToken readDot() throws IOException {
        reader.read();
        return Token.DOT.setLineNumber(reader.getLineNumber());
    }

    private LexToken readColon() throws IOException {
        reader.read();
        return Token.COLON.setLineNumber(reader.getLineNumber());
    }

    private void stripWhitespace() throws IOException {
        while ( whitespaceNext() )
        {
            reader.read(); // just discard it
        }
    }

    private boolean arrowNext() throws IOException {
        boolean dashNext = dashNext();
        if ( !dashNext )
        {
            return false;
        }
        reader.mark(2);
        int dash = reader.read();
        int i = reader.read();
        reader.reset();
        return i == '>';
    }

    private boolean dashNext() throws IOException {
        return intNext('-');
    }

    private boolean commaNext() throws IOException {
        return intNext(',');
    }

    private boolean namedVarNext() throws IOException {
        return intNext('@');
    }

    private boolean dotNext() throws IOException {
        return intNext('.');
    }

    private boolean stackEffectNext() throws IOException {
        return intNext('(');
    }

    private boolean stackEffectEndNext() throws IOException {
        return intNext(')');
    }

    private boolean listNext() throws IOException {
        return intNext('[');
    }

    private boolean listEndNext() throws IOException {
        return intNext(']');
    }

    private boolean lazyEvalNext() throws IOException {
        return intNext('{');
    }

    private boolean lazyEvalEndNext() throws IOException {
        return intNext('}');
    }

    private boolean colonNext() throws IOException {
        return intNext(':');
    }

    private boolean eofNext() throws IOException {
        return intNext( -1 );
    }
    
    private boolean intNext( int i ) throws IOException {
        reader.mark(READ_AHEAD);
        int r = reader.read();
        reader.reset();
        return r == i;
    }

    private boolean whitespaceNext() throws IOException {
        reader.mark(READ_AHEAD);
        int r = reader.read();
        reader.reset();
        return Character.isWhitespace(r);
    }

}
