package com.dukeLearnToProgram.week3;

import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExportData {

    public String countryInfo(CSVParser parser, String country) {

        for(CSVRecord record : parser){
            String countryName = record.get("Country");
            if(countryName.contains(country)) {
                String exports = record.get("Exports");
                String exportValue = record.get("Value (dollars)");
                return countryName + ": " + exports + ": " + exportValue;
            }
        }
        return "NOT FOUND";

    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for(CSVRecord record : parser){
            String exports = record.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2)){
                String countryName = record.get("Country");
                System.out.println(countryName);
            }
        }

    }

    public int numberOfExporters(CSVParser parser, String exportItem){
        int numberOfCountries = 0;

        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if(exports.contains(exportItem)) {
                numberOfCountries = numberOfCountries + 1;
            }
        }

        return numberOfCountries;
    }

    public void bigExporters(CSVParser parser, String amount) {
        for(CSVRecord record : parser){
            String exportsAmount = record.get("Value (dollars)");
            if(exportsAmount.length() > amount.length()){
                String countryName = record.get("Country");
                System.out.println(countryName + " " + exportsAmount);
            }
        }
    }

    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();

        String countryInfoResult = countryInfo(parser, "Nauru");
        System.out.println("countryInfoResult "+countryInfoResult);
        System.out.println("listExportersTwoProducts results: ");
        listExportersTwoProducts(fr.getCSVParser(), "gold","diamonds");
        int numberOfExportersResult = numberOfExporters(fr.getCSVParser(), "gold");
        System.out.println("numberOfExportersResult " + numberOfExportersResult);
        System.out.println("bigExporters result: ");
        bigExporters(fr.getCSVParser(), "$999,999,999,999");
    }


}
