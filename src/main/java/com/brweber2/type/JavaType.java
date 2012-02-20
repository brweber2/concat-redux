package com.brweber2.type;

import com.brweber2.CheckedType;
import com.brweber2.lex.Symbol;

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
            flag = this.type.isAssignableFrom(otherType.type);
            System.err.println("checking if " + otherType.type.getName() + " is ok for " + this.type.getName() + " and the answer is " + flag);
        }
        return flag;
    }

    @Override
    public Symbol toSymbol() {
        return new Symbol(type.getClass().getName());
    }
}
