package org.test.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import static org.test.app.MessageUtils.getMessage;
import org.test.list.LinkedList;
import static org.test.utilities.StringUtils.join;
import static org.test.utilities.StringUtils.split;

public class UtilsTest {

    @Test
    void testUtils(){
        LinkedList tokens;
        tokens = split(getMessage());
        String result = join(tokens);
        assertEquals("Hello World!",result);
    }

}
