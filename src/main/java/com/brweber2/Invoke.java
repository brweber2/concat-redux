package com.brweber2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Invoke implements Call{

    
    protected List<CheckedType> inputTypes = new ArrayList<CheckedType>();
    protected List<CheckedType> outputTypes = new ArrayList<CheckedType>();
    
    protected Instructions instructions = new Instructions();

    public Invoke(List<CheckedType> inputTypes, List<CheckedType> outputTypes) {
        this.inputTypes.addAll(inputTypes);
        this.outputTypes.addAll(outputTypes);
    }

    public Instructions getInstructions() {
        return instructions;
    }

    public void setInstructions(Instructions instructions) {
        this.instructions = instructions;
    }

    public void invoke(Stack thisStack)
    {
        Stack stack = new Stack();
        if ( inputTypes.size() > thisStack.size() )
        {
            throw new RuntimeException("Not enough arguments on the current stack!");
        }
        for (int i = 0; i < inputTypes.size(); i++ )
        {
            stack.push( thisStack.peek(i) );
        }
        List outputs = instructions.execute( stack );
        if ( outputTypes.size() > outputs.size() )
        {
            throw new RuntimeException("Wrong number of outputs!");
        }
        for (Object output : outputs) {
            thisStack.push(output);
        }
    }
}
