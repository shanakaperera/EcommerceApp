/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.certus.test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author shanaka
 */
public class Test {

    public static void main(String[] args) {
/*
    Type Blouses
    Fabric 100% POLYESTER
    Fit Regular
    Wash Dark Colours To Be Washed Separately
    Color Black
    Style Embellished
    Model Stats This model has height 5'8",Bust 34",Waist 29",Hip 36"and is Wearing Size XS

   */   
        
        Map<String, String> myMap = new HashMap<>();
        myMap.put("Type", "Crop Tops");
        myMap.put("Fit", "Regular");
       // myMap.put("Sleeves", "Short Sleeves");
       // myMap.put("Neck", "Round Neck");
        myMap.put("Wash", "Dark Colours Separately, Mild Wash");
        myMap.put("Color", "Black");
       // myMap.put("Length", "Waist Length");
        myMap.put("Fabric Details", "100% POLYESTER");
        myMap.put("Style", "Embellished");
        myMap.put("Model Stats", "This model has height 5'8\",Bust 34\",Waist 29\",Hip 36\"and is Wearing Size XS");

        String work = "<tbody>";
        for (Map.Entry<String, String> entrySet : myMap.entrySet()) {
            String key = entrySet.getKey();
            String value = entrySet.getValue();
            work = work + "<tr><td>"
                    + "<strong>" + key + "</strong>"
                    + "</td>"
                    + "<td>" + value + "</td>"
                    + "</tr>";

        }
        StringBuilder sb = new StringBuilder(work);
        String finalString = sb.append("</tbody>").toString();
        System.out.println(finalString);

    }
}
