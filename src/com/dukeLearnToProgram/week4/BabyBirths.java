package com.dukeLearnToProgram.week4;

import edu.duke.*;
import org.apache.commons.csv.*;

import javax.swing.*;
import java.io.File;

public class BabyBirths {
    public void printName() {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            if(numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                        " Gender " + rec.get(1) +
                        " Num Born " + rec.get(2)
                );
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalNames = 0;
        int totalBoysNames = 0;
        int totalGirlNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            totalNames += 1;
            if (rec.get(1).equals("M")){
                totalBoys += numBorn;
                totalBoysNames += 1;
            } else {
                totalGirls += numBorn;
                totalGirlNames += 1;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total Boys = " + totalBoys);
        System.out.println("total Girls = " + totalGirls);
        System.out.println("total names = " + totalNames);
        System.out.println("total Boys names = " + totalBoysNames);
        System.out.println("total Girls names = " + totalGirlNames);
    }

    private String getFilesPath(String test, int year){
        String fileName = "";
        if(test == "true"){
            fileName = "us_babynames/us_babynames_test/yob"+year+"short.csv";
        } else {
            fileName = "us_babynames/us_babynames_by_year/yob"+year+".csv";
        }
        return fileName;
    }

    public int getRank (int year, String name, String gender, String test){
        int rankValue = -1;
        int rankCounter = 0;
        String fileName = getFilesPath(test, year);
        FileResource fr = new FileResource(fileName);

        for (CSVRecord rec : fr.getCSVParser(false)) {

            if (rec.get(0).equals(name) && rec.get(1).equals(gender)) {
                rankCounter = rankCounter + 1;
                rankValue = rankCounter;
                return rankValue;
            } else if (rec.get(1).equals(gender)) {
                rankCounter = rankCounter + 1;
            }
        }
        return rankValue;
    }

    public String getName(int year, int rank, String gender, String test){
        String name = "NO NAME";
        int rankCounter = 0;
        String fileName = getFilesPath(test, year);
        FileResource fr = new FileResource(fileName);

        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                rankCounter = rankCounter + 1;
            }
            if(rankCounter == rank) {
                name = rec.get(0);
                return name;
            }
        }
        return name;
    }

    public void whatIsNameInYear(String name, int year, int newYear, String gender, String test){
        int rankValue = getRank(year,name,gender,test);
        String newName = getName(newYear, rankValue, gender, test);
        System.out.println(name + " born in "+year+" would be "+newName+" if she/he was born in "+newYear);
    }

    public int yearOfHighestRank(){
        //I'm Here
    }

    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }

    public void testGetRank(){
        int response = getRank(2012,"Mason","M","true");
        System.out.println("The rank value is " + response);
    }

    public void testGetName(){
        String response = getName(2012,2,"M","true");
        System.out.println("The name value is " + response);
        response = getName(2012,6,"M","true");
        System.out.println("The name value is " + response);
    }

    public void testWhatIsNameInYear(){
        whatIsNameInYear("Isabella",2012,2014,"F","true");
    }
}
