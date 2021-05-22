package ParserElement.Feature;

import ParserElement.KMLElement;
import org.json.simple.JSONObject;
import org.jdom2.Element;

public class Properties implements KMLElement {
//    private final String ADMIN; // nom pays
//    private final String ISO_A3; // diminutif
    private final String[] properties;
    private static final String[] propertiesNames = {"ADMIN", "ISO_A3"};

    public Properties(String ADMIN, String ISO_A3) {
        properties = new String[]{ADMIN, ISO_A3};
//        this.ADMIN = ADMIN;
//        this.ISO_A3 = ISO_A3;
    }

    public String getADMIN() {
        return properties[0];
    }

//    public String getISO_A3() {
//        return ISO_A3;
//    }

    public static Properties parse(JSONObject propertiesJSON) {
        String admin = (String) propertiesJSON.get("ADMIN");
        String iso = (String) propertiesJSON.get("ISO_A3");
        return new Properties(admin, iso);
    }

    @Override
    public Element toKML() {
        Element extendedData = new Element("ExtendedData");
        for (int i = 0; i < properties.length; ++i) {
            Element data = new Element("Data")
                    .setAttribute("name", propertiesNames[i]);

            data.addContent(new Element("value").addContent(properties[i]));
            extendedData.addContent(data);
        }
        return extendedData;
    }
}
