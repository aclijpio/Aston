package com.github.aclijpio.collections;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CustomLinkedListTest {

    @Test
    public void addFirst() {
        CustomList<String> list = new CustomLinkedList<>();
        list.addFirst("C");
        list.addFirst("B");
        list.addFirst("A");

        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
        assertEquals(3, list.size());
    }

    @Test
    public void addLast() {
        CustomList<String> list = new CustomLinkedList<>();
        list.addLast("A");
        list.addLast("B");
        list.addLast("C");

        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals("C", list.get(2));
        assertEquals(3, list.size());
    }

    @Test
    public void removeElement() {
        CustomList<String> list = new CustomLinkedList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals("B", list.remove(1));
        assertEquals("A", list.get(0));
        assertEquals("C", list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    public void getFirstAndLast() {
        CustomList<String> list = new CustomLinkedList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals("A", list.getFirst());
        assertEquals("C", list.getLast());
    }

    @Test
    public void clearList() {
        CustomList<String> list = new CustomLinkedList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        list.clear();

        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }

    @Test
    public void sortCustomLinkedList() {
        CustomList<Integer> list = new CustomLinkedList<>();
        list.add(3);
        list.add(1);
        list.add(4);
        list.add(1);
        list.add(5);
        list.add(9);
        list.add(2);
        list.add(6);
        list.add(5);
        list.add(3);
        list.add(5);

        list.sort(Integer::compareTo);

        Integer[] expected = {1, 1, 2, 3, 3, 4, 5, 5, 5, 6, 9};
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], list.get(i));
        }
    }

    @Test
    public void overflowLinkedList() {
        final int range = 100;

        CustomLinkedList<Integer> list = new CustomLinkedList<>();

        for (int i = 0; i < range; i++) {
            list.add(i);
        }
        assertEquals(range, list.size());
    }

    @Test
    public void removeFirstElement() {
        CustomList<String> list = new CustomLinkedList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals("A", list.remove(0));
        assertEquals("B", list.get(0));
        assertEquals("C", list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    public void removeLastElement() {
        CustomList<String> list = new CustomLinkedList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        assertEquals("C", list.remove(2));
        assertEquals("A", list.get(0));
        assertEquals("B", list.get(1));
        assertEquals(2, list.size());
    }

    @Test
    public void getFirstFromEmptyList() {
        CustomList<String> list = new CustomLinkedList<>();

        assertThrows(NoSuchElementException.class, list::getFirst);
    }

    @Test
    public void getLastFromEmptyList() {
        CustomList<String> list = new CustomLinkedList<>();

        assertThrows(NoSuchElementException.class, list::getLast);
    }
}