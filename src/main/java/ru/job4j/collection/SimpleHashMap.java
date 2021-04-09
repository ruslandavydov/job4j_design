package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleHashMap<K, V> implements Iterable {
   private Node<K, V>[] table;
   private static final int DEFAULT_INITIAL_CAPACITY = 16;
   private static final double LOAD_FACTOR = 0.75;
   private int size = 0;
   private int capacity;
   private int modCount;

   public SimpleHashMap() {
      this.capacity = DEFAULT_INITIAL_CAPACITY;
      table = new Node[capacity];
   }

   public boolean insert(K key, V value) {
      boolean result = true;
      Node<K, V> e;
      if (size >= LOAD_FACTOR * capacity) {
         resize();
      }
      int i = getIndex(key);
      if (table[i] == null) {
         table[i] = new Node<>(key.hashCode(), key, value, null);
         size++;
         modCount++;
      } else {
         e = table[i];
         if (e.hash == key.hashCode() && (e.key == key || e.key.equals(key))) {
            e.value = value;
            modCount++;
         } else {
            result = false;
         }
      }
      return result;
   }

   public V get(K key) {
      V result = null;
      int i = getIndex(key);
      Node<K, V> e = table[i];
      if (e != null) {
         if (e.hash == key.hashCode() && (e.key == key || e.key.equals(key))) {
            result = e.value;
         }
      }
      return result;
   }

   public boolean delete(K key) {
      boolean result = false;
      int i = getIndex(key);
      Node<K, V> e = table[i];
      if (e != null && e.hash == key.hashCode() && (e.key == key || e.key.equals(key))) {
         table[i] = null;
         result = true;
         size--;
         modCount++;
      }
      return result;
   }

   private int getIndex(K key) {
      return key.hashCode() & (capacity - 1);
   }

   public int getSize() {
      return size;
   }

   public int getLength() {
      return table.length;
   }

   private void resize() {
      capacity *= 2;
      size = 0;
      Node<K, V>[] oldTable = table;
      table = new Node[capacity];
      for (Node<K, V> e : oldTable) {
         if (e != null) {
            insert(e.key, e.value);
         }
      }
   }

   static class Node<K, V> {
      final int hash;
      final K key;
      V value;
      Node<K, V> next;

      public Node(int hash, K key, V value, Node<K, V> next) {
         this.hash = hash;
         this.key = key;
         this.value = value;
         this.next = next;
      }

      public V getValue() {
         return value;
      }
   }

   @Override
   public Iterator iterator() {
      return new Iterator() {
         private int index;
         Node<K, V> e = table[index];
         private int expectedModCount = modCount;

         @Override
         public boolean hasNext() {
            while ((index < (capacity - 1)) && e == null) {
               index++;
               e = table[index];
            }
            return e != null;
         }

         @Override
         public Object next() {
            if (!hasNext()) {
               throw new NoSuchElementException();
            }
            if (expectedModCount != modCount) {
               throw new ConcurrentModificationException();
            }
            Node<K, V> result = e;
            if (e.next != null) {
               e = e.next;
            } else if (index < capacity - 1) {
               index++;
               e = table[index];
            }
            return result;
         }
      };
   }
}
