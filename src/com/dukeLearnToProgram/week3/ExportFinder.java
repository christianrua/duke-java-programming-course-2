package com.dukeLearnToProgram.week3;

import edu.duke.*;
import org.apache.commons.csv.*;

public class ExportFinder {

    public void findExport(String exportName){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        for(CSVRecord record : parser) {
            if(record.get("Exports").contains(exportName)) {
                System.out.println(record.get("Country"));
            }
        }
    }
}
