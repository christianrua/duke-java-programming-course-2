package com.example.javaproject;

import edu.duke.URLResource;
import java.io.*;
import java.util.*;
import java.util.stream.*;
import com.example.javaproject.ParseHTML;

public class Part4 {

    public List<String> linksSearcher(List<String> urlResponse) {
        List<String> linksFiltered = new ArrayList<String>();
        for (String line: urlResponse){
            if(line.startsWith("<li>") == true && (line.contains("youtube") || line.contains("YouTube") || line.contains("YOUTUBE")) ) {
                int indexFirstQuotation = line.indexOf("\"");
                int indexLastQuotation = line.lastIndexOf("\"");
                String urlName = line.substring(indexFirstQuotation+1, indexLastQuotation);
                linksFiltered.add(urlName);
            }
        }
        return linksFiltered;
    }

        public void printYouTubeLink(){

            ParseHTML htmlParser = new ParseHTML();
            String stringUrl = "https://www.dukelearntoprogram.com/course2/data/manylinks.html";
            List<String> urlResponse = htmlParser.parseHTML(stringUrl);
            List<String> filteredResponse = linksSearcher(urlResponse);
            System.out.println(filteredResponse);

        }

}
