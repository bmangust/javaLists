package ru.maxkharitonov.lists;

public class MyListIsEmptyException extends Exception {
    String body = "MyListException occurred";
    String s;

    MyListIsEmptyException() {
        s = "list is empty";
    }

    public String toString() {
        if (s != null)
            return (body + ": " + s);
        else
            return (body);
    }
}
