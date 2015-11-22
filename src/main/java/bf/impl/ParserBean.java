package bf.impl;

import bf.Code;
import bf.CodeFactory;
import bf.Instruction;
import bf.Parser;

import java.util.*;

/**
 * Cheap implementation of the parsing routine.
 */
public class ParserBean implements Parser {

    /**
     * 'Symbol table' maps characters to Brainf*ck instructions.
     */
    private static final Map<Character, Instruction> SYMBOL_MAP = new HashMap<>();

    /**
     * Starts a loop in Brainf*ck.
     */
    private static final char OPEN = '[';

    /**
     * Ends a loop in Brainf*ck.
     */
    private static final char CLOSE = ']';

    /*
     * Initialize symbol table.
     */
    static {
        SYMBOL_MAP.put('>', Instruction.SHR);
        SYMBOL_MAP.put('<', Instruction.SHL);
        SYMBOL_MAP.put('+', Instruction.INC);
        SYMBOL_MAP.put('-', Instruction.DEC);
        SYMBOL_MAP.put('.', Instruction.OUT);
        SYMBOL_MAP.put(',', Instruction.IN);
        SYMBOL_MAP.put(OPEN, Instruction.FORW);
        SYMBOL_MAP.put(CLOSE, Instruction.BACK);
    }

    /**
     * Introduced to decouple parsing from creation of the runnable program to simplify testing.
     */
    private final CodeFactory codeFactory;

    /**
     * @param codeFactory the code factory
     */
    public ParserBean(CodeFactory codeFactory) {
        this.codeFactory = codeFactory;
    }


    @Override
    public Code parse(String sourceCode) {
        // we don't know the final size of the runnable code, so let's choose a dynamic implementation
        List<Instruction> instructionList = new LinkedList<>();

        // helps to build the jump table
        Stack<Integer> jumpStack = new Stack<>();

        // the jumptable itself
        Map<Integer, Integer> jumps = new HashMap<>();

        int codePtr = 0;
        for (int i = 0; i < sourceCode.length(); i++) {
            char c = sourceCode.charAt(i);
            if (SYMBOL_MAP.containsKey(c)) {
                codePtr++;
                instructionList.add(SYMBOL_MAP.get(c));

                // special handling for loop instructions
                if (c == OPEN) {
                    jumpStack.push(codePtr);
                } else if (c == CLOSE) {
                    if (jumpStack.empty()) {
                        throw new ParserException("Unbalanced closing bracket at position " + i);
                    } else {
                        int start = jumpStack.pop();
                        jumps.put(start, codePtr);
                        jumps.put(codePtr, start);
                    }
                }
            }
        }
        if (!jumpStack.empty()) {
            throw new ParserException("Unbalanced open bracket at position " + jumpStack.peek());
        }
        Instruction[] instructions = instructionList.toArray(new Instruction[instructionList.size()]);
        return codeFactory.create(instructions, jumps);
    }

}
