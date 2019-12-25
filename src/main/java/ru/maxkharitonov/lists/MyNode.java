package ru.maxkharitonov.lists;

public class MyNode<T> {
    private T value;
    private MyNode<T> next;

    public MyNode() {
        this.value = null;
        this.next = null;
    }

    public MyNode(T val) {
        this.value = val;
        this.next = null;
    }
    public T getValue() {
        return (value);
    }

    public void setValue(T val) {
        this.value = val;
    }

    public void updateLink(MyNode<T> addr) {
        this.next = addr;
    }

    public MyNode<T> getNextNode() {
        return (next);
    }

}
