package bf;

import java.io.IOException;

/**
 * Simplest possible device representing the programs external interface.
 */
public interface InOut {

    /**
     * Writes a character to an output device.
     *
     * @param c the character to write
     * @throws IOException if output fails
     */
    void write(char c) throws IOException;

    /**
     * Read a character from the attached input device.
     *
     * @return the character
     * @throws IOException if input fails
     */
    char read() throws IOException;

}