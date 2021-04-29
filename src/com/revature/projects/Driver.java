package com.revature.projects;

import com.revature.projects.models.MyList;

public class Driver {
    public static void main(String[] args) {
        MyList<Integer> myList = new MyList<>();
        myList.add(10);
        myList.add(20);
        myList.add(30);
        myList.add(40);
        myList.add(50);
        myList.add(60);

        System.out.println(myList);
    }
}
