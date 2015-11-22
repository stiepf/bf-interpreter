package bf;

/**
 * Simplest representation of a runnable Brainf*ck program.
 */
public interface Code {

    /**
     * Produce the next executable instruction.
     *
     * @return the next Instruction as determined by the implementation
     */
    Instruction next();

    /**
     * Jump unconditionally to a target instruction, determined by the current position.
     */
    void jump();

    /**
     * Check if the program is finished, i.e. has no more executable instructions.
     *
     * @return true, if finished
     */
    boolean finished();

}