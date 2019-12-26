package ru.maxkharitonov.lists;

public class MyListElementNotFoundException extends RuntimeException {
    String body = "MyListException occurred";
    String s;

    MyListElementNotFoundException() {
        s = "element not found";
    }

    public String toString() {
        if (s != null)
            return (body + ": " + s);
        else
            return (body);
    }
}
