package bf.impl;

import bf.*;

import java.io.IOException;

/**
 * Runs the Brainf*ck program by mapping the instructions to operations in Memory and InOut implementations.
 */
public class InterpreterBean implements Interpreter {

    /**
     * The machine's memory.
     */
    private final Memory memory;

    /**
     * The 'user interface'.
     */
    private final InOut inOut;

    /**
     * @param memory the memory
     * @param inOut  the 'user interface'
     */
    public InterpreterBean(Memory memory, InOut inOut) {
        this.memory = memory;
        this.inOut = inOut;
    }

    @Override
    public void run(Code code) throws IOException {
        while (!code.finished()) {
            Instruction cmd = code.next();
            switch (cmd) {
                case SHR:
                    memory.shr();
                    break;
                case SHL:
                    memory.shl();
                    break;
                case INC:
                    memory.inc();
                    break;
                case DEC:
                    memory.dec();
                    break;
                case FORW:
                    if (memory.get() == (char) 0) {
                        code.jump();
                    }
                    break;
                case BACK:
                    if (memory.get() != (char) 0) {
                        code.jump();
                    }
                    break;
                case IN:
                    memory.set(inOut.read());
                    break;
                case OUT:
                    inOut.write(memory.get());
                    break;
                default:
                    throw new IllegalArgumentException("Unknown instruction " + cmd.name());
            }
        }
    }
}
