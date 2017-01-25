package security;

import junit.framework.TestCase;
import org.junit.Test;

public class AuthenticationFilterTest extends TestCase {

    @Test
    public void testGetUsername() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        String token = "dXNlcj1qb25hdGhhbgp0aW1lPVN1biBKYW4gMjIgMTA6MzQ6MjMgQ0FUIDIwMTc=";
        String username = authenticationFilter.getUsername(token);
        assertEquals("jonathan", username);
    }

}