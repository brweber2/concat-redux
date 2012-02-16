package com.brweber2;

import com.brweber2.call.Literal;
import com.brweber2.call.PrintlnCall;
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
        Call println = new PrintlnCall();

        try {
            StaticTypeChecker.check( str, println );
            Assert.fail("Should not have type checked!!!");
        } catch (Exception e) {
            // good
        }
    }
}
