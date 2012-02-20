package com.brweber2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Instructions {
    
    private List<Call> calls = new ArrayList<Call>();

    public Instructions(List<Call> calls) {
        this(calls.toArray(new Call[calls.size()]));
    }
    
    public Instructions(Call ... calls) {
        if ( calls != null )
        {
            Collections.addAll(this.calls, calls);
        }
    }

    public List<Call> getCalls() {
        return calls;
    }

    public List execute( Stack stack )
    {
        for (Call call : calls) {
            call.invoke(stack);
        }
        return stack.popAll();
    }
}
