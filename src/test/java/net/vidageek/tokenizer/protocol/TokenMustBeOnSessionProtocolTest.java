package net.vidageek.tokenizer.protocol;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpSession;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class TokenMustBeOnSessionProtocolTest {

    @Test
    public void testThatRejectsRequestIfTokenIsNotOnSession() {
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("token")).thenReturn(null);

        Assert.assertTrue(new TokenMustBeOnSessionProtocol(session, "token").wasViolated());
    }

    @Test
    public void testThatAcceptsRequestIfTokenIsOnSession() {
        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("token")).thenReturn("value");

        Assert.assertFalse(new TokenMustBeOnSessionProtocol(session, "token").wasViolated());
    }

    @Test
    public void testRejectedCause() {

        Assert.assertEquals(new TokenMustBeOnSessionProtocol(null, "token").rejectionCause(),
                "Security token [token] is not present in session.");

    }
}
