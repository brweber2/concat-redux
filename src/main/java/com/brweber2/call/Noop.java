package com.brweber2.call;

import com.brweber2.run.Invoke;
import com.brweber2.type.CheckedType;
import com.brweber2.lex.LexToken;

import java.util.Collections;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Noop extends Invoke {

    public final LexToken token;

    public Noop(LexToken token) {
        super(Collections.<CheckedType>emptyList(), Collections.<CheckedType>emptyList());
        this.token = token;
    }
}
