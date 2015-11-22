package bf.impl;

import bf.Memory;

/**
 * Flexible, character-based implementation of the Brainf*ck interpreter's memory.
 */
public class MemoryBean implements Memory {

    /**
     * The size of the memory, i.e. the number of cells.
     */
    private final int memSize;

    /**
     * The size of a single cell, i.e.
     */
    private final int cellSize;

    private final char[] data;

    private int ptr;

    public MemoryBean(int memSize, int cellSize) {
        this.memSize = memSize;
        this.cellSize = cellSize;
        data = new char[memSize];
    }

    @Override
    public void shr() {
        ptr = (ptr + 1) % memSize;
    }

    @Override
    public void shl() {
        ptr = (ptr - 1 + memSize) % memSize;
    }

    @Override
    public void inc() {
        data[ptr] = (char) ((data[ptr] + 1) % cellSize);
    }

    @Override
    public void dec() {
        data[ptr] = (char) ((data[ptr] - 1 + cellSize) % cellSize);
    }

    @Override
    public char get() {
        return data[ptr];
    }

    @Override
    public void set(char c) {
        data[ptr] = c;
    }


    // package-private test interface

    /**
     * @return the memory itself
     */
    final char[] getData() {
        return data;
    }

    /**
     * @return the current position of the memory pointer
     */
    final int getPtr() {
        return ptr;
    }

    /**
     * Set the memory pointer's current position.
     *
     * @param position the position
     */
    final void setPtr(int position) {
        ptr = position;
    }
}
