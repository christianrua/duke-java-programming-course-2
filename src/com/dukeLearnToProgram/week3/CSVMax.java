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
        CSVRecord lowestHumidity = null;
        for(CSVRecord record : parser) {
            if (lowestHumidity == null){
                lowestHumidity = record;
            }
            if(!record.get("Humidity").equals("N/A")){
                int lowestHumidityInt = Integer.parseInt(lowestHumidity.get("Humidity"));
                int currentHumidity = Integer.parseInt(record.get("Humidity"));
                if (currentHumidity < lowestHumidityInt){
                    lowestHumidity = record;
                }
            } else {
                continue;
            }
        }
        return lowestHumidity;

    }

    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestSoFar = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord current = lowestHumidityInFile(fr.getCSVParser());
            lowestSoFar = getDifferencesOfTwo(current,lowestSoFar, "cold", "Humidity");
        }
        return lowestSoFar;
    }

    public double averageTemperatureInFile(CSVParser parser){
        double avgTem = 0.0;
        for(CSVRecord record : parser) {
            avgTem = avgTem + Double.parseDouble(record.get("TemperatureF"));
        }
        avgTem = avgTem/parser.getRecordNumber();
        return avgTem;
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, Integer value) {
        double avgTem = 0.0;
        Integer humidityValue = 0;
        Integer numberOfSumValues = 0;
        for(CSVRecord record : parser) {
            humidityValue = Integer.parseInt(record.get("Humidity"));
            if(humidityValue >= value) {
                avgTem = avgTem + Double.parseDouble(record.get("TemperatureF"));
                numberOfSumValues = numberOfSumValues + 1;
            }

        }
        avgTem = avgTem/numberOfSumValues;
        return avgTem;
    }

    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        double avgTemp = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average temperature in file is " + avgTemp);
    }

    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        double avgTemp = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
        if (avgTemp == 0.0) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average Temp when high Humidity is " + avgTemp);
        }

    }


    public void testLowestHumidityInManyFiles() {
        CSVRecord lowestHumidity = lowestHumidityInManyFiles();
        System.out.println("lowest humidity value " + lowestHumidity.get("Humidity")
                + " at " + lowestHumidity.get("DateUTC"));
    }

    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVRecord lowestHumidity = lowestHumidityInFile(fr.getCSVParser());
        System.out.println("lowest humidity value " + lowestHumidity.get("Humidity")
                + " at " + lowestHumidity.get("DateUTC"));
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

    public void testColdestInManyDays() {
        CSVRecord largest = temperatureInManyDays("Cold");
        System.out.println("coldest temperature was " + largest.get("TemperatureF")
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
                " at "+largest.get("DateUTC"));
    }
}
