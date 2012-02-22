package com.brweber2.lex;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class VarToken implements LexToken {
    
    public final String var;
    public int lineNumber;

    public VarToken(String var)
    {
        this.var = var;
        this.lineNumber = -1;
    }

    public VarToken(String var, int lineNumber)
    {
        this.var = var;
        this.lineNumber = lineNumber;
    }
    
    public VarToken(SymbolToken symbol, int lineNumber) {
        this.var = symbol.symbol;
        this.lineNumber = lineNumber;
    }

    @Override
    public String toString() {
        return "VarToken{" +
                "var='" + var + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VarToken var1 = (VarToken) o;

        if (!var.equals(var1.var)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return var.hashCode();
    }

    @Override
    public LexToken setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
        return this;
    }

    @Override
    public int getLineNumber() {
        return lineNumber;
    }
}
