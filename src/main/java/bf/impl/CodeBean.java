package bf.impl;

import bf.Code;
import bf.Instruction;

import java.util.Map;

/**
 * Implements the program's structure at runtime and the code related operations.
 */
public class CodeBean implements Code {

    /**
     * The parsed Brainf*ck program.
     */
    private final Instruction[] instructions;

    /**
     * The program' jumptable.
     */
    private final Map<Integer, Integer> jumps;

    private int ptr;

    /**
     * Creates the running program.
     *
     * @param instructions the parsed Brainf*ck program
     * @param jumps        the matching jumptable
     */
    public CodeBean(Instruction[] instructions, Map<Integer, Integer> jumps) {
        this.instructions = instructions;
        this.jumps = jumps;
    }

    @Override
    public Instruction next() {
        return instructions[ptr++];
    }

    @Override
    public void jump() {
        ptr = jumps.get(ptr);
    }

    @Override
    public boolean finished() {
        return ptr >= instructions.length;
    }

    int getPtr() {
        return ptr;
    }

    void setPtr(int ptr) {
        this.ptr = ptr;
    }
}
