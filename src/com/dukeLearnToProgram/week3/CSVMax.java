package com.dukeLearnToProgram.week3;

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {

    public CSVRecord getDifferencesOfTwo(CSVRecord currentRow,
                                         CSVRecord largestSoFar,
                                         String comparator,
                                         String columName){
        if(largestSoFar == null){
            largestSoFar = currentRow;

        } else {
            double currentTemp = Double.parseDouble(currentRow.get(columName));
            double largestTemp = Double.parseDouble(largestSoFar.get(columName));

            if (comparator == "Hot") {
                if(currentTemp > largestTemp) {
                    largestSoFar = currentRow;
                }
            } else {
                if(currentTemp < largestTemp) {
                    largestSoFar = currentRow;
                }
            }

        }
        return largestSoFar;
    }

    public CSVRecord temperatureHourInFile(CSVParser parser, String comparator){
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow : parser) {
            largestSoFar = getDifferencesOfTwo(currentRow,largestSoFar, comparator,"TemperatureF");
        }
        return largestSoFar;
    }

    public CSVRecord temperatureInManyDays(String comparator) {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord largestSoFar = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = temperatureHourInFile(fr.getCSVParser(), comparator);
            largestSoFar = getDifferencesOfTwo(current,largestSoFar, comparator, "TemperatureF");
        }
        return largestSoFar;
    }

    public File fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        File coldestFile = null;
        CSVRecord largestSoFar = null;
        CSVRecord flag = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = temperatureHourInFile(fr.getCSVParser(), "cold");
            largestSoFar = getDifferencesOfTwo(current,largestSoFar, "cold", "TemperatureF");
            if (flag != largestSoFar) {
                flag = largestSoFar;
                coldestFile = f;
            }
        }
        return coldestFile;
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        //I'm here
    }

    public void testFileWithColdestTemperature() {
        File f = fileWithColdestTemperature();
        FileResource fr = new FileResource(f);
        CSVRecord coldestTemp = temperatureHourInFile(fr.getCSVParser(), "cold");
        System.out.println("Coldest day was in file " + f.getName());
        System.out.println("Coldest temperature on that day was " + coldestTemp.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were: ");
        for (CSVRecord currentRow : fr.getCSVParser()) {
            System.out.println(currentRow.get("DateUTC") + " : " +currentRow.get("TemperatureF"));
        }
    }
    public void testHottestInManyDays() {
        CSVRecord largest = temperatureInManyDays("hot");
        System.out.println("hottest temperature was " + largest.get("TemperatureF")
        + " at " + largest.get("DateUTC"));
    }

    public void testHottestInDay() {
        FileResource fr = new FileResource();
        CSVRecord largest = temperatureHourInFile(fr.getCSVParser(),"hot");
        System.out.println("hottest temperature was " + largest.get("TemperatureF") +
                " at "+largest.get("TimeEST"));
    }

    public void testColdestInDay() {
        FileResource fr = new FileResource();
        CSVRecord largest = temperatureHourInFile(fr.getCSVParser(),"cold");
        System.out.println("coldest temperature was " + largest.get("TemperatureF") +
                " at "+largest.get("TimeEST"));
    }
}
