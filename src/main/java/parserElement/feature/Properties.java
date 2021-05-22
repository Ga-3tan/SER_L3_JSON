package parserElement.feature;

import parserElement.KMLElement;
import org.json.simple.JSONObject;
import org.jdom2.Element;
import java.util.HashMap;
import java.util.Map;

public class Properties implements KMLElement {
    // Structure "name" => "value"
    private final Map<String, String> properties;

    public Properties(Map<String, String> properties) {
        this.properties = properties;
    }


    public String getADMIN() {
        return properties.get("ADMIN");
    }

    public static Properties parse(JSONObject propertiesJSON) {
//        String admin = (String) propertiesJSON.get("ADMIN");
//        String iso = (String) propertiesJSON.get("ISO_A3");

        Map<String,String> map = new HashMap<>();
        map.put("ADMIN",propertiesJSON.get("ADMIN").toString());
        map.put("ISO_A3",propertiesJSON.get("ISO_A3").toString());

        return new Properties(map);
    }

    @Override
    public Element toKML() {
        Element extendedData = new Element("ExtendedData");
        for (Map.Entry<String, String> property : properties.entrySet()) {
            Element data = new Element("Data")
                    .setAttribute("name", property.getKey());

            data.addContent(new Element("value").addContent(property.getValue()));
            extendedData.addContent(data);
        }
        return extendedData;
    }
}
