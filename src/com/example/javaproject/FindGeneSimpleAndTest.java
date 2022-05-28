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

    public void printDNAStrand(String dna){
        System.out.println("DNA strand is " + dna);
        String gene = findGeneSimple(dna);
        System.out.println("Gene is " + gene);
    }

    public void testFindGeneSimple() {
        String dna1 = "AATGCGTAATATGGT";
        String dna2 = "AATGCTAGGGTAATATGGT";
        String dna3 = "ATCCTATGCTTCGGCTGCTCTAATATGGT";
        String dna4 = "ATGTAA";

        printDNAStrand(dna1);
        printDNAStrand(dna2);
        printDNAStrand(dna3);
        printDNAStrand(dna4);
    }

    public static void main(String[] args) {

    }
}

