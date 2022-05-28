package com.example.javaproject;
import edu.duke.*;
import java.io.*;

public class FindGeneSimpleAndTest {
    public String findGeneSimple(String dna) {
        //start codon is "ATG
        //stop codon is "TAA"
        String result = "";

        return result;
    }

    public void testFindGeneSimple() {
        String dna = "AATGCGTAATATGGT";
        System.out.println("DNA strand is " + dna);
        String gene = findGeneSimple(dna);
        System.out.println("Gene is " + gene);

        dna = "AATGCTAGGGTAATATGGT";
        System.out.println("DNA strand is " + dna);
        gene = findGeneSimple(dna);
        System.out.println("Gene is " + gene);
    }
}
