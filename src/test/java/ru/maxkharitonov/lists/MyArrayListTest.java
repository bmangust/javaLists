package ru.maxkharitonov.lists;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class MyArrayListTest {

    public static final int TEST_SIZE = 5;
    private ArrayList<Integer> a;
    private MyArrayList<Integer> l;
    private ArrayList <String> s;
    private MyArrayList <String> ls;

    @Before
    public void init() {
        a = new ArrayList<Integer>();
        l = new MyArrayList<Integer>();
        s = new ArrayList<String>();
        ls = new MyArrayList<String>();
    }

    @After
    public void afterTest() {
        a = null;
        l = null;
        s = null;
        ls = null;
    }

    // not sure how to test this
//    @Test
//    public void MyArrayList() {
//        MyArrayList<String> nl = new MyArrayList<Class<String>, 5>;
//
//    }

    @Test
    public void getSize() {
        String m = "row ";
        for (int i = 0; i < 5; i++) {
            l.add(i);
            ls.add(m + i);
        }
        assertEquals(5, l.getSize());
        assertEquals(5, ls.getSize());
    }

    @Test
    public void add() {
        String m = "row ";
        for (int i = 0; i < TEST_SIZE; i++) {
            a.add(i);
            l.add(i);
            s.add(m + i);
            ls.add(m + i);
        }
        MyArrayList<Integer>.MyAListIter liter = l.new MyAListIter();
        MyArrayList<String>.MyAListIter lsiter = ls.new MyAListIter();
        for (Integer elem:a) assertEquals(elem, liter.next());
        for (String elem:s) assertEquals(elem, lsiter.next());
    }

    @Test
    public void add2() {
        String m = "row ";
        for (int i = 0; i < TEST_SIZE; i++) {
            a.add(i);
            l.add(i);
            s.add(m + i);
            ls.add(m + i);
        }
        a.add(3, 10);
        s.add(3, m + 10);
        try {
            ls.add(9, m + 10);
        } catch (MyListIndexException e) {
            assertEquals("MyListException occurred: index is out of range", e.toString());
        }
        try {
            l.add(TEST_SIZE, 10);
        } catch (MyListIndexException e) {
            assertEquals("MyListException occurred: index is out of range", e.toString());
        }
        try {
            l.add(3, 10);
            ls.add(3, m + 10);
        } catch (MyListIndexException e) {
            fail();
        }
        assertEquals(a.get(3), l.get(3));
        assertEquals(s.get(3), ls.get(3));
    }

    @Test
    public void addLast() {
        String m = "row ";
        for (int i = 0; i < TEST_SIZE; i++) {
            s.add(m + i);
            ls.addLast(m + i);
            a.add(i);
            l.addLast(i);
        }
        for (int i = 0; i < TEST_SIZE; i++) {
            assertEquals(a.get(i), l.get(i));
            assertEquals(s.get(i), ls.get(i));
        }
    }

    @Test
    public void addFirst() {
        String m = "row ";
        for (int i = 0; i < TEST_SIZE; i++) {
            a.add(0, i);
            l.addFirst(i);
            s.add(0, m + i);
            ls.addFirst(m + i);
        }
        for (int i = 0; i < TEST_SIZE; i++) {
            assertEquals(a.get(i), l.get(i));
            assertEquals(s.get(i), ls.get(i));
        }
    }

    @Test
    public void get() {
        try {
            l.get(2);
        } catch (MyListIsEmptyException e) {
            assertEquals("MyListException occurred: list is empty", e.toString());
        } catch (MyListIndexException e) {
            fail();
        }
        try {
            ls.get(2);
        } catch (MyListIsEmptyException e) {
            assertEquals("MyListException occurred: list is empty", e.toString());
        } catch (MyListIndexException e) {
            fail();
        }
        String m = "row ";
        for (int i = 0; i < TEST_SIZE; i++) {
            s.add(m + i);
            ls.add(m + i);
            a.add(i);
            l.add(i);
        }
        for (int i = 0; i < TEST_SIZE; i++) {
            assertEquals(a.get(i), l.get(i));
            assertEquals(s.get(i), ls.get(i));
        }
        try {
            l.get(7);
        } catch (MyListIsEmptyException e) {
            fail();
        } catch (MyListIndexException e) {
            assertEquals("MyListException occurred: index is out of range", e.toString());
        }
        try {
            ls.get(7);
        } catch (MyListIsEmptyException e) {
            fail();
        } catch (MyListIndexException e) {
            assertEquals("MyListException occurred: index is out of range", e.toString());
        }
    }

    @Test
    public void getFirst() {
        try {
            l.getFirst();
        } catch (MyListIsEmptyException e) {
            assertEquals("MyListException occurred: list is empty", e.toString());
        }
        try {
            ls.getFirst();
        } catch (MyListIsEmptyException e) {
            assertEquals("MyListException occurred: list is empty", e.toString());
        }
        String m = "row ";
        for (int i = 0; i < 2; i++) {
            s.add(m + i);
            ls.add(m + i);
            a.add(i);
            l.add(i);
        }
        assertEquals(a.get(0), l.getFirst());
        assertEquals(s.get(0), ls.getFirst());
    }

    @Test
    public void getLast() {
        try {
            l.getLast();
        } catch (MyListIsEmptyException e) {
            assertEquals("MyListException occurred: list is empty", e.toString());
        }
        try {
            ls.getLast();
        } catch (MyListIsEmptyException e) {
            assertEquals("MyListException occurred: list is empty", e.toString());
        }
        String m = "row ";
        for (int i = 0; i < 2; i++) {
            s.add(m + i);
            ls.add(m + i);
            a.add(i);
            l.add(i);
        }
        assertEquals(a.get(a.size() - 1), l.getLast());
        assertEquals(s.get(a.size() - 1), ls.getLast());
    }

    @Test
    public void removeLast() {
        String m = "row ";
        try {
            l.removeLast();
        } catch (MyListIsEmptyException e) {
            assertEquals("MyListException occurred: list is empty", e.toString());
        }
        try {
            ls.removeLast();
        } catch (MyListIsEmptyException e) {
            assertEquals("MyListException occurred: list is empty", e.toString());
        }
        for (int i = 0; i < TEST_SIZE; i++) {
            a.add(i);
            l.add(i);
            s.add(m + i);
            ls.add(m + i);
        }
        for (int i = 0; i < TEST_SIZE; i++) {
            assertEquals(a.remove(a.size() - 1), l.removeLast());
            assertEquals(s.remove(s.size() - 1), ls.removeLast());
        }
    }

    @Test
    public void removeFirst() {
        String m = "row ";
        try {
            l.removeFirst();
        } catch (MyListIsEmptyException e) {
            assertEquals("MyListException occurred: list is empty", e.toString());
        }
        try {
            ls.removeFirst();
        } catch (MyListIsEmptyException e) {
            assertEquals("MyListException occurred: list is empty", e.toString());
        }
        for (int i = 0; i < TEST_SIZE; i++) {
            a.add(i);
            l.add(i);
            s.add(m + i);
            ls.add(m + i);
        }
        assertEquals(a.remove(0), l.removeFirst());
        assertEquals(s.remove(0), ls.removeFirst());
        assertEquals(TEST_SIZE - 1, l.getSize());
        assertEquals(TEST_SIZE - 1, ls.getSize());
        assertEquals(a.get(a.size() - 1), l.getLast());
        assertEquals(s.get(s.size() - 1), ls.getLast());
        for (int i = 0; i < TEST_SIZE - 1; i++) {
            assertEquals(a.remove(0), l.removeFirst());
            assertEquals(TEST_SIZE - 2 - i, l.getSize());
            assertEquals(s.remove(0), ls.removeFirst());
            assertEquals(TEST_SIZE - 2 - i, ls.getSize());
        }
    }

    @Test
    public void removeAt() {
        try {
            l.removeAt(0);
        } catch (MyListIsEmptyException e) {
            assertEquals("MyListException occurred: list is empty", e.toString());
        } catch (MyListIndexException e) {
            fail();
        }
        try {
            ls.removeAt(5);
        } catch (MyListIsEmptyException e) {
            assertEquals("MyListException occurred: list is empty", e.toString());
        } catch (MyListIndexException e) {
            fail();
        }
        String m = "row ";
        for (int i = 0; i < 5; i++) {
            a.add(i);
            l.add(i);
            s.add(m + i);
            ls.add(m + i);
        }
        try {
            l.removeAt(5);
        } catch (MyListIsEmptyException e) {
            fail();
        } catch (MyListIndexException e) {
            assertEquals("MyListException occurred: index is out of range", e.toString());
        }
        try {
            ls.removeAt(5);
        } catch (MyListIsEmptyException e) {
            fail();
        } catch (MyListIndexException e) {
            assertEquals("MyListException occurred: index is out of range", e.toString());
        }
        assertEquals(a.remove(4), l.removeAt(4));
        assertEquals(a.remove(2), l.removeAt(2));
        assertEquals(a.remove(0), l.removeAt(0));
        assertEquals(s.remove(4), ls.removeAt(4));
        assertEquals(s.remove(2), ls.removeAt(2));
        assertEquals(s.remove(0), ls.removeAt(0));
    }

    @Test
    public void remove() {
        try {
            l.remove(0);
        } catch (MyListIsEmptyException e) {
            assertEquals("MyListException occurred: list is empty", e.toString());
        } catch (MyListElementNotFoundException | NullPointerException e) {
            fail();
        }
        try {
            ls.remove("row 0");
        } catch (MyListIsEmptyException e) {
            assertEquals("MyListException occurred: list is empty", e.toString());
        } catch (MyListElementNotFoundException | NullPointerException e) {
            fail();
        }
        String m = "row ";
        for (int i = 0; i < 5; i++) {
            a.add(i);
            l.add(i);
            s.add(m + i);
            ls.add(m + i);
        }
        assertEquals(4, (int) l.remove(4));
        assertEquals(0, (int) l.remove(0));
        assertEquals("row 1", ls.remove("row 1"));
        assertEquals("row 3", ls.remove("row 3"));
        try {
            assertEquals(0, (int) l.remove(0));
        } catch (MyListElementNotFoundException e) {
            assertEquals("MyListException occurred: element not found", e.toString());
        }
        try {
            assertNotEquals(-4, (int) l.remove(-4));
        } catch (MyListElementNotFoundException | MyListIsEmptyException e) {
            assertEquals("MyListException occurred: element not found", e.toString());
        }
        try {
            assertNull(l.remove(null));
        } catch (NullPointerException e) {
            assertNotEquals("", e.getMessage());
        } catch (MyListIsEmptyException | MyListElementNotFoundException e) {
            fail();
        }
        try {
            assertNull(ls.remove("row 1"));
        } catch (MyListElementNotFoundException e) {
            assertEquals("MyListException occurred: element not found", e.toString());
        }
        try {
            assertNull(ls.remove("row 5"));
        } catch (MyListElementNotFoundException e) {
            assertEquals("MyListException occurred: element not found", e.toString());
        }
        try {
            assertNull(ls.remove(null));
        } catch (NullPointerException e) {
            assertNotEquals("", e.getMessage());
        } catch (MyListIsEmptyException | MyListElementNotFoundException e) {
            fail();
        }
    }

    @Test
    public void clear() {
        String m = "row ";
        for (int i = 0; i < 5; i++) {
            l.add(i);
            ls.add(m + i);
        }
        try {
            l.clear();
            ls.clear();
        } catch (MyListIsEmptyException e) {
            fail();
        }
        assertEquals(0, l.getSize());
        assertEquals(0, ls.getSize());
        try {
            l.clear();
        } catch (MyListIsEmptyException e) {
            assertEquals("MyListException occurred: list is empty", e.toString());
        }
        try {
            ls.clear();
        } catch (MyListIsEmptyException e) {
            assertEquals("MyListException occurred: list is empty", e.toString());
        }
    }

    @Test
    public void addCollection() {
        int i = 0;
        List<Integer> list = Arrays.asList(9,8,7,6);
        List<String> slist = Arrays.asList("row 9","row 8","row 7","row 6");
        l.addCollection(list);
        ls.addCollection(slist);
        MyArrayList<Integer>.MyAListIter liter = l.new MyAListIter();
        MyArrayList<String>.MyAListIter lsiter = ls.new MyAListIter();
        while (i < list.size()) {
            assertEquals(list.get(i), liter.next());
            assertEquals(slist.get(i), lsiter.next());
            i++;
        }
        try {
            l.addCollection(null);
        } catch (NullPointerException e) {
            assertNotEquals("", e.getMessage());
        }
        try {
            ls.addCollection(null);
        } catch (NullPointerException e) {
            assertNotEquals("", e.getMessage());
        }
    }

    @Test
    public void removeCollection() {
        try {
            ls.removeCollection(Arrays.asList("row 1", "row 2"));
        } catch (MyListIsEmptyException e) {
            assertEquals("MyListException occurred: list is empty", e.toString());
        } catch (MyListElementNotFoundException e) {
            fail();
        }
        try {
            l.removeCollection(Arrays.asList(1, 2));
        } catch (MyListIsEmptyException e) {
            assertEquals("MyListException occurred: list is empty", e.toString());
        } catch (MyListElementNotFoundException e) {
            fail();
        }
        int i = 0;
        List<Integer> list = Arrays.asList(9,8,7,6);
        List<String> slist = Arrays.asList("row 9","row 8","row 7","row 6");
        List<Integer> rlist = Arrays.asList(8,6);
        List<String> rslist = Arrays.asList("row 8","row 6");
        l.addCollection(list);
        ls.addCollection(slist);
        try {
            ls.removeCollection(Arrays.asList("row 1", "row 2"));
        } catch (MyListIsEmptyException e) {
            fail();
        } catch (MyListElementNotFoundException e) {
            assertEquals("MyListException occurred: element not found", e.toString());
        }
        try {
            l.removeCollection(Arrays.asList(1, 2));
        } catch (MyListIsEmptyException e) {
            fail();
        } catch (MyListElementNotFoundException e) {
            assertEquals("MyListException occurred: element not found", e.toString());
        }
        try {
            l.removeCollection(rlist);
            ls.removeCollection(rslist);
        } catch (MyListIsEmptyException | MyListElementNotFoundException e) {
            fail();
        }
        assertEquals(9, (int) l.get(0));
        assertEquals(7, (int) l.get(1));
        assertEquals("row 9", ls.get(0));
        assertEquals("row 7", ls.get(1));
        assertNotEquals(8, (int) l.get(1));
        assertNotEquals("row 8", ls.get(1));
        try {
            l.removeCollection(null);
        } catch (NullPointerException e) {
            assertNotEquals("", e.getMessage());
        } catch (MyListIsEmptyException | MyListElementNotFoundException e) {
            fail();
        }
        try {
            ls.removeCollection(null);
        } catch (NullPointerException e) {
            assertNotEquals("", e.getMessage());
        } catch (MyListIsEmptyException | MyListElementNotFoundException e) {
            fail();
        }
    }

    @Test
    public void contains() {
        List<Integer> list = Arrays.asList(9,8,7,6);
        List<String> slist = Arrays.asList("row 9","row 8","row 7","row 6");
        l.addCollection(list);
        ls.addCollection(slist);
        assertTrue(l.contains(8));
        assertTrue(l.contains(6));
        assertTrue(l.contains(9));
        assertFalse(l.contains(10));
        assertFalse(l.contains(1));
        assertTrue(ls.contains("row 8"));
        assertTrue(ls.contains("row 7"));
        assertTrue(ls.contains("row 9"));
        assertFalse(ls.contains("row 10"));
        assertFalse(ls.contains("row 1"));
    }

    @Test
    public void iterator() {
        String m = "row ";
        for (int i = 0; i < TEST_SIZE; i++) {
            a.add(i);
            l.add(i);
            s.add(m + i);
            ls.add(m + i);
        }
        MyArrayList<Integer>.MyAListIter liter = l.new MyAListIter();
        MyArrayList<String>.MyAListIter lsiter = ls.new MyAListIter();
        for (Integer elem : a) assertEquals(elem, liter.next());
        for (String elem : s) assertEquals(elem, lsiter.next());
        int i = 0;
        for (Object item : l) {
            assertEquals(i, item);
            i++;
        }
        i = 0;
        for (Object item : ls) {
            assertEquals(m + i, item);
            i++;
        }
    }

    @Test
    public void iter_remove() {
        String m = "row ";
        for (int i = 0; i < TEST_SIZE; i++) {
            a.add(i);
            l.add(i);
            s.add(m + i);
            ls.add(m + i);
        }
        MyArrayList<Integer>.MyAListIter liter = l.new MyAListIter();
        MyArrayList<String>.MyAListIter lsiter = ls.new MyAListIter();
        for (int i = 0; i < TEST_SIZE - 1; i++) {
            liter.remove();
            a.remove(0);
            assertEquals(a.get(0), l.getFirst());
            lsiter.remove();
            s.remove(0);
            assertEquals(s.get(0), ls.getFirst());
        }
        liter.remove();
        assertEquals(0, l.getSize());
        lsiter.remove();
        assertEquals(0, ls.getSize());
    }
}
