package com.brweber2.call.java;

import com.brweber2.ast.StackEffect;
import com.brweber2.ast.Symbol;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.type.CheckedType;
import com.brweber2.type.JavaType;
import com.brweber2.type.StackEffectType;
import com.brweber2.type.TypeStack;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class StaticMethodCall implements Call {

    // 123 "Main St." ( java.lang.Integer java.lang.String -> com.brweber2.Address ) com.brweber2.Person get-address static-method .

    @Override
    public void invoke(Stack stack) {
        try {
            String methodName = ((Symbol)stack.pop().object).symbol;
            String className = ((Symbol)stack.pop().object).symbol;
            StackEffect stackEffect = (StackEffect) stack.pop().object;
            Method method = getMethod(className,methodName,stackEffect);
            Class[] parameterTypes = method.getParameterTypes();
            Object[] args = new Object[parameterTypes.length];
            for ( int i = parameterTypes.length -1; i >= 0; i-- )
            {
                args[i] = stack.pop().object;
            }
            Object result = method.invoke(null,args);
            stack.push(new JavaType(method.getReturnType()),result);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to invoke a static method.",e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Unable to invoke a static method.",e);
        }
    }

    private Method getMethod(String className, String methodName, StackEffect stackEffect) {
        try {
            Class clazz = Class.forName(className);
            Class[] argTypes = new Class[stackEffect.getInputTypes().size()];
            int i = 0;
            for (CheckedType inputType : stackEffect.getInputTypes()) {
                argTypes[i] = Class.forName(inputType.toSymbol().symbol);
                i++;
            }
            Method m = clazz.getMethod(methodName, argTypes);
            if ( !Modifier.isStatic(m.getModifiers()) )
            {
                throw new RuntimeException("This is not a static method!");
            }
            return m;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to determine which static method to call because the class was not found.",e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Unable to call a static method because it could not be found.",e);
        }
    }

    @Override
    public StackEffect getStackEffect(TypeStack typeStack) {
        StackEffectType seType = (StackEffectType) typeStack.peek(-3);

        StackEffect stackEffect = new StackEffect();
        for (CheckedType inputType: seType.getStackEffect().getInputTypes()) {
            stackEffect.add(inputType); // args to method
        }
        stackEffect.add(seType); // stack effect
        stackEffect.add(new Symbol(Symbol.class.getName())); // class name
        stackEffect.add(new Symbol(Symbol.class.getName())); // method name
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
