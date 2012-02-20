package com.brweber2.transform;

import com.brweber2.Call;
import com.brweber2.ast.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class TransformAst {

    private static final DefineTransformer define = new DefineTransformer();
    private static final LookupTransformer lookup = new LookupTransformer();

    private static final GetTransformer get = new GetTransformer();
    private static final SetTransformer set = new SetTransformer();

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
            calls.add( transformAst.transformStatement(statement) );
        }
        return calls;
    }

    private Call transformStatement(Statement statement) {
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
        if ( "define".equals(name))
        {
            return define;
        }
        else if ( "get".equals(name) )
        {
            return get;
        }
        else if ( "set".equals(name) )
        {
            return set;
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

}
