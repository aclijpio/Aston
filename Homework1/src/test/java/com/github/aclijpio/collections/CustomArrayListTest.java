package com.github.aclijpio.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class CustomArrayListTest {

    @Test
    public void addElementToCustomList(){

        CustomList<String> list = new CustomArrayList<>();

        list.add("A");
        list.add("B");
        list.add("C");

        Assertions.assertEquals("A", list.get(0));
        Assertions.assertEquals("B", list.get(1));
        Assertions.assertEquals("C", list.get(2));
        Assertions.assertEquals(3, list.size());

    }

    @Test
    public void sortCustomList(){
        CustomList<Integer> list = new CustomArrayList<>();
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
            Assertions.assertEquals(expected[i], list.get(i));
        }
    }

    @Test
    public void overflowList(){

        final int capacity = 5;
        final int multi = 4;

        CustomList<Integer> list = new CustomArrayList<>(capacity);
        for (int i = 0; i < capacity * multi; i++) {
            list.add(i);
        }
        Assertions.assertEquals(capacity * multi, list.size());


    }

}