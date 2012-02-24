package com.brweber2.ast;

import com.brweber2.run.Call;
import com.brweber2.transform.TransformAst;
import com.brweber2.type.CheckedType;
import com.brweber2.type.StaticTypeChecker;
import com.brweber2.type.TypeStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Block {
    
    private List<Statement> statements = new ArrayList<Statement>();
    
    public void add(Statement statement) {
        statements.add( statement );
    }

    public List<Call> getInstructions() {
        return TransformAst.transform(statements);
    }
    
    public StackEffect getStackEffect(  )
    {
        StackEffect stackEffect = new StackEffect();
        stackEffect.addArrow();
        List<Symbol> symbols = getOuts(this);
        for (Symbol symbol : symbols) {
            stackEffect.add(symbol);
        }
        return stackEffect;
    }

    public static List<Symbol> getOuts(Block block) {
        TypeStack typeStack = new TypeStack();
        if ( block != null )
        {
            for (Call call : block.getInstructions()) {
                StaticTypeChecker.checkCall(typeStack, call);
            }
        }
        List<Symbol> symbols = new ArrayList<Symbol>();
        for (CheckedType checkedType : typeStack.all() ) {
            symbols.add(checkedType.toSymbol());
        }
        return symbols;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("{ " );
        for (Call call : getInstructions()) {
            str.append(call);
            str.append(" ");
        }
        str.append(" }" );
        return str.toString();
    }
}
