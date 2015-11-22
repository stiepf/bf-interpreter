package bf;

/**
 * The engine's core - it's memory. Most of the operations involve some memory interaction.
 * For practical reasons the memory is limited in size. Implementations must ensure a "wrap around"
 * if the pointer passes the upper or lower bound.
 */
public interface Memory {

    /**
     * Move the memory pointer to the right, i.e. increase it by one.
     */
    void shr();

    /**
     * Move the memory pointer to the left, i.e. decrease it by one.
     */
    void shl();

    /**
     * Increase the current cell by one.
     */
    void inc();

    /**
     * Subtract one from the current cell.
     */
    void dec();

    /**
     * Produce the current cell value.
     *
     * @return the value
     */
    char get();

    /**
     * Set the current cell's value to the given char.
     *
     * @param c the new value
     */
    void set(char c);

}