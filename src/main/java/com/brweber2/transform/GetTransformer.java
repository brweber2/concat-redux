package com.brweber2.transform;

import com.brweber2.Call;
import com.brweber2.CheckedType;
import com.brweber2.ast.Statement;
import com.brweber2.call.Get;
import com.brweber2.lex.Var;
import com.brweber2.type.JavaType;
import com.brweber2.type.TypeSystem;

import java.util.Arrays;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class GetTransformer implements StatementTransformer {
    @Override
    public List<Call> transform(Statement statement) {
        // @var type get
        // @firstName Text get
        if ( statement.getPieces().size() != 3 )
        {
            throw new RuntimeException("A get must be in the form of '@var type get'.");
        }
        Object o = statement.getPieces().get(0);
        if ( !(o instanceof Var) )
        {
            throw new RuntimeException("Get must take a var! " + o + " is not acceptable.");
        }
        Object t = statement.getPieces().get(1);
        if ( !(t instanceof String) )
        {
            throw new RuntimeException("You must specify a type for a 'get'.");
        }
        CheckedType type = TypeSystem.findType((String) t);
        return Arrays.<Call>asList(new Get(type));
    }
}
