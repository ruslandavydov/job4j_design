package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArrayList<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;
    private int modCount;

    public SimpleArrayList() {
        if (size < 0) {
            throw new IllegalArgumentException();
        }
        array = new Object[DEFAULT_CAPACITY];
    }

    public void add(T model) {
        resizeIfNeeded();
        array[size] = model;
        size++;
        modCount++;
    }

    private void resizeIfNeeded() {
        if (array.length == size) {
            Objects[] newArray = new Objects[array.length + 1];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, size);
        array[index] = model;
        modCount++;
    }

    public void remove(int index) {
        Objects.checkIndex(index, size);
        System.arraycopy(array, index + 1, array, index, size - 1);
        size--;
        modCount++;
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
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return mark < size;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) array[mark++];
            }
        };
    }
}
