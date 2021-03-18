package ru.job4j.generics;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test
    public void whenAddOneElement() {
        SimpleArray<String> array = new SimpleArray<>(1);
        array.add("first");
        String rsl = array.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddTwoElement() {
        SimpleArray<String> array = new SimpleArray<>(2);
        array.add("first");
        array.add("second");
        String rsl = array.get(1);
        assertThat(rsl, is("second"));
    }

    @Test
    public void whenSetFirstElement() {
        SimpleArray<String> array = new SimpleArray<>(1);
        array.add("first");
        array.set(0, "one");
        String rsl = array.get(0);
        assertThat(rsl, is("one"));
    }

    @Test
    public void whenSetSecondElement() {
        SimpleArray<String> array = new SimpleArray<>(2);
        array.add("first");
        array.add(("second"));
        array.set(1, "two");
        String rsl = array.get(1);
        assertThat(rsl, is("two"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenArrayEmpty() {
        SimpleArray<String> array = new SimpleArray<>(1);
        array.add("first");
        array.remove(0);
        array.get(0);
    }

    @Test
    public void whenRemoveOneElement() {
        SimpleArray<String> array = new SimpleArray<>(2);
        array.add("first");
        array.add("second");
        array.remove(0);
        String rsl = array.get(0);
        assertThat(rsl, is("second"));
    }

    @Test
    public void whenCorruptedIt() {
        SimpleArray<String> array = new SimpleArray<>(2);
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");
        it.next();
    }
}
