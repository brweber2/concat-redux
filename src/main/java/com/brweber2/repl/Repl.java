package com.brweber2.repl;

import com.brweber2.ast.Statement;
import com.brweber2.bootstrap.Bootstrap;
import com.brweber2.lex.Lexer;
import com.brweber2.lex.TokenStream;
import com.brweber2.parse.Parser;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.transform.TransformAst;
import com.brweber2.type.StaticTypeChecker;
import jline.console.ConsoleReader;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Repl {
    public static void main(String[] args) throws IOException {
        Bootstrap.bootstrap();
        ConsoleReader reader = new ConsoleReader();
        reader.setPrompt(">>");
        Stack stack = new Stack();
        boolean done = false;
        while ( !done )
        {
            try {
                String read = read(reader);
                Object result = eval(read,stack);
                print(result);
                done = done(result);
            } catch (RuntimeException e) {
                e.printStackTrace();
                System.err.flush();
            }
            reader.flush();
        }
    }

    private static Object eval(String read, Stack stack) {
        if ( "exit".equals(read) || "quit".equals(read) )
        {
            return read;
        }
        if ( "clear".equals(read.trim()) )
        {
            stack.clear();
            return "";
        }
        LineNumberReader source = new LineNumberReader(new StringReader(read));
        TokenStream tokens = Lexer.lex(source);
        List<Statement> statements = Parser.parse(tokens);
        List<Call> calls = TransformAst.transform(statements);
        StaticTypeChecker.check(stack,calls);
        
        for (Call call : calls) {
            call.invoke(stack);
        }
        return stack;
    }

    private static String read(ConsoleReader reader) throws IOException {
        return reader.readLine(ConsoleReader.NULL_MASK);
    }

    private static void print(Object result) {
        System.out.println(result.toString());
    }

    private static boolean done(Object result) {
        if ( result instanceof String )
        {
            String sresult = (String) result;
            return ( "exit".equals(sresult) || "quit".equals(sresult) );
        }
        return false;
    }


}
