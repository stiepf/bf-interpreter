package bf;

import java.util.Map;

/**
 * Creates executable programs.
 */
public interface CodeFactory {

    /**
     * Transform a sequence of instructions and a corresponding jump tables into a executable representation.
     *
     * @param instructions a linear sequence of instructions
     * @param jumps        the jump table matching the positions in instruction sequence
     * @return a runnable program
     */
    Code create(Instruction[] instructions, Map<Integer, Integer> jumps);

}
