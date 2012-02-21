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
        List<CheckedType> typeStack = new ArrayList<CheckedType>();
        for (Call call : calls) {
            checkCall( typeStack, call );
        }
    }

    public static void checkCall( List<CheckedType> typeStack, Call call )
    {
        StackEffect stackEffect = call.getStackEffect();
        for (CheckedType inputType : stackEffect.getInputTypes()) {
            CheckedType typeFromStack = typeStack.remove(typeStack.size()-1);
            boolean ok = typeFromStack.ok(inputType );
            if ( !ok )
            {
                throw new RuntimeException("Type check error! " + typeFromStack + " was not cool for " + inputType);
            }
        }
        for (Call nextCall : call.getInstructions().getCalls()) {
            checkCall( typeStack, nextCall );
        }
        for (CheckedType outputType : stackEffect.getOutputTypes()) {
            typeStack.add(outputType);
        }
    }

    public static void check(Stack stack, List<Call> calls) {
        List<CheckedType> typeStack = new ArrayList<CheckedType>();
        for (Stack.TypeObject o : stack.peekAll()) {
            typeStack.add(o.type);
        }
        for (Call call : calls) {
            checkCall(typeStack,call);
        }
    }
}
