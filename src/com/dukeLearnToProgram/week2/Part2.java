package com.dukeLearnToProgram.week2;

public class Part2 {

        public int howMany (String stringa, String stringb) {
            int startIndex = 0;
            int numOfOccurrences = 0;
            int occurrenceIndex = 0;
            while(true) {
                occurrenceIndex = stringb.indexOf(stringa, startIndex);
                if(occurrenceIndex == -1) {
                    break;
                } else {
                    numOfOccurrences = numOfOccurrences + 1;
                    startIndex = occurrenceIndex + stringa.length();
                }
            }
            return numOfOccurrences;
        }

        public void testHowMany() {
            System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
            System.out.println(howMany("AA", "ATAAAA"));
        }

}
