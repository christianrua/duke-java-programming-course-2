package com.example.javaproject;
import edu.duke.*;
import java.io.*;
import java.util.Locale;

public class FindGeneSimpleAndTest {
    public String findGeneSimple(String dna, String starCodon, String stopCodon ) {
        //start codon is "ATG"
        //stop codon is "TAA"
        String result = "";
        int startIndex = dna.indexOf(starCodon);
        if (startIndex == -1) // no ATG
            {
                return "ATG not found";
            }
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        if (stopIndex == -1) // no TAA
        {
            return "TAA not found";
        }

        result = dna.substring(startIndex, stopIndex + 3);
        if ((startIndex - stopIndex + 3) % 3 == 0) {
            return result;
        } else {
            return "malformed dna";
        }


    }

    public void printDNAStrand(String dna){
        String starCodon = "ATG";
        String stopCodon = "TAA";
        String gene = "";
        System.out.println("DNA strand is " + dna);
        if( dna.equals(dna.toUpperCase()) ) {
            gene = findGeneSimple(dna, starCodon, stopCodon);
        } else {
            gene = findGeneSimple(dna, starCodon.toLowerCase(), stopCodon.toLowerCase());
        }

        System.out.println("Gene is " + gene);
    }

    public void testFindGeneSimple() {
        String dna1 = "AATGCGTAATATGGT";
        String dna2 = "AATGCTAGGGTAATATGGT";
        String dna3 = "ATCCTATGCTTCGGCTGCTCTAATATGGT";
        String dna4 = "ATGTAA";
        String dna5 = "TTATAA";
        String dna6 = "ATGATG";
        String dna7 = "aatgcgtaatatggt";
        String dna8 = "atcctatgcttcggctgctctaatatggt";

        printDNAStrand(dna1);
        printDNAStrand(dna2);
        printDNAStrand(dna3);
        printDNAStrand(dna4);
        printDNAStrand(dna5);
        printDNAStrand(dna6);
        printDNAStrand(dna7);
        printDNAStrand(dna8);
    }

    public static void main(String[] args) {

    }
}

