package org.test.utilities.testing.it;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.test.list.LinkedList;
import static org.test.utilities.StringUtils.join;
import static org.test.utilities.StringUtils.split;

public class UtilitiesTest {

    @BeforeAll
    public static void printCmd(){
        System.out.println("command line in " + UtilitiesTest.class + ": " + ProcessHandle.current().info().commandLine().get());
    }


    @Test
    void testUtils(){
        LinkedList tokens;
        tokens = split("Hello    World!");
        String result = join(tokens);
        assertEquals("Hello World!",result);
    }
}
