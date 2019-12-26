package ru.maxkharitonov.lists;

public class MyListIndexException extends RuntimeException {
    String body = "MyListException occurred";
    String s;

    MyListIndexException() {
        s = "index is out of range";
    }

    public String toString() {
        if (s != null)
            return (body + ": " + s);
        else
            return (body);
    }
}
