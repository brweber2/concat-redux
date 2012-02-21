package com.brweber2.transform;

import com.brweber2.run.Call;
import com.brweber2.ast.Block;
import com.brweber2.ast.Items;
import com.brweber2.ast.NumberLiteral;
import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Statement;
import com.brweber2.ast.StringLiteral;
import com.brweber2.call.IdentityCall;
import com.brweber2.call.Literal;
import com.brweber2.lex.Symbol;
import com.brweber2.lex.Var;
import com.brweber2.type.JavaType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class TransformAst {

    private static final DefineTransformer define = new DefineTransformer();
    private static final LookupTransformer lookup = new LookupTransformer();

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
    
    private Statement replaceLiterals(Statement statement)
    {
        Statement mod = new Statement();
        for (Object o : statement.getPieces()) 
        {
            if ( o instanceof NumberLiteral )
            {
                Number n = ((NumberLiteral)o).getNbr();
                mod.add( new Literal<Number>(n,n.getClass()));
            }
            else if ( o instanceof StringLiteral )
            {
                String s= ((StringLiteral)o).getStr();
                mod.add( new Literal<String>(s,String.class));
            }
            else
            {
                mod.add(o);
            }
        }
        return mod;
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
        if ( "define".equals(name))
        {
            return define;
        }
        else if ( "staticField".equals(name) )
        {
            return staticField;
        }
        else if ( "staticMethod".equals(name) )
        {
            return staticMethod;
        }
        else if ( "constructor".equals(name) )
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

    public static Call transformArg(Object o) {
        if ( o instanceof NumberLiteral )
        {
            Number nbr = ((NumberLiteral)o).getNbr();
            return new Literal<Number>(nbr,nbr.getClass());
        }
        else if ( o instanceof StringLiteral )
        {
            return new Literal<String>(((StringLiteral)o).getStr(),String.class);
        }
        else if ( o instanceof Symbol )
        {
            return new IdentityCall(new JavaType(Symbol.class),o);
        }
        else if ( o instanceof Var)
        {
            return new IdentityCall(new JavaType(Var.class),o);
        }
        else if ( o instanceof Block)
        {
            return new IdentityCall(new JavaType(Block.class),o);
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
