package Feature;

import Geometry.Geometry;

public class Feature { /* PAYS */
    private final Properties properties;
    private final Geometry geometry;

    public Feature(Properties properties, Geometry geometry) {
        this.properties = properties;
        this.geometry = geometry;
    }

    public Properties getProperties() {
        return properties;
    }

    public Geometry getGeometry() {
        return geometry;
    }
}
