package com.brweber2;

import com.brweber2.call.Literal;
import com.brweber2.call.PrintlnCall;
import org.testng.annotations.Test;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class HelloWorldTest {
    @Test
    public void testHelloWorld()
    {
        // define println function
        Literal<String> str = new Literal<String>("World");
        Call println = new PrintlnCall();

        Main.invoke(str, println);
    }
}
