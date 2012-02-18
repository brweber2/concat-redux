package com.brweber2.lex;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Var implements LexToken {
    
    public final String var;
    
    public Var(String var)
    {
        this.var = var;
    }
    
    public Var(Symbol symbol) {
        this.var = symbol.symbol;
    }

    @Override
    public String toString() {
        return "Var{" +
                "var='" + var + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Var var1 = (Var) o;

        if (!var.equals(var1.var)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return var.hashCode();
    }
}
