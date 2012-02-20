package com.brweber2.transform;

import com.brweber2.Call;
import com.brweber2.CheckedType;
import com.brweber2.ast.Statement;
import com.brweber2.call.Set;
import com.brweber2.lex.Var;
import com.brweber2.type.TypeSystem;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class SetTransformer implements StatementTransformer {
    @Override
    public Call transform(Statement statement) {
        // value @var type set
        if ( statement.getPieces().size() != 4 )
        {
            throw new RuntimeException("A set must be in the form 'value @var type set'.");
        }
        if ( !(statement.getPieces().get(1) instanceof Var) )
        {
            throw new RuntimeException("The var to set must be of type Var and not " + statement.getPieces().get(1) );
        }
        Object o = statement.getPieces().get(2);
        if ( !(o instanceof String))
        {
            throw new RuntimeException("The type must be specified as a string.");
        }
        CheckedType type = TypeSystem.findType((String) o);
        return new Set(type);
    }
}
