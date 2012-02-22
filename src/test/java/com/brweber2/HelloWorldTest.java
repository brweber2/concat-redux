package com.brweber2;

import com.brweber2.call.PrintlnCall;
import com.brweber2.call.StringLiteral;
import com.brweber2.run.Call;
import org.testng.annotations.Test;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class HelloWorldTest {
    @Test
    public void testHelloWorld() throws InterruptedException {
        // define println function
        StringLiteral str = new StringLiteral("World");
        Call println = new PrintlnCall();

        Main.invoke(str, println);
    }
}
