package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private static class Node<T> {
        T element;
        Node<T> next;

        public Node(T element) {
            this.element = element;
        }
    }

    private Node<T> head;
    public int size = 0;

    public void add(T element) {
        Node<T> node = new Node<T>(element);
        if (head == null) {
            head = node;
            size++;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        size++;
    }

    public void addFirst(T element) {
        Node<T> node = new Node<T>(element);
        if (head == null) {
            head = node;
            size++;
            return;
        }
        node.next = head;
        head = node;
        size++;
    }

    public T deleteFirst() {
        if (head != null) {
            T result = head.element;
            head = head.next;
            size--;
            return result;
        }
        throw new NoSuchElementException();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T element = node.element;
                node = node.next;
                return element;
            }
        };
    }
}
