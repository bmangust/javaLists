package ru.maxkharitonov.lists;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MyLinkedListTest {

    private ArrayList<Integer> a;
    private MyLinkedList<Integer> l;
    private ArrayList <String> s;
    private MyLinkedList <String> ls;

    @Before
    private void init() {
        a = new ArrayList<Integer>();
        l = new MyLinkedList<Integer>();

        s = new ArrayList<String>();
        ls = new MyLinkedList<String>();
    }

    @After
    private void afterTest() {
        a = null;
        l = null;
        s = null;
        ls = null;
    }

    @Test
    public void add() {
        for (int i = 0; i < 5; i++) {
            a.add(i);
            l.add(i);
        }
        MyLinkedList.MyLListIter liter = l.new MyLListIter();
        for (Integer elem:a) assertEquals(elem, liter.next());


        for (int i = 0; i < 5; i++) {
            try {
                Assert.assertEquals(a.get(i), l.get(i));
            } catch (MyListIsEmptyException | MyListIndexException e) {
                e.printStackTrace();
            }
        }

        String m = "row ";
        for (int i = 0; i < 5; i++) {
            s.add(m + i);
            ls.add(m + i);
        }
        for (int i = 0; i < 5; i++) {
            try {
                Assert.assertEquals(s.get(i), ls.get(i));
            } catch (MyListIsEmptyException | MyListIndexException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void addLast() {
        for (int i = 0; i < 5; i++) {
            a.add(i);
            l.addLast(i);
        }
        for (int i = 0; i < 5; i++) {
            try {
                Assert.assertEquals(a.get(i), l.get(i));
            } catch (MyListIsEmptyException | MyListIndexException e) {
                e.printStackTrace();
            }
        }

        String m = "row ";
        for (int i = 0; i < 5; i++) {
            s.add(m + i);
            ls.addLast(m + i);
        }
        for (int i = 0; i < 5; i++) {
            try {
                Assert.assertEquals(s.get(i), ls.get(i));
            } catch (MyListIsEmptyException | MyListIndexException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void addFirst() {
        for (int i = 0; i < 5; i++) {
            a.add(0, i);
            l.addFirst(i);
        }
        for (int i = 0; i < 5; i++) {
            try {
                Assert.assertEquals(a.get(i), l.get(i));
            } catch (MyListIsEmptyException | MyListIndexException e) {
                e.printStackTrace();
            }
        }

        String m = "row ";
        for (int i = 0; i < 5; i++) {
            s.add(0, m + i);
            ls.addFirst(m + i);
        }
        for (int i = 0; i < 5; i++) {
            try {
                Assert.assertEquals(s.get(i), ls.get(i));
            } catch (MyListIsEmptyException | MyListIndexException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void get() {
        for (int i = 0; i < 5; i++) {
            a.add(i);
            l.add(i);
        }
        for (int i = 0; i < 5; i++) {
            try {
                Assert.assertEquals(a.get(i), l.get(i));
            } catch (MyListIsEmptyException | MyListIndexException e) {
                e.printStackTrace();
            }
        }

        String m = "row ";
        for (int i = 0; i < 5; i++) {
            s.add(m + i);
            ls.add(m + i);
        }
        for (int i = 0; i < 5; i++) {
            try {
                Assert.assertEquals(s.get(i), ls.get(i));
            } catch (MyListIsEmptyException | MyListIndexException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void getFirst() {
        for (int i = 0; i < 2; i++) {
            a.add(i);
            l.add(i);
        }
        try {
            Assert.assertEquals(a.get(0), l.getFirst());
        } catch (MyListIsEmptyException e) {
            e.printStackTrace();
        }

        String m = "row ";
        for (int i = 0; i < 2; i++) {
            s.add(m + i);
            ls.add(m + i);
        }
        try {
            Assert.assertEquals(s.get(0), ls.getFirst());
        } catch (MyListIsEmptyException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getLast() {
        for (int i = 0; i < 2; i++) {
            a.add(i);
            l.add(i);
        }
        try {
            Assert.assertEquals(a.get(0), l.getFirst());
        } catch (MyListIsEmptyException e) {
            e.printStackTrace();
        }

        String m = "row ";
        for (int i = 0; i < 2; i++) {
            s.add(m + i);
            ls.add(m + i);
        }
        try {
            Assert.assertEquals(s.get(0), ls.getFirst());
        } catch (MyListIsEmptyException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void removeLast() {
        for (int i = 0; i < 5; i++) {
            a.add(i);
            l.add(i);
        }
        for (int i = 0; i < 5; i++) {
            try {
                Assert.assertEquals(a.remove(a.size()), l.removeLast());
            } catch (MyListIsEmptyException e) {
                e.printStackTrace();
            }
        }

        String m = "row ";
        for (int i = 0; i < 5; i++) {
            s.add(m + i);
            ls.add(m + i);
        }
        for (int i = 0; i < 5; i++) {
            try {
                Assert.assertEquals(s.remove(s.size()), ls.removeLast());
            } catch (MyListIsEmptyException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void removeFirst() {
        for (int i = 0; i < 5; i++) {
            a.add(i);
            l.add(i);
        }
        for (int i = 0; i < 5; i++) {
            try {
                Assert.assertEquals(a.remove(a.size()), l.removeLast());
            } catch (MyListIsEmptyException e) {
                e.printStackTrace();
            }
        }

        String m = "row ";
        for (int i = 0; i < 5; i++) {
            s.add(m + i);
            ls.add(m + i);
        }
        try {
            Assert.assertEquals(s.remove(0), ls.removeFirst());
        } catch (MyListIsEmptyException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void removeAt() {
        for (int i = 0; i < 5; i++) {
            a.add(i);
            l.add(i);
        }
        try {
            Assert.assertEquals(a.remove(4), l.removeAt(4));
            Assert.assertEquals(a.remove(2), l.removeAt(2));
        } catch (MyListIsEmptyException | MyListIndexException e) {
            e.printStackTrace();
        }

        String m = "row ";
        for (int i = 0; i < 2; i++) {
            s.add(m + i);
            ls.add(m + i);
        }
        try {
            Assert.assertEquals(s.remove(4), ls.removeAt(4));
            Assert.assertEquals(s.remove(2), ls.removeAt(2));
        } catch (MyListIsEmptyException | MyListIndexException e) {
            e.printStackTrace();
        }
    }
}