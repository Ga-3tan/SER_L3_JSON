import parserElement.feature.Feature;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Feature> countries = Parser.geojsonToJavaObject("countries.geojson");
        Parser.javaObjectToKML(countries);
    }
}
