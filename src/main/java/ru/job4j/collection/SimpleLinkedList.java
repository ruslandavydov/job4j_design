package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }

    private Node<E> first;
    private Node<E> last;
    private int size;
    private int modCount;

    public void add(E element) {
        Node<E> newNode = new Node<>(element);
        if (first == null) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        size++;
        modCount++;
    }

    public Node<E> getNodeByIndex(int index) {
        Objects.checkIndex(index, size);
        Node<E> current = first;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    public E get(int index) {
        Objects.checkIndex(index, size);
        return getNodeByIndex(index).element;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int mark;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return mark < size;
            }

            @Override
            public E next() {
                E result = first.element;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (mark != 0) {
                    result = first.next.element;
                }
                mark++;
                return result;
            }
        };
    }
}
