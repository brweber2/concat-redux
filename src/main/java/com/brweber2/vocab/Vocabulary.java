package com.brweber2.vocab;

import com.brweber2.Call;

import java.util.HashMap;
import java.util.Map;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class Vocabulary {

    private static String currentVocabName;
    private static Map<String,Vocabulary> vocabs = new HashMap<String,Vocabulary>();

    public static void setVocab( String name )
    {
        if ( !vocabs.containsKey(name) )
        {
            vocabs.put( name, new Vocabulary() );
        }
        currentVocabName = name;
    }

    public static Vocabulary getCurrent()
    {
        if ( currentVocabName == null )
        {
            currentVocabName = "default";
        }
        if ( !vocabs.containsKey( currentVocabName) )
        {
            vocabs.put( currentVocabName, new Vocabulary() );
        }
        return vocabs.get( currentVocabName );
    }
    
    private String name;
    private Map<String,Call> words = new HashMap<String,Call>();

    public String getName() {
        return name;
    }

    public Call findWord( String name )
    {
        return words.get(name);
    }

    public void register( String name, Call word )
    {
        words.put(name,word);
    }
}
