package net.vidageek.tokenizer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

/**
 * @author jonasabreu
 * 
 */
final public class TokenizerFilterTest {

    @Test
    public void testThatRejectsRequestIfCantFindToken() throws Throwable {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);

        new TokenizerFilter().doFilter(request, response, chain);

        verifyZeroInteractions(chain);
        verify(response).sendError(403, "Invalid security token.");
    }
}
