import parserElement.feature.Feature;
import parserElement.style.Style;
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
    private static final Style STYLE = new Style("style", "FF90EE90", 2,0);

    public static List<Feature> geojsonToJavaObject(String filename) {
        JSONParser jsonParser = new JSONParser();
        List<Feature> featureList = new LinkedList<>();
        try (FileReader reader = new FileReader(filename)) {
            // lecture du fichier
            Object obj = jsonParser.parse(reader);
            JSONArray countriesList = (JSONArray) ((JSONObject) obj).get("features");

            // parcours du tableau de pays
            for (Object country : countriesList) {
                featureList.add(Feature.parse((JSONObject) country, STYLE.getId()));
            }
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
        return featureList;
    }

    public static void javaObjectToKML(List<Feature> featuresCollection){
        Document document = new Document();
        document.setRootElement(new Element("Document"));

        document.getRootElement().addContent(STYLE.toKML());

        for (Feature feature : featuresCollection) {
            document.getRootElement().addContent(feature.toKML());
        }

        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());

        File output = new File(OUT_PATH);
        if (!output.exists()) {
            output.mkdir();
        }

        try {
            xmlOutputter.output(document, new FileWriter(OUT_PATH + "countries.kml"));
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
