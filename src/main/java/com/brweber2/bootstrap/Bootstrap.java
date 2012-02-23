package com.brweber2.bootstrap;

import com.brweber2.call.Conditional;
import com.brweber2.call.FalseLiteral;
import com.brweber2.call.Get;
import com.brweber2.call.Pop;
import com.brweber2.call.PrintlnCall;
import com.brweber2.call.Push;
import com.brweber2.call.Set;
import com.brweber2.call.Swap;
import com.brweber2.call.TrueLiteral;
import com.brweber2.call.TypeAlias;
import com.brweber2.call.Use;
import com.brweber2.call.VocabCall;
import com.brweber2.vocab.Vocabulary;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Bootstrap {
    public static void bootstrap()
    {
        Vocabulary.setVocab("kernel");
        Vocabulary.getCurrent().register("vocab", new VocabCall() );
        Vocabulary.getCurrent().register("alias", new TypeAlias() );
        Vocabulary.getCurrent().register("use", new Use() );
        Vocabulary.getCurrent().register("push", new Push() );
        Vocabulary.getCurrent().register("pop", new Pop() );
        Vocabulary.getCurrent().register("swap", new Swap() );
        Vocabulary.getCurrent().register("if", new Conditional() );
        Vocabulary.getCurrent().register("true", new TrueLiteral() );
        Vocabulary.getCurrent().register("false", new FalseLiteral() );
        Vocabulary.getCurrent().register("get", new Get() );
        Vocabulary.getCurrent().register("set", new Set() );
        Vocabulary.getCurrent().register("println", new PrintlnCall() );
        Vocabulary.setVocab("default");
    }
}
