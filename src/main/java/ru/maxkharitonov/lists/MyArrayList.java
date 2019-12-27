package ru.maxkharitonov.lists;

import java.util.Collection;
import java.util.Iterator;
import org.jetbrains.annotations.NotNull;

public class MyArrayList<T> implements Iterable<T> {
    private Object[] head;
    private int size;
    private final static int DEFAULT_LENGTH = 10;

    public MyArrayList () {
        this.head = new Object[DEFAULT_LENGTH];
    }

    public MyArrayList (@NotNull Collection<? extends T> c) {
        this.head = c.toArray();
        this.size = c.toArray().length;
    }

    private void extend() {
        Object[] newHead = new Object[size * 2];
        System.arraycopy(head, 0, newHead, 0, size);
        head = newHead;
    }

    private boolean isEnough() {
        return size < head.length;
    }

    private boolean isIndexInRange(int index) {
        return index < size;
    }

    public int getSize() {
        return size;
    }

    public void add(T o) throws NullPointerException {
        if (o == null) throw new NullPointerException();
        add(size, o);
    }

    public void add(int index, T o) throws IndexOutOfBoundsException, NullPointerException {
        if (o == null) throw new NullPointerException();
        if (index > size) throw new IndexOutOfBoundsException();
        else if (!isEnough()) extend();
        else {
            System.arraycopy(head, index, head, index + 1, size - index);
            head[index] = o;
        }
        size++;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) throws MyListIsEmptyException, IndexOutOfBoundsException {
        if (size == 0) throw new MyListIsEmptyException();
        if (!isIndexInRange(index)) throw new IndexOutOfBoundsException();
        return (T) head[index];
    }

    @SuppressWarnings("unchecked")
    public T removeAt(int index) throws MyListIsEmptyException, IndexOutOfBoundsException {
        T elem;

        if (size == 0) throw new MyListIsEmptyException();
        if (!isIndexInRange(index)) throw new IndexOutOfBoundsException();
        elem = (T) head[index];
        System.arraycopy(head, index + 1, head, index, size);
        head[size - 1] = null;
        size--;
        return (elem);
    }

    public T remove(Object o)
    throws MyListIsEmptyException,  NullPointerException {
        if (size == 0) throw new MyListIsEmptyException();
        if (o == null) throw new NullPointerException();
        for (int i = 0; i < size; i++) {
            if (head[i].equals(o)) return removeAt(i);
        }
        throw new MyListElementNotFoundException();
    }

    public void clear() throws MyListIsEmptyException {
        head = new Object[DEFAULT_LENGTH];
        size = 0;
    }

    public void addCollection(Collection<?> c) throws NullPointerException {
        if (c == null) throw new NullPointerException();
        T[] a = (T[]) c.toArray();
        if (a.length != 0 && (a.length + size) < head.length) {
            System.arraycopy(a, 0, head, size, a.length);
        } else if (a.length != 0) {
            Object[] newHead = new Object[size + a.length + 10];
            System.arraycopy(head, 0, newHead, 0, size);
            System.arraycopy(a, 0, newHead, size, a.length);
            head = newHead;
        }
        size += a.length;
    }

    public void removeCollection(Collection<?> c)
    throws MyListIsEmptyException, MyListElementNotFoundException, NullPointerException {
        if (c == null) throw new NullPointerException();
        Object[] a = c.toArray();
        if (a.length != 0) {
            for (int i = 0; i < a.length; i++) {
                if (this.contains(a[i])) remove(a[i]);
            }
        }
    }

    public boolean contains(Object o) {
        if (o == null)
            throw new NullPointerException();
        for (int i = 0; i < this.size; i++) {
            if (head[i] == o) return (true);
        }
        return (false);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyAListIter();
    }

    class MyAListIter implements Iterator<T> {
        private int currentIndex;

        public MyAListIter() {
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            if (!hasNext())
                return null;
            int returnIndex = currentIndex;
            currentIndex++;
            return (T) head[returnIndex];
        }

        @Override
        public void remove() {
            try {
                removeAt(currentIndex);
            } catch (MyListIsEmptyException | IndexOutOfBoundsException e) {
                throw new UnsupportedOperationException();
            }
        }
    }
}
