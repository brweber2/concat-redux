package com.brweber2.call;

import com.brweber2.Call;
import com.brweber2.CheckedType;
import com.brweber2.Instructions;
import com.brweber2.Stack;
import com.brweber2.type.JavaType;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Literal<T> implements Call {

    protected T literal;
    protected CheckedType type;

    public Literal(T literal, Class<T> c) {
        this.literal = literal;
        type = new JavaType(c);
    }

    @Override
    public void invoke(Stack stack) {
        stack.push( literal );
    }

    @Override
    public List<CheckedType> getInputTypes() {
        return Collections.emptyList();
    }

    @Override
    public List<CheckedType> getOutputTypes() {
        return Arrays.asList(type);
    }

    @Override
    public Instructions getInstructions() {
        return new Instructions();
    }
}
