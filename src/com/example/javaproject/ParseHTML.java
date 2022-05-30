package com.example.javaproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ParseHTML {

    public List<String> parseHTML(String url) {
        List<String> a = new ArrayList<String>();
        try {
            String parseLine; /* variable definition */
            /* create objects */
            URL URL = new URL(url);
            BufferedReader br = new BufferedReader(new InputStreamReader(URL.openStream()));

            while ((parseLine = br.readLine()) != null) {
                /* read each line */
//                System.out.println(parseLine);
                a.add(parseLine);
            }
            br.close();


        } catch (MalformedURLException me) {
            System.out.println(me);

        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        return a;
    }
}
