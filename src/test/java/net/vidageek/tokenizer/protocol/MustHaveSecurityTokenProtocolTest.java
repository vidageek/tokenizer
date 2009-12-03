package net.vidageek.tokenizer.protocol;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import junit.framework.Assert;

import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class MustHaveSecurityTokenProtocolTest {

    @Test
    public void testThatIsViolatedIfCantFindTokenRequestParameter() {
        HttpServletRequest request = mock(HttpServletRequest.class);

        Assert.assertTrue(new MustHaveSecurityTokenProtocol(request, "token").wasViolated());
    }

    @Test
    public void testThatIsNotViolatedIfFindsTokenRequestParameter() {
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("token")).thenReturn("");

        Assert.assertFalse(new MustHaveSecurityTokenProtocol(request, "token").wasViolated());
    }
}
