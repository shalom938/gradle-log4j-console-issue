package org.test.utilities.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.test.list.LinkedList;
import static org.test.utilities.StringUtils.join;
import static org.test.utilities.StringUtils.split;

public class UtilitiesTest {


    @Test
    void testUtils(){

        System.out.println("command line in public class UtilitiesTest : "+ProcessHandle.current().info().commandLine().get());

        LinkedList tokens;
        tokens = split("Hello    World!");
        String result = join(tokens);
        assertEquals("Hello World!",result);
    }
}
