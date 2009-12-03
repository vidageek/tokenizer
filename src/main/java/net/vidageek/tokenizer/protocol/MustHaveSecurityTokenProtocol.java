package net.vidageek.tokenizer.protocol;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jonasabreu
 * 
 */
final public class MustHaveSecurityTokenProtocol implements Protocol {

    private final HttpServletRequest request;
    private final String securityTokenName;

    public MustHaveSecurityTokenProtocol(final HttpServletRequest request, final String securityTokenName) {
        this.request = request;
        this.securityTokenName = securityTokenName;
    }

    public boolean wasViolated() {
        return request.getParameter(securityTokenName) == null;
    }

    public String rejectionCause() {
        return "Security token [" + securityTokenName + "] is not present.";
    }

}
