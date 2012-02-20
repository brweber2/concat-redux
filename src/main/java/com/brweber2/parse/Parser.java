package com.brweber2.parse;

import com.brweber2.ast.Block;
import com.brweber2.ast.Items;
import com.brweber2.ast.NumberLiteral;
import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Statement;
import com.brweber2.ast.StringLiteral;
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

    public static List<Statement> parse( TokenStream tokens )
    {
        return new Parser().parseStatements( tokens );
    }

    public List<Statement> parseStatements( TokenStream tokens )
    {
        List<Statement> calls = new ArrayList<Statement>();
        LexToken token;
        while ( ( token = tokens.getNextToken() ) != Token.EOF )
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
                statement.add((Var) token);
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
                else if ( token == Token.DOUBLE_QUOTE )
                {
                    statement.add( parseString( token, tokens ) );
                }
                else if ( isNumberToken(token) )
                {
                    statement.add( parseNumber( token, tokens ) );
                }
            }
            token = tokens.getNextToken();
        }
        return statement;
    }

    private StringLiteral parseString(LexToken token, TokenStream tokens) {
        StringBuilder str = new StringBuilder();
        if ( token == Token.DOUBLE_QUOTE )
        {
            token = tokens.getNextToken();
        }
        while ( token != Token.DOUBLE_QUOTE )
        {
            if ( token == Token.EOF )
            {
                throw new RuntimeException("Found EOF while reading a string.");
            }
            // todo this really limits the contents of strings....
            if ( token instanceof Symbol )
            {
                str.append(((Symbol)token).symbol);
            }
            token = tokens.getNextToken();
        }
        return new StringLiteral(str.toString());
    }

    private boolean isNumberToken(LexToken token)
    {
        return ( token == Token.ZERO ||
                token == Token.ONE ||
                token == Token.TWO ||
                token == Token.THREE ||
                token == Token.FOUR ||
                token == Token.FIVE ||
                token == Token.SIX ||
                token == Token.SEVEN ||
                token == Token.EIGHT ||
                token == Token.NINE );
    }

    private NumberLiteral parseNumber(LexToken token, TokenStream tokens) {
        boolean decimalFound = false;
        List<LexToken> numberTokens = new ArrayList<LexToken>();
        while ( isOkForNumberToken(decimalFound,token) )
        {
            if ( token == Token.EOF )
            {
                break;
            }
            if ( token == Token.DOT )
            {
                decimalFound = true;
            }
            numberTokens.add(token);
            token = tokens.getNextToken(); 
        }
        return new NumberLiteral(numberTokens);
    }

    private boolean isOkForNumberToken(boolean decimalAlreadyFound, LexToken token) {
        if ( decimalAlreadyFound )
        {
            return isNumberToken(token);
        }
        return token == Token.DOT || isNumberToken(token);
    }

    public Block parseLazy( LexToken token, TokenStream tokens )
    {
        // { ... }
        Block block = new Block();
        if ( token == Token.BRACE_OPEN )
        {
            token = tokens.getNextToken();
        }
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
        if ( token == Token.BRACKET_OPEN )
        {
            token = tokens.getNextToken();
        }
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
        if ( token == Token.PAREN_OPEN )
        {
            token = tokens.getNextToken();
        }
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
