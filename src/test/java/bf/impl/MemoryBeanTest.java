package bf.impl;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class MemoryBeanTest {

    private final MemoryBean underTest = new MemoryBean(5, 256);

    private final char[] data = underTest.getData();

    @Before
    public void setUp() {
        data[0] = 'a';
        data[1] = 'c';
        data[2] = 'e';
        data[3] = 'g';
        data[4] = 'i';
    }

    @Test
    public void getReturnsCurrentCell() throws Exception {
        underTest.setPtr(2);
        assertThat("Cell content", underTest.get(), is(equalTo('e')));
    }

    @Test
    public void setModifiesCurrentCell() throws Exception {
        underTest.setPtr(2);
        underTest.set('x');
        assertThat("Cell content", data[2], is(equalTo('x')));
    }

    @Test
    public void shrIncreasesPtr() throws Exception {
        underTest.setPtr(2);
        underTest.shr();
        assertThat("Memory pointer", underTest.getPtr(), is(equalTo(3)));
    }

    @Test
    public void shrWrapsAtRightmostCell() throws Exception {
        underTest.setPtr(4);
        underTest.shr();
        assertThat("Memory pointer", underTest.getPtr(), is(equalTo(0)));
    }

    @Test
    public void shlDecreasesPtr() throws Exception {
        underTest.setPtr(2);
        underTest.shl();
        assertThat("Memory pointer", underTest.getPtr(), is(equalTo(1)));
    }

    @Test
    public void shlWrapsAtLeftmostCell() throws Exception {
        underTest.setPtr(0);
        underTest.shl();
        assertThat("Memory pointer", underTest.getPtr(), is(equalTo(4)));
    }

    @Test
    public void incIncreasesCurrentCell() throws Exception {
        underTest.setPtr(2);
        underTest.inc();
        assertThat("Cell content", data[2], is(equalTo('f')));
    }

    @Test
    public void incWrapsAtCellSize() throws Exception {
        data[0] = (char) 255;
        underTest.inc();
        assertThat("Cell content", data[0], is(equalTo((char) 0)));
    }


    @Test
    public void decDecreasesCurrentCell() throws Exception {
        underTest.setPtr(2);
        underTest.dec();
        assertThat("Cell content", data[2], is(equalTo('d')));
    }

    @Test
    public void decWrapsAtZero() throws Exception {
        data[0] = (char) 0;
        underTest.dec();
        assertThat("Cell content", data[0], is(equalTo((char) 255)));
    }

}
