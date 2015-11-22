package bf;

/**
 * A parser for Brainf***.
 */
public interface Parser {

    /**
     * Parse the sourcecode onto a runnable representation.
     *
     * @param sourceCode the program's source code (including comments)
     * @return the runnable program
     */
    Code parse(String sourceCode);

}