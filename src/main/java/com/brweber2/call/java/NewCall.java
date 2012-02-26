package com.brweber2.call.java;

import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Symbol;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.type.CheckedType;
import com.brweber2.type.JavaType;
import com.brweber2.type.StackEffectType;
import com.brweber2.type.TypeStack;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class NewCall implements Call {

    // "John" "Smith" (java.lang.String, java.lang.String -> com.brweber2.Person ) new .

    @Override
    public void invoke(Stack stack) {
        try {
            StackEffect stackEffect = (StackEffect) stack.pop().object;
            Constructor constructor = getConstructorFromStackEffect(stackEffect);
            Class[] parameterTypes = constructor.getParameterTypes();
            Object[] args = new Object[parameterTypes.length];
            for ( int i = parameterTypes.length -1; i >= 0; i-- )
            {
                args[i] = stack.pop().object;
            }
            Object instance = constructor.newInstance(args);
            stack.push(new JavaType(instance.getClass()),instance);
        } catch (InstantiationException e) {
            throw new RuntimeException("Unable to construct and instance.",e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to construct and instance.",e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Unable to construct and instance.",e);
        }
    }

    private Constructor getConstructorFromStackEffect(StackEffect stackEffect) {
        try {
            String className = stackEffect.getOutputTypes().get(0).toSymbol().symbol;
            Class clazz = Class.forName(className);
            Class[] argTypes = new Class[stackEffect.getInputTypes().size()];
            int i = 0;
            for (CheckedType inputType : stackEffect.getInputTypes()) {
                argTypes[i] = Class.forName(inputType.toSymbol().symbol);
                i++;
            }
            return clazz.getConstructor(argTypes);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to find constructor because the class couldn't be found.",e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Unable to find a constructor matching " + stackEffect, e);
        }
    }

    @Override
    public StackEffect getStackEffect(TypeStack typeStack) {
        StackEffectType seType = (StackEffectType) typeStack.peek(-1);

        StackEffect stackEffect = new StackEffect();
        for (CheckedType inputType: seType.getStackEffect().getInputTypes()) {
            stackEffect.add(inputType);
        }
        stackEffect.add(seType);
        stackEffect.addArrow();
        if ( seType.getStackEffect().getOutputTypes().size() != 1 )
        {
            throw new RuntimeException("A constructor call must have one (and only one) output type!");
        }
        for (CheckedType outputType : seType.getStackEffect().getOutputTypes()) {
            stackEffect.add(outputType);
        }
        return stackEffect;
    }
}
