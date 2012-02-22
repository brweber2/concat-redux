package com.brweber2.run;

import com.brweber2.ast.StackEffect;
import com.brweber2.type.CheckedType;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Invoke implements Call {
    
    protected StackEffect stackEffect;
    
    protected Instructions instructions = new Instructions();

    protected Invoke( List<CheckedType> inputTypes, List<CheckedType> outputTypes )
    {
        StackEffect se = new StackEffect();
        for (CheckedType inputType : inputTypes) {
            se.add(inputType.toSymbol());
        }
        se.addArrow();
        for (CheckedType outputType : outputTypes) {
            se.add(outputType.toSymbol());
        }
        this.stackEffect = se;
    }
    
    public Invoke(StackEffect stackEffect) {
        this.stackEffect = stackEffect;
    }

//    public Instructions getInstructions() {
//        return instructions;
//    }
//
    public void setInstructions(Instructions ... instructions) {
        if ( this.instructions == null )
        {
            this.instructions = new Instructions();
        }
        for (Instructions instruction : instructions) {
            this.instructions.getCalls().addAll(instruction.getCalls());
        }
    }

    public void invoke(Stack thisStack)
    {
        Stack stack = new Stack();

        if ( stackEffect.getInputTypes().size() > thisStack.size() )
        {
            throw new RuntimeException("Not enough arguments on the current stack!");
        }
        for (int i = 0; i < stackEffect.getInputTypes().size(); i++ )
        {
            Stack.TypeObject typeObject = thisStack.pop();
            stack.push( typeObject.type, typeObject.object );
        }
        List outputs = getOutputs(stack);
        if ( stackEffect.getOutputTypes().size() > outputs.size() )
        {
            throw new RuntimeException("Wrong number of outputs!");
        }
        int i = outputs.size();
        for (Object output : outputs) {
            CheckedType type = stackEffect.getOutputTypes().get(i-1);
            thisStack.push(type, output);
            i--;
        }
    }

    @Override
    public StackEffect getStackEffect() {
        return stackEffect;
    }

    protected List getOutputs( Stack stack )
    {
        return instructions.execute(stack);
    }
}
