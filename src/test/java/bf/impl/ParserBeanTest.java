package bf.impl;

import bf.CodeFactory;
import bf.Instruction;
import bf.Parser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static bf.Instruction.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.AdditionalMatchers.aryEq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ParserBeanTest {

    private static final String BF_SOURCE = "+-[.,[comment]<>][.]";
    private static final String UNBALANCE_BRACKETS_OPEN = "+-[.,[comment]<>[.]";
    private static final String UNBALANCED_BRACKETS_CLOSE = "+-[.,[comment]<>].]";

    private static final Instruction[] INSTRUCTIONS =
            {INC, DEC, FORW, OUT, IN, FORW, BACK, SHL, SHR, BACK, FORW, OUT, BACK};

    private Parser underTest;

    @Mock
    private CodeFactory codeFactoryMock;

    @Captor
    private ArgumentCaptor<Map<Integer, Integer>> jumpsCaptor;

    @Before
    public void setUp() {
        underTest = new ParserBean(codeFactoryMock);
    }

    @Test
    public void removesNonInstructionCharacters() throws Exception {
        underTest.parse(BF_SOURCE);
        verify(codeFactoryMock).create(aryEq(INSTRUCTIONS), any());
    }

    @Test(expected = ParserException.class)
    public void parseFailsForUnmatchedOpenBracket() throws Exception {
        underTest.parse(UNBALANCE_BRACKETS_OPEN);
    }

    @Test(expected = ParserException.class)
    public void parseFailsForUnmatchedCloseBracket() throws Exception {
        underTest.parse(UNBALANCED_BRACKETS_CLOSE);
    }

    @Test
    public void calculatesProperJumpTable() throws Exception {
        underTest.parse(BF_SOURCE);
        verify(codeFactoryMock).create(any(), jumpsCaptor.capture());
        Map<Integer, Integer> jumps = jumpsCaptor.getValue();
        assertPair(jumps, 3, 10);
        assertPair(jumps, 6, 7);
        assertPair(jumps, 11, 13);
    }

    private void assertPair(Map<Integer, Integer> jumps, int begin, int end) {
        int target = jumps.get(begin);
        assertThat("Forward jump", target, is(end));
        assertThat("Backward jump", end, is(target));
    }
}
