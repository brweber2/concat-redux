package com.brweber2.parse;

import com.brweber2.ast.Block;
import com.brweber2.ast.Items;
import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Statement;
import com.brweber2.lex.LexToken;
import com.brweber2.lex.Symbol;
import com.brweber2.lex.Token;
import com.brweber2.lex.TokenStream;
import com.brweber2.lex.Var;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Parser {

    public List<Statement> parse( TokenStream tokens )
    {
        return parseStatements( tokens );
    }

    public List<Statement> parseStatements( TokenStream tokens )
    {
        List<Statement> calls = new ArrayList<Statement>();
        LexToken token;
        while ( ( token = tokens.getNextToken() ) == Token.EOF )
        {
            calls.add(parseStatement(token, tokens));
        }
        return calls;
    }

    public Statement parseStatement( LexToken token, TokenStream tokens )
    {
        Statement statement = new Statement();
        // parse until a period
        // limit to one colon per statement???
        // symbol | var | colon | list | lazy | stack effect
        while ( token != Token.DOT )
        {
            if ( token == Token.EOF )
            {
                throw new RuntimeException("Unexpected EOF while reading a statement on line " + token.getLineNumber() );
            }
            if ( token instanceof Symbol )
            {
                statement.add( (Symbol) token );
            }
            else if ( token instanceof Var )
            {
                statement.add( (Var) token );
            }
            else if ( token == Token.COLON )
            {
                statement.addColon();
            }
            else
            {
                if ( token == Token.PAREN_OPEN )
                {
                    statement.add( parseStackEffect( token, tokens ) );
                }
                else if ( token == Token.BRACE_OPEN )
                {
                    statement.add( parseLazy( token, tokens ) );
                }
                else if ( token == Token.BRACKET_OPEN )
                {
                    statement.add( parseList( token, tokens ) );
                }
            }
            token = tokens.getNextToken();
        }
        return statement;
    }

    public Block parseLazy( LexToken token, TokenStream tokens )
    {
        // { ... }
        Block block = new Block();
        while ( token != Token.BRACE_CLOSE )
        {
            if ( token == Token.EOF )
            {
                throw new RuntimeException("Unexpected EOF while reading a block on line " + token.getLineNumber() );
            }
            block.add( parseStatement( token, tokens ) );
            token = tokens.getNextToken();
        }
        return block;
    }

    public Items parseList( LexToken token, TokenStream tokens )
    {
        // [ ... , ... ]
        Items items = new Items();
        while ( token != Token.BRACKET_CLOSE )
        {
            if ( token == Token.EOF )
            {
                throw new RuntimeException("Unexpected EOF while reading a items on line " + token.getLineNumber() );
            }
            if ( token instanceof Symbol )
            {
                items.add( (Symbol) token );
            }
            else if ( token instanceof Var )
            {
                items.add( (Var) token );
            }
            else
            {
                if ( token == Token.BRACE_OPEN )
                {
                    items.add( parseLazy( token, tokens ) );
                }
                else if ( token == Token.BRACKET_OPEN )
                {
                    items.add( parseList( token, tokens ) );
                }
            }
            token = tokens.getNextToken();
        }
        return items;
    }

    public StackEffect parseStackEffect( LexToken token, TokenStream tokens )
    {
        // ( ... -> ... )
        StackEffect stackEffect = new StackEffect();
        while ( token != Token.PAREN_CLOSE )
        {
            if ( token == Token.EOF )
            {
                throw new RuntimeException("Unexpected EOF while reading a stack effect on line " + token.getLineNumber() );
            }
            if ( token instanceof Symbol )
            {
                stackEffect.add( (Symbol) token );
            }
            else if ( token instanceof Var )
            {
                stackEffect.add( (Var) token );
            }
            else if ( token == Token.ARROW )
            {
                stackEffect.addArrow();
            }
            else
            {
                throw new RuntimeException("Unsupported token " + token + " in stack effect on line " + token.getLineNumber() );
            }
            token = tokens.getNextToken();
        }
        return stackEffect;
    }
}
