package com.example.javaproject;

public class Part3 {

    public boolean twoOccurrences (String stringa, String stringb) {
        int count = ( stringb.split(stringa).length ) - 1;
        if (count >= 2) {
            return true;
        }
        else {
            return false;
        }
    }

    public String lastPart (String stringa, String stringb) {
        int firstOccurrence = stringb.indexOf(stringa);
        if (firstOccurrence == -1) {
            return stringb;
        } else {
            return stringb.substring(firstOccurrence+1);
        }

    }

    public void testing() {
        boolean result1 = twoOccurrences("by", "A story by Abby Long");
        boolean result2 = twoOccurrences("a", "banana");
        boolean result3 = twoOccurrences("atg", "ctgtatgta");

        System.out.println("twoOccurrences result 1 " + result1);
        System.out.println("twoOccurrences result 2 " + result2);
        System.out.println("twoOccurrences result 3 " + result3);

        String result4 = lastPart("an", "banana");
        String result5 = lastPart("zoo", "forest");

        System.out.println("lastPart result 4 " + result4);
        System.out.println("lastPart result 5 " + result5);


    }
}
