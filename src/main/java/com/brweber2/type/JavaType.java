package com.brweber2.type;

import com.brweber2.ast.Symbol;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class JavaType implements CheckedType {
    
    protected Class type;

    public JavaType(Class type) {
        this.type = type;
    }

    @Override
    public boolean ok(CheckedType type) {
        boolean flag = false;
        if ( type instanceof JavaType )
        {
            JavaType otherType = (JavaType) type;
            flag = otherType.type.isAssignableFrom(this.type);
        }
        return flag;
    }

    @Override
    public Symbol toSymbol() {
        return new Symbol(type.getName());
    }

    @Override
    public Class getJavaClass() {
        return type;
    }

    @Override
    public String toString() {
        return "JavaType{" +
                "type=" + type.getName() +
                '}';
    }
}
