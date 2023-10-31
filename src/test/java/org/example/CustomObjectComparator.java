package org.example;

import java.util.Comparator;

public class CustomObjectComparator implements Comparator<CustomComparableObject> {
    @Override
    public int compare(CustomComparableObject obj1, CustomComparableObject obj2) {
        // Сравниваем объекты на основе их имен (по алфавиту)
        return obj1.getName().compareTo(obj2.getName());
    }
}
