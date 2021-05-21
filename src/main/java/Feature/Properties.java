package Feature;

import org.json.simple.JSONObject;

public class Properties {
    private final String ADMIN; // nom pays
    private final String ISO_A3; // diminutif

    public Properties(String ADMIN, String ISO_A3) {
        this.ADMIN = ADMIN;
        this.ISO_A3 = ISO_A3;
    }

    public static Properties parse(JSONObject propertiesJSON) {
        String admin = (String) propertiesJSON.get("ADMIN");
        String iso = (String) propertiesJSON.get("ISO_A3");
        return new Properties(admin,iso);
    }

    public String getADMIN() {
        return ADMIN;
    }

    public String getISO_A3() {
        return ISO_A3;
    }
}
