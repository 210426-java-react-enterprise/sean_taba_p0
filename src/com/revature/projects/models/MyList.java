package com.revature.projects.models;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MyList<T> {

    private T[] array;
    private int numberOfElements;

    public MyList() {
        this.array = (T[]) new Object[5];
        numberOfElements = 0;
    }

    private void grow()
    {
        T[] newArray = (T[]) new Object[2 * array.length];

        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }

        array = newArray;
    }

    public void add(T element)
    {
        if(array.length == numberOfElements) grow();
        array[numberOfElements] = element;
        numberOfElements++;
    }

    @Override
    public String toString() {
        return "MyList{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}
