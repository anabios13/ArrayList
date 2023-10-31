package org.example;

import by.anabios13.MyArrayList;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MyArrayListTest {
    private MyArrayList<Integer> list;
    private MyArrayList<CustomComparableObject> listForSortTest;


    @Before
    public void setUp() {
        list = new MyArrayList<>();
        listForSortTest = new MyArrayList<>();
    }

    @Test
    public void testAdd() {
        for (int i=0;i<1000;i++)
        list.add(1);
        assertEquals(1, (int) list.get(0));
    }

    @Test
    public void testAddCustomObjects() {
        for (int i=0;i<1000;i++)
            listForSortTest.add(new CustomComparableObject("A"));
        assertEquals(1000, listForSortTest.size());
    }


    @Test
    public void testAddAtIndex() {
        list.add(1);
        for (int i=0; i<1000;i++)
        list.add(0, 2);
        assertEquals(2, (int) list.get(0));
        assertEquals(1, (int) list.get(1000));
    }



    @Test
    public void testReplaceAtIndex() {
        list.add(1);
        list.replace(0,2);
        assertEquals(2, (int) list.get(0));
    }

    @Test
    public void testRemove() {
        list.add(1);
        list.add(2);
        list.remove(0);
        assertEquals(2, (int) list.get(0));
    }

    @Test
    public void testClear() {
        list.add(1);
        list.add(2);
        list.clear();
        assertEquals(0,list.size());
    }

    @Test
    public void testGet() {
        list.add(1);
        list.add(2);
        assertEquals(2, (int) list.get(1));
    }

    @Test
    public void testQuickSort() {
        list.add(3);
        list.add(1);
        list.add(2);
        list.add(4);

        list.quickSort(Comparator.naturalOrder());

        assertEquals(1, (int) list.get(0));
        assertEquals(2, (int) list.get(1));
        assertEquals(3, (int) list.get(2));
        assertEquals(4, (int) list.get(3));
    }

    @Test
    public void testQuickSortEmptyList() {
        list.quickSort(Comparator.naturalOrder());
        assertEquals(list.size(),0);
    }

    @Test
    public void testSortWithCustomComparator() {
        CustomComparableObject obj1 = new CustomComparableObject("B");
        CustomComparableObject obj2 = new CustomComparableObject("A");
        CustomComparableObject obj3 = new CustomComparableObject("C");

        listForSortTest.add(obj1);
        listForSortTest.add(obj2);
        listForSortTest.add(obj3);

        // Создаем кастомный компаратор
        Comparator<CustomComparableObject> customComparator = new CustomObjectComparator();

        // Вызываем метод сортировки с кастомным компаратором
        listForSortTest.quickSort(customComparator);

        // Проверяем, что элементы отсортированы правильно
        assertEquals("A", listForSortTest.get(0).getName());
        assertEquals("B", listForSortTest.get(1).getName());
        assertEquals("C", listForSortTest.get(2).getName());
    }


    @Test
    public void testSize() {
        assertEquals(list.size(),0);
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(3, list.size());
    }

}
