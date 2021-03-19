package ru.job4j.generics;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] array;
    private int size;

    public SimpleArray(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
        array = new Object[size];
    }

    public void add(T model) {
        array[size] = model;
        size++;
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, size);
        array[index] = model;
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        System.arraycopy(array, index + 1, array, index, size - 1);
        size--;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int mark;

            @Override
            public boolean hasNext() {
                return mark < size;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                return (T) array[mark++];
            }
        };
    }
}
