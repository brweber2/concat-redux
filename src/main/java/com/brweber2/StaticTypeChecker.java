package com.brweber2;

import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Statement;
import com.brweber2.call.DefineCall;

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
            typeStack.add(inputType);
        }
        for (Call nextCall : call.getInstructions().getCalls()) {
            checkCall( typeStack, nextCall );
        }
        for (CheckedType outputType : stackEffect.getOutputTypes()) {
            if ( !outputType.ok(typeStack.remove(typeStack.size()-1) ) )
            {
                throw new RuntimeException("Type check error!");
            }
        }
    }

//    public static void checkCall2( List<CheckedType> typeStack, Call call )
//    {
//        if ( call instanceof DefineCall )
//        {
//            checkDefineCall( typeStack, (DefineCall) call );
//            return;
//        }
//        List<CheckedType> inputTypes = call.getInputTypes();
//        // pop input types from type stack
//        if ( typeStack.size() < inputTypes.size() )
//        {
//            throw new RuntimeException("Type checking is not balanced. Not enough types on the stack for the inputs required. Checking " + call + " with " + typeStack);
//        }
//        for (CheckedType inputType : inputTypes) {
//            boolean ok = inputType.ok( typeStack.remove( typeStack.size()-1));
//            if ( !ok )
//            {
//                throw new RuntimeException( "Type mismatch!" );
//            }
//        }
//        for (Call c : call.getInstructions().getCalls()) {
//            checkCall( typeStack, c );
//        }
//        List<CheckedType> outputTypes = call.getOutputTypes();
//        // put output types on the type stack
//        for (CheckedType outputType : outputTypes) {
//            typeStack.add( outputType );
//        }
//    }
//
//    private static void checkDefineCall(List<CheckedType> typeStack, DefineCall call) {
//        for (CheckedType inputType : call.getInputTypes()) {
//            typeStack.add(inputType);
//        }
//        for (Call c : call.getInstructions().getCalls()) {
//            checkCall( typeStack, c );
//        }
//        List<CheckedType> outputTypes = call.getOutputTypes();
//        // put output types on the type stack
//        for (CheckedType outputType : outputTypes) {
//            typeStack.add( outputType );
//        }
//    }

}
