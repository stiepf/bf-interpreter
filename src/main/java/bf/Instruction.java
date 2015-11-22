package bf;

/**
 * All instructions of the Brainf*ck language.
 */
public enum Instruction {

    /**
     * Shift right (increase memory pointer)
     */
    SHR,

    /**
     * Shift left (decrease memory pointer)
     */
    SHL,

    /**
     * Increase value of current cell
     */
    INC,

    /**
     * Decrease value of current cell
     */
    DEC,

    /**
     * Print value of current cell
     */
    OUT,

    /**
     * Read value into current cell
     */
    IN,

    /**
     * Jump forward conditionally, based on value of current cell
     */
    FORW,

    /**
     * Jump backward conditionally based on value of current cell
     */
    BACK

}
