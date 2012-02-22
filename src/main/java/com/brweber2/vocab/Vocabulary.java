package com.brweber2.vocab;

import com.brweber2.ast.Symbol;
import com.brweber2.run.Call;
import com.brweber2.type.CheckedType;

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

    public static Call findWord(Symbol vocab, Symbol wordName) {
        return vocabs.get(vocab.symbol).findWord(wordName.symbol);
    }

    // -------------------- END STATIC SECTION ------------------------------------------------------
    
    private Map<String,Call> words = new HashMap<String,Call>();
    private Map<String,CheckedType> typeAliases = new HashMap<String, CheckedType>();

    public Call findWord( String name )
    {
        return findWord(name,true);
    }

    private Call findWord( String name, boolean lookInKernel )
    {
        Call result = words.get(name);
        if ( result == null && lookInKernel )
        {
            return vocabs.get("kernel").findWord(name,false);
        }
        return result;
    }

    public void register( String name, Call word )
    {
        words.put(name,word);
    }
    
    public void alias( String name, CheckedType type )
    {
        typeAliases.put( name, type );
    }
    
    public CheckedType lookupType( String name )
    {
        return typeAliases.get( name );
    }

}
