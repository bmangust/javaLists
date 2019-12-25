package ru.maxkharitonov.lists;

import java.util.Collection;
import java.util.Iterator;

public class MyLinkedList<T> implements Iterable{
    private MyNode<T> head;
    private int size;

    public MyLinkedList () {
        head = null;
        size = 0;
    }

    public MyLinkedList (T o) {
        addLast(o);
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

    public void add(int index, T o) throws MyListIndexException, NullPointerException {
        MyNode<T> node = new MyNode<T>();
        node.setValue(o);

        if (!IsIndexInRange(index))
            throw new MyListIndexException();
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

    public T get(int index) throws MyListIsEmptyException, MyListIndexException {
        MyNode<T> tmp = head;
        int i = 0;

        if (head == null)
            throw new MyListIsEmptyException();
        if (!IsIndexInRange(index))
            throw new MyListIndexException();
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
            return elem;
        }
        while (cur.getNextNode() != null) {
            prev = cur;
            cur = cur.getNextNode();
        }
        elem = cur.getValue();
        prev.updateLink(null);
        return (elem);
    }

    public T removeFirst() throws MyListIsEmptyException {
        T elem;
        MyNode<T> cur = head;

        if (head == null)
            throw new MyListIsEmptyException();
        elem = cur.getValue();
        head = cur.getNextNode();
        return (elem);
    }

    public T removeAt(int index) throws MyListIsEmptyException, MyListIndexException {
        T elem;
        int i = 0;
        MyNode<T> cur = head;
        MyNode<T> prev = head;

        if (!IsIndexInRange(index))
            throw new MyListIndexException();
        if (head == null)
            throw new MyListIsEmptyException();
        if (index == 0) {
            elem = cur.getValue();
            head = cur.getNextNode();
            return (elem);
        }
        while (i != index) {
            prev = cur;
            cur = cur.getNextNode();
            i++;
        }
        elem = cur.getValue();
        prev.updateLink(cur.getNextNode());
        return (elem);
    }

    public void clear() throws MyListIsEmptyException {
        if (head == null)
            throw new MyListIsEmptyException();
        while (head != null) {
            removeFirst();
        }
    }

    public int getSize() {
        return (size);
    }

    public void addCollection(Collection c) {

    }


    @Override
    public Iterator iterator() {
        return new MyLListIter();
    }

    class MyLListIter implements Iterator<T> {
        private MyNode<T> listHead;
        private MyNode<T> current;
        private MyNode<T> next;
        private int currentIndex;

        public MyLListIter() {
            listHead = head;
            current = head;
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size && current.getNextNode() != null;
        }

        // how to return MyNode properly?
        @Override
        public T next() {
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
            } catch (MyListIsEmptyException | MyListIndexException e) {
//                e.printStackTrace();
                throw new UnsupportedOperationException();
            }
        }
    }
}
