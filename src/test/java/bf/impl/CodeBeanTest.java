package bf.impl;

import bf.Instruction;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static bf.Instruction.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CodeBeanTest {

    private static final Instruction[] INSTRUCTIONS = {INC, FORW, DEC, BACK, OUT};

    private CodeBean underTest;

    @Before
    public void setUp() {
        Map<Integer, Integer> jumps = new HashMap<>();
        jumps.put(2, 4);
        jumps.put(4, 2);
        underTest = new CodeBean(INSTRUCTIONS, jumps);
    }

    @Test
    public void nextReturnsInstructionsInProperOrder() throws Exception {
        for (Instruction expected : INSTRUCTIONS) {
            Instruction actual = underTest.next();
            assertThat("Instruction", actual, is(expected));
        }
    }

    @Test
    public void finishedIsFalseIfInstructionAvailable() throws Exception {
        for (int i = 0; i < INSTRUCTIONS.length - 1; i++) {
            underTest.next();
            assertThat("Program finished", underTest.finished(), is(false));
        }
    }

    @Test
    public void finishedIsTrueAfterLastInstruction() throws Exception {
        for (Instruction i : INSTRUCTIONS) {
            underTest.next();
        }
        assertThat("Program finished", underTest.finished(), is(true));
    }

    @Test
    public void jumpMovesPointerToCorrespondingPosition() throws Exception {
        underTest.setPtr(2);
        underTest.jump();
        assertThat("Code pointer", underTest.getPtr(), is(4));
    }
}
