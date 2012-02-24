package com.brweber2.transform;

import com.brweber2.ast.Symbol;
import com.brweber2.call.java.FieldAccess;
import com.brweber2.call.literal.IdentityCall;
import com.brweber2.run.Call;
import com.brweber2.ast.Statement;
import com.brweber2.type.JavaType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class StaticFieldTransformer implements StatementTransformer {
    @Override
    public List<Call> transform(Statement statement) {
        try {
            List<Call> calls = new ArrayList<Call>();
            List args = statement.getArgs();
            Symbol fieldName = (Symbol) args.get(0);
            Symbol className = (Symbol) args.get(1);
            Class c = Class.forName(className.symbol);
            Field f = c.getField(fieldName.symbol);
            calls.add(new IdentityCall(new JavaType(Symbol.class),fieldName));
            calls.add(new IdentityCall(new JavaType(Symbol.class),className));
            calls.add(new FieldAccess(f,true));
            return calls;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to transform a static field.",e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Unable to transform a static field.",e);
        }
    }
}
