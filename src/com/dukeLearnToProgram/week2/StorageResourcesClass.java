package com.dukeLearnToProgram.week2;

import edu.duke.StorageResource;

public class StorageResourcesClass {

    public int findStopCodon(String dnaStr, int startIndex, String stopCodon) {
        int currIndex = dnaStr.indexOf(stopCodon,startIndex + 3);
        while (currIndex != -1) {
            int diff = currIndex - startIndex;
            if (diff % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
            }
        }
        return -1;
    }

    public String findGene(String dna, int where) {

        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }

        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = 0;

        if(taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }

        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)) {
            minIndex = tagIndex;
        }

        if (minIndex == -1 ) {
            return "";
        }

        return dna.substring(startIndex, minIndex +3);
    }

    public StorageResource attachAllGenes(String dna){
        int startIndex = 0;
        StorageResource sr = new StorageResource();
        while (true) {
            String currentGene = findGene(dna,startIndex);
            if (currentGene.isEmpty()) {
                break;
            }

            System.out.println(currentGene);
            sr.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return sr;
    }


    public void testFindStop() {
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna,0,"TAA");
        if (dex != 9) System.out.println("error on 9");
        dex = findStopCodon(dna, 9, "TAA");
        if (dex != 21) System.out.println("error on 21");
        dex = findStopCodon(dna, 1, "TAA");
        if (dex != -1) System.out.println("error on 26");
        dex = findStopCodon(dna, 0, "TAG");
        System.out.println("tests finished");

    }

    public void testAttachAllGenes() {
        System.out.println("testing first dna string");
        String dna1 = "CGATGATCGCATGATTCATGCTTAAATAAAGCTCA";
        System.out.println(attachAllGenes(dna1).size());

        System.out.println("testing second dna string");
        String dna2 = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        System.out.println(attachAllGenes(dna2).size());

        System.out.println("tests finished");

        String question1 = "AATGCTAACTAGCTGACTAAT";
        System.out.println(attachAllGenes(question1).size());
    }
}
