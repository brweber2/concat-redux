package com.brweber2;

import java.util.List;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public interface Call {
    public void invoke(Stack stack);
    public List<CheckedType> getInputTypes();
    public List<CheckedType> getOutputTypes();
    public Instructions getInstructions();
}
