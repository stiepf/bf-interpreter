package bf;

import java.io.IOException;

/**
 * Runs the 'compiled' Brainf*ck program.
 */
public interface Interpreter {

    /**
     * Control the execution of the given program.
     *
     * @param code the program
     * @throws IOException if input or output fails
     */
    void run(Code code) throws IOException;

}