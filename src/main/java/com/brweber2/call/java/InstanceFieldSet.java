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
public class InstanceFieldSet implements Call {
    
    // <value> <instance> <field-name> ( -> Instance-type  ) ifs
    
    @Override
    public void invoke(Stack stack) {
        try {
            StackEffect se = (StackEffect) stack.pop().object;
            Symbol fieldName = (Symbol) stack.pop().object;
            Object instance = stack.pop().object;
            Object value = stack.pop().object;
            CheckedType className = se.getOutputTypes().get(0);

            Field field = Class.forName(className.toSymbol().symbol).getField(fieldName.symbol);
            field.set(instance,value);
            stack.push( new JavaType(instance.getClass()), instance );
        } catch (NoSuchFieldException e) {
            throw new RuntimeException("Unable to set instance field.",e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to set instance field.",e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to set instance field.",e);
        }
    }

    @Override
    public StackEffect getStackEffect(TypeStack typeStack) {
        StackEffect stackEffect = new StackEffect();

        StackEffectType seType = (StackEffectType) typeStack.peek(-1);
        List<CheckedType> inputTypes = seType.getStackEffect().getInputTypes();
        if ( !inputTypes.isEmpty() )
        {
            throw new RuntimeException("A instance field set must not have an input type.");
        }

        List<CheckedType> outputTypes = seType.getStackEffect().getOutputTypes();
        if ( outputTypes.size() != 1 )
        {
            throw new RuntimeException("A instance field set must have the class type in the stack effect.");
        }
        
        stackEffect.add(typeStack.peek(-4));
        stackEffect.add(outputTypes.get(0));
        stackEffect.add(new Symbol(Symbol.class.getName()));
        stackEffect.add(seType);
        stackEffect.addArrow();
        stackEffect.add(outputTypes.get(0));
        return stackEffect;
    }
}
