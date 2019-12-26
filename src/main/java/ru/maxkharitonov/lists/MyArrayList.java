package ru.maxkharitonov.lists;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MyArrayList<T> implements Iterable {
    private Object[] head;
    private int size;
    private final static int DEFAULT_LENGTH = 10;

    public MyArrayList () {
        this.head = new Object[DEFAULT_LENGTH];
    }

    public MyArrayList (Class<T> c, int s) {
        @SuppressWarnings("unchecked")
        final T[] head = (T[]) Array.newInstance(c, s);
        this.head = head;
        this.size = s;
    }

    private void extend() {
        head = Arrays.copyOf(this.head, this.size * 2);
    }

    private void isEnough() {
        if (size == head.length)
            extend();
    }

    private void selfCopy() {
        for(int i = size - 1; i >= 0; i--) {
            head[i + 1] = head[i];
        }
    }

    private void selfCopy(int start) {
        for(int i = size - 1; i >= start; i--) {
            head[i + 1] = head[i];
        }
    }

    private boolean isIndexInRange(int index) {
        return index < size;
    }

    public int getSize() {
        return (size);
    }

    public void add(T o) throws NullPointerException {
        if (o == null)
            throw new NullPointerException();
        addLast(o);
    }

    public void addLast(T o) throws NullPointerException {
        if (o == null)
            throw new NullPointerException();
        isEnough();
        head[size] = o;
        size++;
    }

    public void addFirst(T o) throws NullPointerException {
        if (o == null)
            throw new NullPointerException();
        if (size == 0) head[0] = o;
        else if (size < head.length) {
            selfCopy();
            head[0] = o;
        } else {
            Object[] newHead = new Object[size * 2];
            newHead[0] = o;
            System.arraycopy(head, 0, newHead, 1, size);
            head = newHead;
        }
        size++;
    }

    public void add(int index, T o) throws MyListIndexException, NullPointerException {
        if (o == null)
            throw new NullPointerException();
        if (index > size)
            throw new MyListIndexException();
        if (index == 0)
            addFirst(o);
        else if (index == size)
            addLast(o);
        else {
            if (size < head.length) {
                selfCopy(index);
                head[index] = o;
            } else {
                Object[] newHead = new Object[size * 2];
                System.arraycopy(head, 0, newHead, 0, index);
                newHead[index] = o;
                System.arraycopy(head, index, newHead, index + 1, size);
                head = newHead;
            }
        }
        size++;
    }

    public T get(int index) throws MyListIsEmptyException, MyListIndexException {
        if (size == 0)
            throw new MyListIsEmptyException();
        if (!isIndexInRange(index))
            throw new MyListIndexException();
        return (T) head[index];
    }

    public T getFirst() throws MyListIsEmptyException {
        if (size == 0)
            throw new MyListIsEmptyException();
        return (T) head[0];
    }

    public T getLast() throws MyListIsEmptyException {
        if (size == 0)
            throw new MyListIsEmptyException();
        return (T) head[size - 1];
    }

    public T removeLast() throws MyListIsEmptyException {
        T elem;

        if (size == 0)
            throw new MyListIsEmptyException();
        elem = (T) head[size - 1];
        head[size - 1] = null;
        size--;
        return elem;
    }

    public T removeFirst() throws MyListIsEmptyException {
        T elem;

        if (size == 0)
            throw new MyListIsEmptyException();
        elem = (T) head[0];
        System.arraycopy(head, 1, head, 0, size - 1);
        head[size - 1] = null;
        size--;
        return (elem);
    }

    public T removeAt(int index) throws MyListIsEmptyException, MyListIndexException {
        T elem;

        if (size == 0)
            throw new MyListIsEmptyException();
        if (!isIndexInRange(index))
            throw new MyListIndexException();
        if (index == 0)
            return removeFirst();
        else if (index == size)
            return removeLast();
        elem = (T) head[index];
        System.arraycopy(head, index + 1, head, index, size);
        head[size - 1] = null;
        size--;
        return (elem);
    }

    public T remove(Object o)
    throws MyListIsEmptyException, MyListElementNotFoundException, NullPointerException {
        if (size == 0)
            throw new MyListIsEmptyException();
        if (o == null)
            throw new NullPointerException();
        for (int i = 0; i < size; i++) {
            if (head[i].equals(o)) {
                try {
                    return removeAt(i);
                } catch (MyListIndexException e) {
                    e.printStackTrace();
                }
            }
        }
        throw new MyListElementNotFoundException();
    }

    public void clear() throws MyListIsEmptyException {
        while (size > 0) {
            removeFirst();
        }
    }

    public void addCollection(Collection<?> c) throws NullPointerException {
        if (c == null)
            throw new NullPointerException();
        Object[] a = c.toArray();
        int alen = a.length;
        if (alen != 0) {
            int i = 0;
            while (i < alen) {
                // need to add class check here?
                // in case of having <Integer> list and other type collection
                add((T) a[i]);
                i++;
            }
        }
    }

    public void removeCollection(Collection<?> c)
    throws MyListIsEmptyException, MyListElementNotFoundException, NullPointerException {
        if (c == null)
            throw new NullPointerException();
        Object[] a = c.toArray();
        int alen = a.length;
        if (alen != 0) {
            int i = 0;
            while (i < alen) {
                // need to add class check here?
                // in case of having <Integer> list and other type collection
                if (this.contains(a[i]))
                    remove(a[i]);
                i++;
            }
        }
    }

    public boolean contains(Object o) {
        if (o == null)
            throw new NullPointerException();
        MyArrayList<Object>.MyAListIter iter = new MyArrayList<Object>.MyAListIter();
        for (int i = 0; i < this.size; i++) {
            if (iter.next() == o)
                return (true);
        }
        return (false);
    }

    @Override
    public void forEach(Consumer action) {

    }

    @Override
    public Spliterator spliterator() {
        return null;
    }
    @Override
    public Iterator<T> iterator() {
        return new MyArrayList.MyAListIter();
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
            } catch (MyListIsEmptyException | MyListIndexException e) {
                throw new UnsupportedOperationException();
            }
        }
    }
}
