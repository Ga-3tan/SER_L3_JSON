import ParserElement.Feature.Feature;

import ParserElement.style.Style;

import java.io.*;

import java.util.List;
import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import org.jdom2.Element;
import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Parser {
    private static final String OUT_PATH = "output\\";
    private static Style styleMap = new Style("style", "FF90EE90", 2,0);

    public static List<Feature> geojsonToJavaObject(String filename) {
        JSONParser jsonParser = new JSONParser();
        List<Feature> featureList = new LinkedList<>();
        try (FileReader reader = new FileReader(filename)) {
            // lecture du fichier
            Object obj = jsonParser.parse(reader);
            JSONArray countriesList = (JSONArray) ((JSONObject) obj).get("features");

            // parcours du tableau de pays
            for (Object country : countriesList) {
                featureList.add(Feature.parse((JSONObject) country));
            }
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
        return featureList;
    }

//    static List<Feature> test() {
//        List<Point> coord = Arrays.asList(new Point(-69.99693762899992, 12.577582098000036),
//                new Point(-69.93639075399994, 12.53172435100005),
//                new Point(-69.92467200399994, 12.519232489000046),
//                new Point(-69.91576087099992, 12.497015692000076),
//                new Point(-69.88019771999984, 12.453558661000045),
//                new Point(-69.87682044199994, 12.427394924000097),
//                new Point(-69.88809160099993, 12.417669989000046),
//                new Point(-69.90880286399994, 12.417792059000107),
//                new Point(-69.93053137899989, 12.425970770000035),
//                new Point(-69.94513912699992, 12.44037506700009),
//                new Point(-69.92467200399994, 12.44037506700009),
//                new Point(-69.92467200399994, 12.447211005000014),
//                new Point(-69.95856686099992, 12.463202216000099),
//                new Point(-70.02765865799992, 12.522935289000088),
//                new Point(-70.04808508999989, 12.53115469000008),
//                new Point(-70.05809485599988, 12.537176825000088),
//                new Point(-70.06240800699987, 12.546820380000057),
//                new Point(-70.06037350199995, 12.556952216000113),
//                new Point(-70.0510961579999, 12.574042059000064),
//                new Point(-70.04873613199993, 12.583726304000024),
//                new Point(-70.05264238199993, 12.600002346000053),
//                new Point(-70.05964107999992, 12.614243882000054),
//                new Point(-70.06110592399997, 12.625392971000068),
//                new Point(-70.04873613199993, 12.632147528000104),
//                new Point(-70.00715084499987, 12.5855166690001),
//                new Point(-69.99693762899992, 12.577582098000036));
//        Polygon poly = new Polygon();
////        for(Point p : coord)
////            poly.add(p);
//
//       // Properties prop = new Properties("Aruba", "ABW");
//        Feature feature = new Feature(prop, poly, styleMap.getId());
//        List<Feature> f = new LinkedList<>();
//        f.add(feature);
//
//        return f;
//    }

    public static void javaObjectToKML(List<Feature> featuresCollection) throws Exception {
        Document document = new Document();
        document.setRootElement(new Element("Document"));

        document.getRootElement().addContent(styleMap.toKML());

        for (Feature feature : featuresCollection) {
            document.getRootElement().addContent(feature.toKML());
        }

        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());

        File output = new File(OUT_PATH);
        if (!output.exists()) {
            output.mkdir();
        }
        xmlOutputter.output(document, new FileWriter(OUT_PATH + "countries.kml"));

    }
}
