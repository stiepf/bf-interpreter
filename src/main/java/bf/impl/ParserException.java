package bf.impl;

/**
 * Signifies a problem within the Brainf*ck source code.
 */
public class ParserException extends RuntimeException {

    public ParserException(String message) {
        super(message);
    }

}
