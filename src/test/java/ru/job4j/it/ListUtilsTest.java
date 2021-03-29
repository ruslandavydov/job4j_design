package ru.job4j.it;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 4));
        ListUtils.addAfter(input, 2, 3);
        assertThat(Arrays.asList(0, 1, 2, 3, 4), Is.is(input));
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(Arrays.asList(0, 1, 2, 3), Is.is(input));
    }

    @Test
    public void whenRemoveIfBiggerTwo() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        Predicate<Integer> predicate = x -> x > 2;
        ListUtils.removeIf(input, predicate);
        assertThat(Arrays.asList(0, 1, 2), Is.is(input));
    }

    @Test
    public void whenRemoveIfSmallerTwo() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        Predicate<Integer> predicate = x -> x < 2;
        ListUtils.removeIf(input, predicate);
        assertThat(Arrays.asList(2, 3, 4), Is.is(input));
    }

    @Test
    public void whenRemoveIfEqualsTwo() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        Predicate<Integer> predicate = x -> x == 2;
        ListUtils.removeIf(input, predicate);
        assertThat(Arrays.asList(0, 1, 3, 4), Is.is(input));
    }

    @Test
    public void whenRemoveIfEven() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        Predicate<Integer> predicate = x -> x % 2 == 0;
        ListUtils.removeIf(input, predicate);
        assertThat(Arrays.asList(1, 3), Is.is(input));
    }

    @Test
    public void whenReplaceEvenToFive() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        Predicate<Integer> predicate = x -> x % 2 == 0;
        ListUtils.replaceIf(input, predicate, 5);
        assertThat(Arrays.asList(5, 1, 5, 3, 5), Is.is(input));
    }

    @Test
    public void whenReplaceFiveToTwo() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 5, 3, 4));
        Predicate<Integer> predicate = x -> x == 5;
        ListUtils.replaceIf(input, predicate, 2);
        assertThat(Arrays.asList(0, 1, 2, 3, 4), Is.is(input));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        List<Integer> elements = new ArrayList<>(Arrays.asList(0, 3, 5));
        ListUtils.removeAll(input, elements);
        assertThat(Arrays.asList(1, 2, 4), Is.is(input));
    }

    @Test
    public void whenRemoveAllDouble() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        List<Integer> elements = new ArrayList<>(Arrays.asList(0, 3, 3, 0));
        ListUtils.removeAll(input, elements);
        assertThat(Arrays.asList(1, 2, 4, 5), Is.is(input));
    }
}
