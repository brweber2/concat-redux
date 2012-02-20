package com.brweber2.bootstrap;

import com.brweber2.transform.DefineTransformer;
import com.brweber2.vocab.Vocabulary;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Bootstrap {
    static
    {
        Vocabulary.getCurrent().register("define", new DefineTransformer());
    }
}
