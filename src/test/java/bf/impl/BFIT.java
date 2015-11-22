package bf.impl;

import bf.*;
import org.junit.Before;
import org.junit.Test;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Simple integration test - provides an alternative I/O instead of STDIN/STDOUT.
 */
public class BFIT {

    private static final String HELLO_WORLD = "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]" +
            ">>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.";
    private final Writer out = new StringWriter();
    private final Reader in = new StringReader("");
    private Interpreter underTest;
    private Code code;

    @Before
    public void setUp() {
        InOut io = new InOutBean(in, out);
        Memory mem = new MemoryBean(30000, 256);
        Parser parser = new ParserBean(new CodeFactoryBean());
        code = parser.parse(HELLO_WORLD);
        underTest = new InterpreterBean(mem, io);
    }

    @Test
    public void runHelloWorld() throws Exception {
        underTest.run(code);
        String result = out.toString();
        assertThat("Program output", result, is("Hello World!\n"));
    }

}
