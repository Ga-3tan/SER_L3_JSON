package Feature;

public class Properties {
    private final String ADMIN; // nom pays
    private final String ISO_A3; // diminutif

    public Properties(String ADMIN, String ISO_A3) {
        this.ADMIN = ADMIN;
        this.ISO_A3 = ISO_A3;
    }

    public String getADMIN() {
        return ADMIN;
    }

    public String getISO_A3() {
        return ISO_A3;
    }
}
