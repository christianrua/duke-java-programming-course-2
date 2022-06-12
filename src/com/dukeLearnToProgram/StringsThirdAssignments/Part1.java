package com.dukeLearnToProgram.StringsThirdAssignments;

import edu.duke.StorageResource;
import edu.duke.FileResource;
import com.dukeLearnToProgram.StringsThirdAssignments.*;

public class Part1 {

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

    public StorageResource getAllGenes(String dna){
        int startIndex = 0;
        StorageResource sr = new StorageResource();
        while (true) {
            String currentGene = findGene(dna,startIndex);
            if (currentGene.isEmpty()) {
                break;
            }

            //System.out.println(currentGene);
            sr.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }

        return sr;
    }

    public int countLetters(String word,String letter){
        int occurrences = 0;
        int startIndex = word.indexOf(letter, 0);
        while(startIndex != -1){
            occurrences = occurrences + 1;
            startIndex = word.indexOf(letter, startIndex + letter.length());
        }
        return occurrences;
    }

    public float cgRatio(String dna){
        int cRepetitions = countLetters(dna,"c");
        int gRepetitions = countLetters(dna,"g");
        return (float) (cRepetitions + gRepetitions) / dna.length();
    }

    public int countCTG(String dna){
        return countLetters(dna, "CTG");
    }

    public void processGenes(StorageResource sr){
        int overNineCharacters = 0;
        float cgRatioValue = 0;
        int over3dot5cgRatio = 0;
        int highestGeneLength = 0;

        for(String gene: sr.data()) {
            if(gene.length() > 60) {
                System.out.println("Gene with a length over 60 letters " + gene);
                overNineCharacters = overNineCharacters + 1;
            }

            cgRatioValue = cgRatio(gene);
            if (cgRatioValue > 0.35){
                System.out.println("gene with a cgRatio over 0.35 " + gene);
                over3dot5cgRatio = over3dot5cgRatio + 1;
            }

            if(gene.length() > highestGeneLength) {
                highestGeneLength = gene.length();
            }

        }
        System.out.println("the total number of Genes that has more that 60 characters are: " + overNineCharacters);
        System.out.println("the total numbers of genes with a cgRatio over 3.5 are " + over3dot5cgRatio);
        System.out.println("the highest gene length is " + highestGeneLength);
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
        System.out.println(getAllGenes(dna1).size());

        System.out.println("testing second dna string");
        String dna2 = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        System.out.println(getAllGenes(dna2).size());

        System.out.println("tests finished");

        String question1 = "AATGCTAACTAGCTGACTAAT";
        System.out.println(getAllGenes(question1).size());
    }

    public void testProcessGenes(){
        FileResource fr = new FileResource("/home/chr1st14n_ru4/development/duke-java-programming-course-2/GRch38dnapart.fa");
        String dna = fr.asString().toUpperCase();
        System.out.println(getAllGenes(dna).size());
//        for (String gene: getAllGenes(dna).data()){
//            System.out.println("find it gene: "+gene);
//        }

        processGenes(getAllGenes(dna));

    }
}
