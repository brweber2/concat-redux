package com.brweber2;

import com.brweber2.run.Call;
import com.brweber2.run.Stack;

import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Main {

    public static void invoke(List<Call> calls) {
        invoke(calls.toArray(new Call[calls.size()]));
    }
    
    public static void invoke(Call ... calls) {
        Stack stack = new Stack();
        for (Call call : calls) {
            call.invoke(stack);
        }
    }

}
