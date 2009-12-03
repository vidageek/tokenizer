package net.vidageek.tokenizer.protocol;

/**
 * @author jonasabreu
 * 
 */
public interface Protocol {

    boolean wasViolated();

    String rejectionCause();

}
