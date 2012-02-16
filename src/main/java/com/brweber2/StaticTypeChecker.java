package com.brweber2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class StaticTypeChecker {

    public static void check( Call ... calls )
    {
        List<CheckedType> typeStack = new ArrayList<CheckedType>();
        for (Call call : calls) {
            checkCall( typeStack, call );
        }
    }

    public static void checkCall( List<CheckedType> typeStack, Call call )
    {
        List<CheckedType> inputTypes = call.getInputTypes();
        // pop input types from type stack
        if ( typeStack.size() < inputTypes.size() )
        {
            throw new RuntimeException("Type checking is not balanced. Not enough types on the stack for the inputs required.");
        }
        for (CheckedType inputType : inputTypes) {
            if ( ! inputType.ok( typeStack.remove( typeStack.size()-1)))
            {
                throw new RuntimeException( "Type mismatch!" );
            }
        }
        for (Call c : call.getInstructions().getCalls()) {
            checkCall( typeStack, call );
        }
        List<CheckedType> outputTypes = call.getOutputTypes();
        // put output types on the type stack
        for (CheckedType outputType : outputTypes) {
            typeStack.add( outputType );
        }
    }
}
