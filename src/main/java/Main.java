/**
 * File     : Main.java
 * Authors  : Gaétan Zwick, Alessandro Parrino
 * Date     : 18.05.2021
 */

import parserElement.feature.Feature;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Feature> countries = Parser.geojsonToJavaObject("countries.geojson");
        for(Feature f : countries) System.out.println(f);
        Parser.javaObjectToKML(countries);
    }
}
