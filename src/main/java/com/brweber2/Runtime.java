package com.brweber2;

import com.brweber2.ast.Statement;
import com.brweber2.lex.Lexer;
import com.brweber2.lex.TokenStream;
import com.brweber2.parse.Parser;
import com.brweber2.transform.TransformAst;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Runtime {
    
    public static void main(String[] args) throws FileNotFoundException {
        LineNumberReader source = new LineNumberReader(new FileReader(args[0]));
        TokenStream tokens = Lexer.lex(source);
        List<Statement> statements = Parser.parse(tokens);
        List<Call> calls = TransformAst.transform(statements);
        StaticTypeChecker.check(calls);
        Main.invoke(calls);
    }
}
