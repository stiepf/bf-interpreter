package bf.impl;

import bf.*;

import java.io.*;

/**
 * The Brainf*ck interpreter's main entry point.
 */
class BF {

    /**
     * Default number of memory cells.
     */
    public static final int MEM_SIZE = 30000;

    /**
     * Default precision, i.e. 8-bit.
     */
    public static final int CELL_SIZE = 256;

    public static void main(String[] args) throws Exception {
        InOut io = new InOutBean(new InputStreamReader(System.in), new OutputStreamWriter(System.out));
        Memory mem = new MemoryBean(MEM_SIZE, CELL_SIZE);
        Parser pars = new ParserBean(new CodeFactoryBean());
        Interpreter interpreter = new InterpreterBean(mem, io);
        File sourceFile = new File(args[0]);
        Code code = pars.parse(readFile(sourceFile));
        interpreter.run(code);
    }

    /**
     * Helper method to read the source code file into a string.
     *
     * @param input a file containing BF source code
     * @return the input file's content
     * @throws IOException if read operation fails
     */
    private static String readFile(File input) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(input));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();
        return stringBuilder.toString();
    }
}
