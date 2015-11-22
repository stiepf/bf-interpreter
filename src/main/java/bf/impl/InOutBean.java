package bf.impl;

import bf.InOut;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

/**
 * Simplistic implementation based on character io.
 */
public class InOutBean implements InOut {

    private final Reader in;

    private final Writer out;

    /**
     * @param in  the program's input source
     * @param out the program's output sink
     */
    public InOutBean(Reader in, Writer out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void write(char c) throws IOException {
        out.write(c);
        out.flush();
    }

    @Override
    public char read() throws IOException {
        return (char) in.read();
    }
}
