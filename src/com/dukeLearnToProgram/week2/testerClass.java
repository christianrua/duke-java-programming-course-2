package com.dukeLearnToProgram.week2;

import com.dukeLearnToProgram.week2.Part1;
import com.dukeLearnToProgram.week2.Part2;
import com.dukeLearnToProgram.week2.Part3;

public class testerClass {

        public static void main(String[] args) {
            System.out.println("testing Part1");
            Part1 part1TestClass = new Part1();
            part1TestClass.testFindGene();

            System.out.println("testing Part2");
            Part2 part2TestClass = new Part2();
            part2TestClass.testHowMany();

            System.out.println("testing Part3");
            Part3 part3TestClass = new Part3();
            part3TestClass.testCountGenes();

        }
}
