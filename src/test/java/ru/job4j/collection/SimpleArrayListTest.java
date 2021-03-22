package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayListTest {

    @Test
    public void whenAddOneElement() {
        SimpleArrayList<String> array = new SimpleArrayList<>();
        array.add("first");
        String rsl = array.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenIt() {
        SimpleArrayList<String> array = new SimpleArrayList<>();
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleArrayList<String> array = new SimpleArrayList<>();
        array.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        SimpleArrayList<String> array = new SimpleArrayList<>();
        array.add("first");
        array.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleArrayList<String> array = new SimpleArrayList<>();
        array.iterator().next();
    }

    @Test
    public void whenAddTwoElement() {
        SimpleArrayList<String> array = new SimpleArrayList<>();
        array.add("first");
        array.add("second");
        String rsl = array.get(1);
        assertThat(rsl, is("second"));
    }

    @Test
    public void whenSetFirstElement() {
        SimpleArrayList<String> array = new SimpleArrayList<>();
        array.add("first");
        array.set(0, "one");
        String rsl = array.get(0);
        assertThat(rsl, is("one"));
    }

    @Test
    public void whenSetSecondElement() {
        SimpleArrayList<String> array = new SimpleArrayList<>();
        array.add("first");
        array.add(("second"));
        array.set(1, "two");
        String rsl = array.get(1);
        assertThat(rsl, is("two"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenArrayEmpty() {
        SimpleArrayList<String> array = new SimpleArrayList<>();
        array.add("first");
        array.remove(0);
        array.get(0);
    }

    @Test
    public void whenRemoveOneElement() {
        SimpleArrayList<String> array = new SimpleArrayList<>();
        array.add("first");
        array.add("second");
        array.remove(0);
        String rsl = array.get(0);
        assertThat(rsl, is("second"));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleArrayList<String> array = new SimpleArrayList<>();
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");
        it.next();
    }
}