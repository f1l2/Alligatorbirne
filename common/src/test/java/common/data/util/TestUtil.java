package common.data.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import common.data.util.Util;

public class TestUtil {

    @Test
    public void testParseHost() {
        String authority = "localhost:8080";
        assertEquals("localhost", Util.parseHost(authority));
        assertEquals("8080", Util.parsePort(authority));

    }

}
