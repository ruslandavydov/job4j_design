package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator<Integer> {
    private final int[] numbers;
    private int count = 0;

    public EvenIt(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        int nextCount = -1;
        for (int i = count; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                count = i;
                nextCount++;
                break;
            }
        }
        return nextCount == 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return numbers[count++];
    }
}
