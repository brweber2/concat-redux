package com.brweber2.type;

import com.brweber2.ast.StackEffect;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class StaticTypeChecker {

    public static void check( List<Call> calls )
    {
        check(calls.toArray(new Call[calls.size()]));
    }
    
    public static void check( Call ... calls )
    {
        TypeStack typeStack = new TypeStack();
        for (Call call : calls) {
            checkCall( typeStack, call );
        }
    }

    public static void checkCall( TypeStack typeStack, Call call )
    {
        StackEffect stackEffect = call.getStackEffect(typeStack);
        for (CheckedType inputType : stackEffect.getInputTypes()) {
            CheckedType typeFromStack = typeStack.pop();
//            System.out.println("checking " + typeFromStack + " and " + inputType);
            boolean ok = typeFromStack.ok(inputType );
            if ( !ok )
            {
                throw new RuntimeException("Type check error! " + typeFromStack + " was not cool for " + inputType + " of " + call);
            }
        }
        for (CheckedType outputType : stackEffect.getOutputTypes()) {
            typeStack.add(outputType);
        }
    }

    public static void check(Stack stack, List<Call> calls) {
        TypeStack typeStack = new TypeStack();
        for (Stack.TypeObject o : stack.peekAll()) {
            typeStack.add(o.type);
        }
        for (Call call : calls) {
            checkCall(typeStack,call);
        }
    }
}
