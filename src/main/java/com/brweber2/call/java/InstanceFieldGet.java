package com.brweber2.call.java;

import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Symbol;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.type.CheckedType;
import com.brweber2.type.JavaType;
import com.brweber2.type.StackEffectType;
import com.brweber2.type.TypeStack;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class InstanceFieldGet implements Call {
    
    // instance field-name ( instance-type -> field-type ) sfg

    @Override
    public void invoke(Stack stack) {
        try {
            StackEffect se = (StackEffect) stack.pop().object;
            Symbol fieldName = (Symbol) stack.pop().object;
            Object instance = stack.pop().object;
            Symbol className = (Symbol) se.getInputTypes().get(0);

            Field field = Class.forName(className.symbol).getField(fieldName.symbol);
            stack.push( new JavaType( field.getType() ), field.get(instance) );
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to access a static field.",e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to access a static field.",e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Unable to access a static field.",e);
        }
    }

    @Override
    public StackEffect getStackEffect(TypeStack typeStack) {
        StackEffectType seType = (StackEffectType) typeStack.peek(-1);

        List<CheckedType> inputTypes = seType.getStackEffect().getInputTypes();
        if ( inputTypes.size() != 1 )
        {
            throw new RuntimeException("A static field get must have the class type in the stack effect.");
        }

        List<CheckedType> outputTypes = seType.getStackEffect().getOutputTypes();
        if ( outputTypes.size() != 1 )
        {
            throw new RuntimeException("A static field get must have one return type.");
        }

        StackEffect stackEffect = new StackEffect();
        stackEffect.add(inputTypes.get(0));
        stackEffect.add(new Symbol(Symbol.class.getName())); // field-name
        stackEffect.add(seType);
        stackEffect.addArrow();
        stackEffect.add(outputTypes.get(0));
        return stackEffect;
    }
}
