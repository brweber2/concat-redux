package com.brweber2;

import com.brweber2.ast.StackEffect;
import com.brweber2.call.Literal;
import com.brweber2.call.PrintlnCall;
import com.brweber2.lex.Symbol;
import com.brweber2.run.Call;
import com.brweber2.run.Stack;
import com.brweber2.type.StaticTypeChecker;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigInteger;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class TypeCheckTest {
    @Test
    public void typeCheckHelloWorld() throws InterruptedException {
        Literal<String> str = new Literal<String>("World",String.class);
        Call println = new PrintlnCall();

        StaticTypeChecker.check( str, println );
    }

    @Test
    public void typeCheckInvalidHelloWorld() throws InterruptedException {
        Literal<BigInteger> str = new Literal<BigInteger>(BigInteger.TEN,BigInteger.class);
        Call println = new Call() {
            @Override
            public void invoke(Stack stack) {
                String object = (String) stack.pop().object;
                System.out.println("testing println: " + object + "!");
            }

            @Override
            public StackEffect getStackEffect() {
                StackEffect stackEffect = new StackEffect();
                stackEffect.add(new Symbol(String.class.getName()));
                stackEffect.addArrow();
                return stackEffect;
            }
        };

        try {
            StaticTypeChecker.check(str, println);
            Assert.fail("Should not have type checked!!!");
        } catch (Exception e) {
            // good
        }
    }
}
