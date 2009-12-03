package net.vidageek.tokenizer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.vidageek.tokenizer.protocol.MustHaveSecurityTokenProtocol;
import net.vidageek.tokenizer.protocol.Protocol;

/**
 * @author jonasabreu
 * 
 */
final public class TokenizerFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(final ServletRequest servletRequest, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;

        for (Protocol protocol : protocols(request)) {
            if (protocol.wasViolated()) {
                rejectRequest(response);
            }
        }
    }

    private void rejectRequest(final ServletResponse response) throws IOException {
        ((HttpServletResponse) response).sendError(403, "Invalid security token.");
    }

    private List<Protocol> protocols(final HttpServletRequest request) {
        final ArrayList<Protocol> protocols = new ArrayList<Protocol>();
        protocols.add(new MustHaveSecurityTokenProtocol(request, "security-token"));
        return protocols;
    }

    public void init(final FilterConfig filterConfig) throws ServletException {
    }

}
