package com.revature.projects;

import com.revature.projects.utilities.MyList;

public class Driver {
    public static void main(String[] args) {
        MyList<Integer> myList = new MyList<>();
        myList.add(10);
        myList.add(20);
        myList.add(30);
        myList.add(40);
        myList.add(50);
        myList.add(60);

        myList.add(3,25);
        myList.set(6,55);
        System.out.println("List contains 40: " + myList.contains(40));
        System.out.println("List contains 65: " + myList.contains(65));
        System.out.println("List size: " + myList.size());
        System.out.println("List is empty: " + myList.isEmpty());
        Object[] myArray = myList.toArray();

        myList.remove(5);
        Object myInt = myList.get(3);
        System.out.println("Index of 40: " + myList.indexOf(40));


        System.out.println(myList);
    }
}
