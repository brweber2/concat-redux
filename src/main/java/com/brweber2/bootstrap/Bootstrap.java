package com.brweber2.bootstrap;

import com.brweber2.call.controlflow.Conditional;
import com.brweber2.call.controlflow.DoBlock;
import com.brweber2.call.java.InstanceFieldGet;
import com.brweber2.call.java.InstanceMethodCall;
import com.brweber2.call.java.NewCall;
import com.brweber2.call.java.StaticFieldGet;
import com.brweber2.call.java.StaticMethodCall;
import com.brweber2.call.literal.FalseLiteral;
import com.brweber2.call.stack.Dup;
import com.brweber2.call.stack.Get;
import com.brweber2.call.stack.Infix;
import com.brweber2.call.stack.Pop;
import com.brweber2.call.PrintlnCall;
import com.brweber2.call.stack.Push;
import com.brweber2.call.stack.Set;
import com.brweber2.call.stack.Swap;
import com.brweber2.call.literal.TrueLiteral;
import com.brweber2.call.word.TypeAlias;
import com.brweber2.call.word.Use;
import com.brweber2.call.word.VocabCall;
import com.brweber2.type.JavaType;
import com.brweber2.vocab.Vocabulary;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Bootstrap {
    public static void bootstrap()
    {
        Vocabulary.setVocab("kernel");
        Vocabulary.getCurrent().register("vocab", new VocabCall());
        Vocabulary.getCurrent().register("alias", new TypeAlias() );
        Vocabulary.getCurrent().register("use", new Use() );
        Vocabulary.getCurrent().register("push", new Push() );
        Vocabulary.getCurrent().register("pop", new Pop() );
        Vocabulary.getCurrent().register("swap", new Swap() );
        Vocabulary.getCurrent().register("dup", new Dup() );
        Vocabulary.getCurrent().register("if", new Conditional() );
        Vocabulary.getCurrent().register("true", new TrueLiteral() );
        Vocabulary.getCurrent().register("false", new FalseLiteral() );
        Vocabulary.getCurrent().register("infix", new Infix() );
        Vocabulary.getCurrent().register("get", new Get() );
        Vocabulary.getCurrent().register("set", new Set() );
        Vocabulary.getCurrent().register("println", new PrintlnCall() );
        Vocabulary.getCurrent().register("do", new DoBlock() );
        Vocabulary.getCurrent().register("new", new NewCall() );
        Vocabulary.getCurrent().register("sm", new StaticMethodCall() );
        Vocabulary.getCurrent().register("im", new InstanceMethodCall() );
        Vocabulary.getCurrent().register("sfg", new StaticFieldGet() );
        Vocabulary.getCurrent().register("ifg", new InstanceFieldGet() );
        Vocabulary.setVocab("default");
        Vocabulary.getCurrent().alias("int", new JavaType(Integer.TYPE));
        // todo add other primitive types (and other type aliases in general)
    }
}
