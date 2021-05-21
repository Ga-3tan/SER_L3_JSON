import Feature.Feature;
import Feature.FeaturesCollection;
import Feature.Properties;
import Geometry.MultiPolygon;
import Geometry.Polygon;
import Geometry.Point;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Geometry.Geometry;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Parser {
    private static final String PATH = "output/";

    public static List<Feature> geojsonToJavaObject(String filename) {
        JSONParser jsonParser = new JSONParser();
        List<Feature> featureList = new LinkedList<Feature>();;
        try (FileReader reader = new FileReader(filename)) {

            // lecture du fichier
            Object obj = jsonParser.parse(reader);
            JSONArray countriesList = (JSONArray) ((JSONObject) obj).get("features");

            // parcours du tableau de pays
            for (Object country : countriesList) {
                featureList.add(countriesParser((JSONObject)country));
            }
        } catch (IOException | ParseException e){
            System.out.println(e.getMessage());
        }
        return featureList;
    }

    private static Feature countriesParser(JSONObject pays) throws ParseException {
        JSONObject propertiesJSON = (JSONObject) pays.get("properties");
        Properties properties = Properties.parse(propertiesJSON);
        JSONObject geometryJSON = (JSONObject) pays.get("geometry");

        Geometry geometry;
        switch ((String) geometryJSON.get("type")) {
            case "MultiPolygon":
                geometry = MultiPolygon.parse((JSONArray) geometryJSON.get("coordinates"));
                break;
            case "Polygon":
                geometry = Polygon.parse((JSONArray) geometryJSON.get("coordinates"));
                break;
            case "Point":
                geometry = Point.parse((JSONArray) geometryJSON.get("coordinates"));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + (String) geometryJSON.get("type"));
        }

        return new Feature(properties, geometry);
    }

    static FeaturesCollection test() {
        return null;
    }

    public static void javaObjectToKML(FeaturesCollection featuresCollection) {

    }
}
