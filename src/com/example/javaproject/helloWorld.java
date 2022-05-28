package com.example.javaproject;

import com.example.javaproject.FindGeneSimpleAndTest;
public class helloWorld {
    public static void main(String[] args) {
        String s = "dukeprogramming";
        String x = s.substring(4,7);
        System.out.println(s);
        System.out.println(x);
        System.out.println("length value " + s.length());
        System.out.println("index of program is " + s.indexOf("program"));

        FindGeneSimpleAndTest testingClass = new FindGeneSimpleAndTest();
        testingClass.testFindGeneSimple();
    }
}
