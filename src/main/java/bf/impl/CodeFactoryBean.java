package bf.impl;

import bf.Code;
import bf.CodeFactory;
import bf.Instruction;

import java.util.Map;

/**
 * Decouples the instantiation and usage of Code objects from each other.
 */
public class CodeFactoryBean implements CodeFactory {

    @Override
    public Code create(Instruction[] instructions, Map<Integer, Integer> jumps) {
        return new CodeBean(instructions, jumps);
    }

}
