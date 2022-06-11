package com.example.javaproject;

import java.util.Arrays;
import java.util.OptionalInt;

public class FindGeneWhile3Codons {

    int[] starIndexCodonsArray = new int[3];
    int[] interMediumArray = new int[3];

    private int findSmallestCodonIndex(String dna, int startIndex) {

        starIndexCodonsArray[0] = dna.indexOf("TAA", startIndex);
        starIndexCodonsArray[1] = dna.indexOf("TGA", startIndex);
        starIndexCodonsArray[2] = dna.indexOf("TAG", startIndex);

        int minValue = Arrays.stream(starIndexCodonsArray).filter(
                entry -> entry > 0
        ).min().orElse(-1);

        return minValue;

    }

    public void filterStreamArrayTest() {
        starIndexCodonsArray[0] = -1;
        starIndexCodonsArray[1] = -1;
        starIndexCodonsArray[2] = -1;

        int minValue = Arrays.stream(starIndexCodonsArray).filter(
                entry -> entry > 0
        ).min().orElse(-1);

        System.out.println(minValue);
    }

    public String findGene3Codons(String dna) {
        int startIndex = dna.indexOf("ATG");
        int currIndex = findSmallestCodonIndex(dna, startIndex + 3);

        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return dna.substring(startIndex, currIndex + 3);
            } else {
                currIndex = findSmallestCodonIndex(dna, currIndex + 1);
            }
        }
        return "not gene where find it";
    }

    public void testFindGeneSimple() {
        String dna = "ATGATCGCTGATTAGGCTTAAATGACG";
        System.out.println("DNA strand is " + dna);
        String gene = findGene3Codons(dna);
        System.out.println("Gene is " + gene);

    }
}
