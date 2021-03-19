package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public class MemStore<T extends Base> implements Store<T> {
    private final List<T> mem = new ArrayList<>();

    private int findIndex(String id) {
        int index = -1;
        for (int i = 0; i < mem.size(); i++) {
            if (mem.get(i).getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean rsl = false;
        int index = findIndex(id);
        if (index >= 0) {
            mem.set(index, model);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        boolean rsl = false;
        int index = findIndex(id);
        if (index >= 0) {
            mem.remove(index);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public T findById(String id) {
        int index = findIndex(id);
        return index >= 0 ? mem.get(index) : null;
    }
}
