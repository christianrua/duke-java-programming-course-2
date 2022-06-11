package com.dukeLearnToProgram.week2;

public class Part3 {

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

    public int countAllGenes(String dna){
        int startIndex = 0;
        int counter = 0;
        while (true) {
            String currentGene = findGene(dna,startIndex);
            if (currentGene.isEmpty()) {
                break;
            }

            counter = counter + 1;
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return counter;
    }


    public void testCountGenes() {
        System.out.println("testing first dna string");
        String dna1 = "CGATGATCGCATGATTCATGCTTAAATAAAGCTCA";
        System.out.println(countAllGenes(dna1));

        System.out.println("testing second dna string");
        String dna2 = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        System.out.println(countAllGenes(dna2));

        System.out.println("tests finished");
    }
}
