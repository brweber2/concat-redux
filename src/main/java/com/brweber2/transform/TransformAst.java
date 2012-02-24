package com.brweber2.transform;

import com.brweber2.ast.Symbol;
import com.brweber2.ast.Var;
import com.brweber2.call.literal.BlockLiteral;
import com.brweber2.call.word.WordCall;
import com.brweber2.call.literal.IdentityCall;
import com.brweber2.run.Call;
import com.brweber2.ast.Block;
import com.brweber2.ast.Items;
import com.brweber2.ast.NumberLiteral;
import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Statement;
import com.brweber2.ast.StringLiteral;
import com.brweber2.type.JavaType;
import com.brweber2.type.StaticTypeChecker;
import com.brweber2.vocab.Vocabulary;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class TransformAst {

    private static final DefineTransformer define = new DefineTransformer();
    private static final LookupTransformer lookup = new LookupTransformer();
    private static final DoTransformer doCall = new DoTransformer();
    private static final LiteralTransformer literal = new LiteralTransformer();

    private static final StaticFieldTransformer staticField = new StaticFieldTransformer();
    private static final StaticMethodTransformer staticMethod = new StaticMethodTransformer();
    private static final ConstructorTransformer constructor = new ConstructorTransformer();
    private static final InstanceFieldTransformer instanceField = new InstanceFieldTransformer();
    private static final InstanceMethodTransformer instanceMethod = new InstanceMethodTransformer();

    
    public static List<Call> transform( List<Statement> statements )
    {
        TransformAst transformAst = new TransformAst();
        List<Call> calls = new ArrayList<Call>();
        for (Statement statement : statements) {
            calls.addAll(transformAst.transformStatement(statement));
        }
        return calls;
    }

    private List<Call> transformStatement(Statement statement) {
        return getStatementTransformer(statement).transform(statement);
    }

    /**
     * define
     * lookup
     * set
     * get
     * native
     *   static field
     *   static method call
     *   constructor call
     *   instance field
     *   instance method call
     *
     * @param statement The statement that will be transformed to a call.
     * @return The appropriate class for transforming this statement to a call.
     */
    private StatementTransformer getStatementTransformer(Statement statement) {
        String name = statement.getName();
        if ( name == null )
        {
            return literal;
        }
        if ( "define".equals(name))
        {
            return define;
        }
        else if ("do".equals(name) )
        {
            return doCall;
        }
        else if ( "static-field".equals(name) )
        {
            return staticField;
        }
        else if ( "staticMethod".equals(name) )
        {
            return staticMethod;
        }
        else if ( "new".equals(name) )
        {
            return constructor;
        }
        else if ( "instanceField".equals(name) )
        {
            return instanceField;
        }
        else if ( "instanceMethod".equals(name) )
        {
            return instanceMethod;
        }
        else 
        {
            return lookup;
        }
    }

    public static Call transformPiece(Object o) {
        if ( o instanceof NumberLiteral )
        {
            return new com.brweber2.call.literal.NumberLiteral(((NumberLiteral)o).getNbr());
        }
        else if ( o instanceof StringLiteral )
        {
            return new com.brweber2.call.literal.StringLiteral(((StringLiteral)o).getStr());
        }
        else if ( o instanceof Block)
        {
            return new BlockLiteral((Block)o);
        }
        else if ( o instanceof Symbol)
        {
            Symbol s = (Symbol)o;
            if (Vocabulary.getCurrent().findWord(s.symbol) != null || Vocabulary.isKeyword(s.symbol))
            {
                return new WordCall(s.symbol);
            }
            return new IdentityCall(new JavaType(Symbol.class),s);
        }
        else if ( o instanceof Var)
        {
            return new IdentityCall(new JavaType(Var.class),o);
        }
        else if ( o instanceof Items )
        {
            return new IdentityCall(new JavaType(Items.class),o);
        }
        else if ( o instanceof StackEffect )
        {
            return new IdentityCall(new JavaType(StackEffect.class),o);
        }
        else
        {
            throw new RuntimeException("Unknown ast type " + o );
        }
    }
}
