package org.test.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.test.app.MessageUtils.getMessage;
import org.test.list.LinkedList;
import static org.test.utilities.StringUtils.join;
import static org.test.utilities.StringUtils.split;

public class UtilsTest {

    @BeforeAll
    public static void printCmd(){
        System.out.println("command line in " + UtilsTest.class + ": " + ProcessHandle.current().info().commandLine().get());
    }

    @Test
    void testUtils(){
        LinkedList tokens;
        tokens = split(getMessage());
        String result = join(tokens);
        assertEquals("Hello World!",result);
    }

}
