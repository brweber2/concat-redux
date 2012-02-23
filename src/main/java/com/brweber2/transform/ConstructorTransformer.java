package com.brweber2.transform;

import com.brweber2.ast.Symbol;
import com.brweber2.call.java.ConstructorCall;
import com.brweber2.call.literal.IdentityCall;
import com.brweber2.run.Call;
import com.brweber2.ast.Statement;
import com.brweber2.type.JavaType;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class ConstructorTransformer implements StatementTransformer {
    @Override
    public List<Call> transform(Statement statement) {
        try {
            // arg0 ... argN class new
            List args = statement.getArgs();
            Symbol classNameSymbol = (Symbol) args.remove(args.size() - 1);
            String classNameString = classNameSymbol.symbol;
            Class[] argClasses = getArgClasses(args);
            Class constructorClass = Class.forName(classNameString);
            Constructor c = constructorClass.getConstructor(argClasses);
            List<Call> calls = new ArrayList<Call>();
            for (Object arg : args) {
                calls.add( TransformAst.transformPiece( arg ) );
            }
            calls.add( new IdentityCall(new JavaType(constructorClass),classNameSymbol));
            calls.add( new ConstructorCall(c) );
            return calls;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Unable to transform a constructor call.",e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to transform a constructor call.",e);
        }
    }

    private Class[] getArgClasses(List args) {
        try {
            Class[] classes = new Class[args.size()];
            int i = 0;
            for (Object arg : args) {
                Symbol symbol = (Symbol) arg;
                classes[i] = Class.forName(symbol.symbol);
                i++;
            }
            return classes;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to determine the type of an argument to a constructor call.",e);
        }
    }
}
