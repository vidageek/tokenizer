package net.vidageek.tokenizer.protocol;

import javax.servlet.http.HttpSession;

/**
 * @author jonasabreu
 * 
 */
final public class TokenMustBeOnSessionProtocol implements Protocol {

    private final HttpSession session;
    private final String securityTokenKey;

    public TokenMustBeOnSessionProtocol(final HttpSession session, final String securityTokenKey) {
        this.session = session;
        this.securityTokenKey = securityTokenKey;
    }

    public String rejectionCause() {
        return "Security token [" + securityTokenKey + "] is not present in session.";
    }

    public boolean wasViolated() {
        return session.getAttribute(securityTokenKey) == null;
    }

}
