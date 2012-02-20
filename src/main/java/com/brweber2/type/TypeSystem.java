package com.brweber2.type;

import com.brweber2.CheckedType;
import com.brweber2.vocab.Vocabulary;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class TypeSystem {
    
    public static CheckedType findType( String name )
    {
        // todo add user defined types, imports, aliases, etc.
        try {
            return new JavaType(Class.forName(name));
        } catch (ClassNotFoundException e) {
            CheckedType type = Vocabulary.getCurrent().lookupType(name);
            if ( type == null )
            {
                throw new RuntimeException("Unable to find type " + name + ".",e);
            }
            return type;
        }
    }
}
