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
public class InstanceFieldTransformer implements StatementTransformer {
    @Override
    public List<Call> transform(Statement statement) {
        try {
            List<Call> calls = new ArrayList<Call>();
            List args = statement.getArgs();
            Symbol fieldName = (Symbol) args.remove(args.size() - 1);
            Symbol className = (Symbol) args.remove(args.size() - 1);
            String fieldNameString = fieldName.symbol;
            System.out.println("fn: " + fieldNameString);
            String classNameString = className.symbol;
            System.out.println("cn: " + classNameString);
            Class c = Class.forName(className.symbol);
            for (Field field : c.getFields()) {
                System.out.println("found field: " + field.getName());
            }
            Field f = c.getField(fieldName.symbol);
            for (Object arg : args) {
                calls.add(TransformAst.transformPiece(arg));
            }
            calls.add(new IdentityCall(new JavaType(Symbol.class),className));
            calls.add(new IdentityCall(new JavaType(Symbol.class),fieldName));
            calls.add(new FieldAccess(f,false));
            return calls;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to transform an instance field.",e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Unable to transform an instance field.",e);
        }
    }
}
