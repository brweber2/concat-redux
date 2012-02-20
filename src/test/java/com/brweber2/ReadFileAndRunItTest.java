package com.brweber2;

import com.brweber2.call.PrintlnCall;
import com.brweber2.vocab.Vocabulary;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

/**
 * @author brweber2
 *         Copyright: 2012
 */
public class ReadFileAndRunItTest {
    
    @Test
    public void readFileAndRunIt() throws IOException {
        Vocabulary.getCurrent().register("println", new PrintlnCall() );
        String source = "\n\n:define wow ( java.lang.String ->  ) { :println . } .\n" +
                        ":wow \"Hello\" .";
        File file = File.createTempFile("readFileAndRunIt", UUID.randomUUID().toString());
        FileWriter writer = new FileWriter(file);
        writer.write(source);
        writer.close();
        Runtime.main(new String[] {file.getAbsolutePath()});
        System.out.println();
    }
}
