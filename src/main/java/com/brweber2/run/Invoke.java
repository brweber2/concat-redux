package com.brweber2.run;

import com.brweber2.ast.StackEffect;
import com.brweber2.type.CheckedType;
import com.brweber2.type.TypeStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Invoke implements Call {
    
    protected StackEffect stackEffect;
    
    protected List<Call> calls = new ArrayList<Call>();
    
    public Invoke(StackEffect stackEffect,List<Call> instructions) {
        this.stackEffect = stackEffect;
        this.calls = stackEffect.getInstructions();
        this.calls.addAll(instructions);
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
        getOutputs(stack);
        if ( stackEffect.getOutputTypes().size() > stack.size() )
        {
            throw new RuntimeException("Wrong number of outputs!");
        }
        int i = stack.size()-1;
        for (Object output : stack.popAll()) {
            List<CheckedType> outputTypes = stackEffect.getOutputTypes();
            if ( i < outputTypes.size() )
            {
                CheckedType type = outputTypes.get(i);
                thisStack.push(type, output);
            }
            i--;
        }
    }

    @Override
    public StackEffect getStackEffect(TypeStack typeStack) {
        return stackEffect;
    }

    protected void getOutputs( Stack stack )
    {
        for (Call call : calls) {
            call.invoke(stack);
        }
    }
}
