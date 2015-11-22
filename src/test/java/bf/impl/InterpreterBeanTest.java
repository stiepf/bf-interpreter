package bf.impl;

import bf.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static bf.Instruction.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InterpreterBeanTest {

    private Interpreter underTest;

    @Mock
    private Memory memoryMock;

    @Mock
    private InOut inOutMock;

    @Mock
    private Code codeMock;

    @Before
    public void setUp() {
        underTest = new InterpreterBean(memoryMock, inOutMock);
    }

    @Test
    public void executesShr() throws Exception {
        runSingleInstruction(SHR);
        verify(memoryMock).shr();
    }

    @Test
    public void executesShl() throws Exception {
        runSingleInstruction(SHL);
        verify(memoryMock).shl();
    }

    @Test
    public void executesInc() throws Exception {
        runSingleInstruction(INC);
        verify(memoryMock).inc();
    }

    @Test
    public void executesDec() throws Exception {
        runSingleInstruction(DEC);
        verify(memoryMock).dec();
    }

    @Test
    public void executesIn() throws Exception {
        when(inOutMock.read()).thenReturn('C');
        runSingleInstruction(IN);
        verify(memoryMock).set('C');
    }

    @Test
    public void executesOut() throws Exception {
        when(memoryMock.get()).thenReturn('C');
        runSingleInstruction(OUT);
        verify(inOutMock).write('C');
    }

    @Test
    public void executesForwIfNull() throws Exception {
        verifyJumpInstruction(FORW, 0, 1);
    }

    @Test
    public void executesForwNot() throws Exception {
        verifyJumpInstruction(FORW, 1, 0);
    }

    @Test
    public void executesBackIfNotNull() throws Exception {
        verifyJumpInstruction(BACK, 1, 1);
    }

    @Test
    public void executesBackNot() throws Exception {
        verifyJumpInstruction(BACK, 0, 0);
    }

    private void runSingleInstruction(Instruction instruction) throws IOException {
        when(codeMock.finished()).thenReturn(false, true);
        when(codeMock.next()).thenReturn(instruction);
        underTest.run(codeMock);
    }

    private void verifyJumpInstruction(Instruction instruction, int value, int jumpTimes)
            throws Exception {
        when(memoryMock.get()).thenReturn((char) value);
        runSingleInstruction(instruction);
        verify(codeMock, times(jumpTimes)).jump();
    }
}
