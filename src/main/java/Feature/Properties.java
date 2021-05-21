package Feature;

import org.jdom2.Element;

public class Properties {
//    private final String ADMIN; // nom pays
//    private final String ISO_A3; // diminutif
      private final String[] properties;
      private static final String[] propertiesNames = { "ADMIN" , "ISO_A3" };



    public Properties(String ADMIN, String ISO_A3) {
        properties = new String[]{ ADMIN, ISO_A3 };
//        this.ADMIN = ADMIN;
//        this.ISO_A3 = ISO_A3;
    }

    public String getADMIN() {
        return properties[0];
    }
//    public String getISO_A3() {
//        return ISO_A3;
//    }

    public Element toKML(){
        Element extendedData = new Element("ExtendedData");
        for(int i = 0; i < properties.length ; ++i){
            Element data = new Element("Data")
                           .setAttribute("name", propertiesNames[i]);

            data.addContent(new Element("value").addContent(properties[i]));
            extendedData.addContent(data);
        }
        return extendedData;}
}
