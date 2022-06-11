package com.example.javaproject;


public class AllCodons {

    public int findStopCodon(String dnaStr, int startIndex, String stopCodon) {

        int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3);

        while (currIndex != -1) {
            int diff = currIndex - startIndex;

            if (diff % 3 == 0){
                return currIndex;
            } else {
                currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dnaStr.length();
    }

    public String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex,"TAA");
        int tagIndex = findStopCodon(dna, startIndex,"TAG");
        int tgaIndex = findStopCodon(dna, startIndex,"TGA");

        int temp = Math.min(taaIndex, tgaIndex);
        int minIndex = Math.min(temp, tagIndex);

        if(minIndex == dna.length()) {
            return "there is no gene";
        }

        return dna.substring(startIndex, minIndex + 3);
    }

    public void testFindGene(){
        System.out.println(findGene("CGATGGTTGATAAGCCTAAGCTATAA"));
        //expected response ATGGTTGATAAGCCTAAGCTAA
    }


    public void testFindStop() {
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna,0,"TAA");
        System.out.println("first value "+ dex);
        if (dex != 9) System.out.println("error on 9");
        dex = findStopCodon(dna, 9, "TAA");
        System.out.println("second value "+ dex);
        if (dex != 21) System.out.println("error on 21");
        dex = findStopCodon(dna, 1, "TAA");
        System.out.println("third value "+ dex);
        if (dex != 26) System.out.println("error on 26");
        dex = findStopCodon(dna, 0, "TAG");
        System.out.println("fourth value "+ dex);
        System.out.println("tests finished");


    }
}
