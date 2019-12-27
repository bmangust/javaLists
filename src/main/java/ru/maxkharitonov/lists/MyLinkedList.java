package ru.maxkharitonov.lists;

import java.util.Collection;
import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T>{
    private MyNode<T> head;
    private int size;

    public MyLinkedList () {
        head = null;
        size = 0;
    }

    private boolean IsIndexInRange(int index) {
        return index < size;
    }

    public void add(T o) {
        addLast(o);
    }

    public void addLast(T o) {
        MyNode<T> node = new MyNode<T>();
        node.setValue(o);
        MyNode<T> tmp = head;

        if (head == null) {
            head = node;
        } else {
            while (tmp.getNextNode() != null) {
                tmp = tmp.getNextNode();
            }
            tmp.updateLink(node);
        }
        size++;
    }

    public void addFirst(T o) {
        MyNode<T> node = new MyNode<T>();
        node.setValue(o);

        if (head != null) {
            node.updateLink(head);
        }
        head = node;
        size++;
    }

    public void add(int index, T o) throws IndexOutOfBoundsException, NullPointerException {
        MyNode<T> node = new MyNode<T>();
        node.setValue(o);

        if (index > size)
            throw new IndexOutOfBoundsException();
        if (o == null)
            throw new NullPointerException();
        if (head == null) {
            head = node;
        } else if (index == 0) {
            node.updateLink(head);
            head = node;
        } else {
            MyNode<T> current = head;
            int i = 0;
            while (i != (index - 1)) {
                current = current.getNextNode();
                ++i;
            }
            node.updateLink(current.getNextNode());
            current.updateLink(node);
        }
        size++;
    }

    public T get(int index) throws MyListIsEmptyException, IndexOutOfBoundsException {
        MyNode<T> tmp = head;
        int i = 0;

        if (head == null)
            throw new MyListIsEmptyException();
        if (!IsIndexInRange(index))
            throw new IndexOutOfBoundsException();
        while (i != index) {
            tmp = tmp.getNextNode();
            ++i;
        }
        return (tmp.getValue());
    }

    public T getFirst() throws MyListIsEmptyException {
        if (head == null)
            throw new MyListIsEmptyException();
        return (head.getValue());
    }

    public T getLast() throws MyListIsEmptyException {
        if (head == null)
            throw new MyListIsEmptyException();
        MyNode<T> tmp = head;
        while (tmp.getNextNode() != null) {
            tmp = tmp.getNextNode();
        }
        return (tmp.getValue());
    }

    public T removeLast() throws MyListIsEmptyException {
        T elem;
        MyNode<T> cur = head;
        MyNode<T> prev = head;

        if (head == null)
            throw new MyListIsEmptyException();
        if (size == 1) {
            elem = head.getValue();
            head = null;
            size--;
            return elem;
        }
        while (cur.getNextNode() != null) {
            prev = cur;
            cur = cur.getNextNode();
        }
        elem = cur.getValue();
        prev.updateLink(null);
        size--;
        return (elem);
    }

    public T removeFirst() throws MyListIsEmptyException {
        T elem;
        MyNode<T> cur = head;

        if (head == null)
            throw new MyListIsEmptyException();
        elem = cur.getValue();
        head = cur.getNextNode();
        size--;
        return (elem);
    }

    public T removeAt(int index) throws MyListIsEmptyException, IndexOutOfBoundsException {
        T elem;
        int i = 0;
        MyNode<T> cur = head;
        MyNode<T> prev = head;

        if (head == null)
            throw new MyListIsEmptyException();
        if (!IsIndexInRange(index))
            throw new IndexOutOfBoundsException();
        if (index == 0) {
            elem = cur.getValue();
            head = cur.getNextNode();
            size--;
            return (elem);
        }
        while (i != index) {
            prev = cur;
            cur = cur.getNextNode();
            i++;
        }
        elem = cur.getValue();
        prev.updateLink(cur.getNextNode());
        size--;
        return (elem);
    }

    public T remove(Object o) throws MyListIsEmptyException, MyListElementNotFoundException, NullPointerException {
        T elem;
        int i = 0;
        MyNode<T> cur = head;
        MyNode<T> prev;

        if (head == null)
            throw new MyListIsEmptyException();
        if (o == null)
            throw new NullPointerException();
        if (head.getValue().equals(o)) {
            elem = head.getValue();
            head.updateLink(head.getNextNode());
            size--;
            return (elem);
        }
        while (i < size) {
            prev = cur;
            cur = cur.getNextNode();
            i++;
            if (cur != null && cur.getValue().equals(o)) {
                elem = cur.getValue();
                prev.updateLink(cur.getNextNode());
                size--;
                return (elem);
            }
        }
        throw new MyListElementNotFoundException();
    }

    public void clear() throws MyListIsEmptyException {
        while (head != null) {
            removeFirst();
        }
    }

    public int getSize() {
        return (size);
    }

    public void addCollection(Collection<?> c) throws NullPointerException {
        if (c == null)
            throw new NullPointerException();
        Object[] a = c.toArray();
        if (a.length != 0) {
            int i = 0;
            while (i < a.length) {
                add((T) a[i]);
                i++;
            }
        }
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
        MyLinkedList<Object>.MyLListIter iter = new MyLinkedList<Object>.MyLListIter();
        for (int i = 0; i < this.size; i++) {
            if (iter.next() == o)
                return (true);
        }
        return (false);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyLListIter();
    }

    class MyLListIter implements Iterator<T> {
        private MyNode<T> current;
        private int currentIndex;

        public MyLListIter() {
            current = head;
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size && current.getNextNode() != null;
        }

        @Override
        public T next() {
            if (current == null)
                return (null);
            MyNode<T> result = current;
            current = current.getNextNode();
            currentIndex++;
            return result.getValue();
        }

        @Override
        public void remove() {
            current = current.getNextNode();
            try {
                removeAt(currentIndex);
            } catch (MyListIsEmptyException | IndexOutOfBoundsException e) {
                throw new UnsupportedOperationException();
            }
        }
    }
}
