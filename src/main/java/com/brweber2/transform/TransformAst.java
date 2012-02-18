package com.brweber2.transform;

import com.brweber2.Call;
import com.brweber2.ast.Statement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class TransformAst {

    private static TransformAst transformAst;

    public static TransformAst getInstance()
    {
        if ( transformAst == null )
        {
            transformAst = new TransformAst();
        }
        return transformAst;
    }
    
    private Map<String,StatementTransformer> transformers = new HashMap<String,StatementTransformer>();
    
    public List<Call> transform( List<Statement> statements )
    {
        List<Call> calls = new ArrayList<Call>();
        for (Statement statement : statements) {
            calls.add( transformStatement( statement ) );
        }
        return calls;
    }
    
    public void registerTranformer( StatementTransformer transformer )
    {
        if ( transformers.containsKey( transformer.getName() ) )
        {
            System.out.println("replacing transformer for " + transformer.getName());
        }
        transformers.put( transformer.getName(), transformer );
    }

    public void removeTransformer( StatementTransformer transformer )
    {
        transformers.remove( transformer.getName() );
    }

    private Call transformStatement(Statement statement) {
        String name = statement.getName();
        if ( !transformers.containsKey(name))
        {
            throw new RuntimeException("Invalid statement: " + statement);
        }
        return transformers.get(name).transform(statement);
    }

}
