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

    public void setInstructions(Instructions ... instructions) {
        if ( this.instructions == null )
        {
            this.instructions = new Instructions();
        }
        for (Instructions instruction : instructions) {
            this.instructions.getCalls().addAll(instruction.getCalls());
        }
    }

    public List<CheckedType> getInputTypes() {
        return inputTypes;
    }

    public void setInputTypes(List<CheckedType> inputTypes) {
        this.inputTypes = inputTypes;
    }

    public List<CheckedType> getOutputTypes() {
        return outputTypes;
    }

    public void setOutputTypes(List<CheckedType> outputTypes) {
        this.outputTypes = outputTypes;
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
        List outputs = getOutputs(stack);
        if ( outputTypes.size() > outputs.size() )
        {
            throw new RuntimeException("Wrong number of outputs!");
        }
        for (Object output : outputs) {
            thisStack.push(output);
        }
    }
    
    protected List getOutputs( Stack stack )
    {
        return instructions.execute(stack);
    }
}
