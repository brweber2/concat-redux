package com.brweber2;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Main {

    public static void invoke(Call ... calls) {
        Stack stack = new Stack();
        for (Call call : calls) {
            call.invoke(stack);
        }
    }

}
