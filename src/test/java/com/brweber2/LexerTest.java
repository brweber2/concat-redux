package com.brweber2;

import com.brweber2.lex.LexToken;
import com.brweber2.lex.Lexer;
import com.brweber2.lex.Symbol;
import com.brweber2.lex.Token;
import com.brweber2.lex.Var;
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
                new Symbol("vocab"),
                new Symbol("math"),
                Token.BRACE_OPEN,
                new Symbol("from"),
                new Symbol("kernel"),
                Token.COLON,
                new Symbol("use"),
                Token.BRACKET_OPEN,
                new Symbol("main"),
                Token.COMMA,
                new Symbol("println"),
                Token.COMMA,
                new Symbol("exit"),
                Token.BRACKET_CLOSE,
                Token.DOT,
                Token.COLON,
                new Symbol("define"),
                new Symbol("add"),
                Token.PAREN_OPEN,
                new Symbol("Number"),
                new Symbol("Number"),
                Token.ARROW,
                new Symbol("Number"),
                Token.PAREN_CLOSE,
                Token.BRACE_OPEN,
                new Symbol("+"),
                Token.BRACE_CLOSE,
                Token.DOT,
                Token.COLON,
                new Symbol("define"),
                new Symbol("subtract"),
                Token.PAREN_OPEN,
                new Var("a"),
                new Symbol("Number"),
                new Var("b"),
                new Symbol("Number"),
                Token.ARROW,
                new Symbol("Number"),
                Token.PAREN_CLOSE,
                Token.BRACE_OPEN,
                new Var("a"),
                new Var("b"),
                new Symbol("-"),
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
