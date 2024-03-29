package com.brweber2.parse;

import com.brweber2.ast.Block;
import com.brweber2.ast.Items;
import com.brweber2.ast.NumberLiteral;
import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Statement;
import com.brweber2.ast.StringLiteral;
import com.brweber2.ast.Symbol;
import com.brweber2.ast.Var;
import com.brweber2.lex.LexToken;
import com.brweber2.lex.NumberToken;
import com.brweber2.lex.StringToken;
import com.brweber2.lex.SymbolToken;
import com.brweber2.lex.Token;
import com.brweber2.lex.TokenStream;
import com.brweber2.lex.VarToken;

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
        return parseStatement(token,tokens,Token.DOT);
    }

    

    public Statement parseStatement( LexToken token, TokenStream tokens, LexToken ... terminatingTokens )
    {
        Statement statement = new Statement();
        // parse until a period
        // limit to one colon per statement???
        // symbol | var | colon | list | lazy | stack effect
        while ( !terminates(token,terminatingTokens) )
        {
            if ( token == Token.EOF )
            {
                break;
            }
            if ( token instanceof SymbolToken)
            {
                statement.add( new Symbol( (SymbolToken) token ) );
            }
            else if ( token instanceof VarToken)
            {
                statement.add( new Var( (VarToken)token ) );
            }
            else if ( token == Token.COLON )
            {
                statement.addColon();
            }
            else if ( token instanceof StringToken )
            {
                statement.add( new StringLiteral( (StringToken) token ) );
            }
            else if ( token instanceof NumberToken)
            {
                statement.add( new NumberLiteral( (NumberToken) token ) );
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
        statement.setTerminatingToken(token);
        return statement;
    }

    private boolean terminates(LexToken token, LexToken[] terminatingTokens) {
        for (LexToken terminatingToken : terminatingTokens) {
            if ( terminatingToken == token )
            {
                return true;
            }
        }
        return false;
    }

    public Block parseLazy( LexToken token, TokenStream tokens )
    {
        // { ... }
        Block block = new Block();
        if ( token == Token.BRACE_OPEN )
        {
            token = tokens.getNextToken();
        }
        while ( true )
        {
            if ( token == Token.EOF )
            {
                throw new RuntimeException("Unexpected EOF while reading a block on line " + token.getLineNumber() );
            }
            Statement st = parseStatement( token, tokens, Token.DOT, Token.BRACE_CLOSE );
            block.add( st );
            if ( st.getTerminatingToken() == Token.BRACE_CLOSE )
            {
                break;
            }
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
        while ( true )
        {
            if ( token == Token.EOF )
            {
                throw new RuntimeException("Unexpected EOF while reading a items on line " + token.getLineNumber() );
            }
            Statement statement = parseStatement(token, tokens, Token.COMMA, Token.BRACKET_CLOSE );
            if ( statement.getPieces().size() > 1 )
            {
                items.add( statement.getPieces() );
            }
            else
            {
                items.add( statement.getPieces().get(0) );
            }
            if ( statement.getTerminatingToken() == Token.BRACKET_CLOSE )
            {
                break;
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
            if ( token instanceof SymbolToken)
            {
                stackEffect.add( new Symbol( (SymbolToken) token ) );
            }
            else if ( token instanceof VarToken)
            {
                stackEffect.add( new Var( (VarToken) token ) );
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
