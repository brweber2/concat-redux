package com.brweber2;

import com.brweber2.lex.LexToken;
import com.brweber2.lex.Lexer;
import com.brweber2.lex.SymbolToken;
import com.brweber2.lex.Token;
import com.brweber2.lex.VarToken;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class LexerTest {

    @Test
    public void lex()
    {
        String src = ":vocab math {\n" +
                "  from kernel : use [main,println,exit].\n" +
                "\n" +
                "  :define add ( Number Number -> Number ) { + }.\n" +
                "  :define subtract ( @a Number @b Number -> Number ) { @a @b - }.\n" +
                "}.";
        
        List<LexToken> expecteds = Arrays.asList(                
                Token.COLON,
                new SymbolToken("vocab"),
                new SymbolToken("math"),
                Token.BRACE_OPEN,
                new SymbolToken("from"),
                new SymbolToken("kernel"),
                Token.COLON,
                new SymbolToken("use"),
                Token.BRACKET_OPEN,
                new SymbolToken("main"),
                Token.COMMA,
                new SymbolToken("println"),
                Token.COMMA,
                new SymbolToken("exit"),
                Token.BRACKET_CLOSE,
                Token.DOT,
                Token.COLON,
                new SymbolToken("define"),
                new SymbolToken("add"),
                Token.PAREN_OPEN,
                new SymbolToken("Number"),
                new SymbolToken("Number"),
                Token.ARROW,
                new SymbolToken("Number"),
                Token.PAREN_CLOSE,
                Token.BRACE_OPEN,
                new SymbolToken("+"),
                Token.BRACE_CLOSE,
                Token.DOT,
                Token.COLON,
                new SymbolToken("define"),
                new SymbolToken("subtract"),
                Token.PAREN_OPEN,
                new VarToken("a"),
                new SymbolToken("Number"),
                new VarToken("b"),
                new SymbolToken("Number"),
                Token.ARROW,
                new SymbolToken("Number"),
                Token.PAREN_CLOSE,
                Token.BRACE_OPEN,
                new VarToken("a"),
                new VarToken("b"),
                new SymbolToken("-"),
                Token.BRACE_CLOSE,
                Token.DOT,
                Token.BRACE_CLOSE,
                Token.DOT,
                Token.EOF
        );
        
        Lexer lexer = new Lexer(new LineNumberReader(new StringReader(src)));
        List<LexToken> tokens = lexer.read();
        
        Assert.assertNotNull(tokens);
        Assert.assertEquals(tokens.size(),expecteds.size());
        for ( int i = 0; i < tokens.size(); i++ )
        {
            LexToken t = tokens.get(i);
            LexToken e = expecteds.get(i);
            System.out.println("checking token " + i + " found " + t + " expected " + e);
            Assert.assertEquals(t,e);
        }
        Assert.assertEquals(tokens,expecteds);
    }
}
