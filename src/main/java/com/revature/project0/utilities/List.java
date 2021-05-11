package com.revature.project0.utilities;


import exceptions.IllegalInputException;

public interface List<T> {

    int size();
    boolean isEmpty();
    boolean contains(T element);
    MyIterator<T> iterator();
    T[] toArray();
    boolean add(T element) throws IllegalInputException;
    void add(int index, T element) throws IllegalInputException;
    void clear();
    T get(int index);
    T set(int index, T element);
    boolean remove(T element);
    T remove(int index);
    int indexOf(T element);
    T at(int index);
    T getLast();

}
