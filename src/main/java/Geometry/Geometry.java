package Geometry;

import org.jdom2.Element;

public abstract class Geometry {

    public String getClassName() {
        return getClass().getSimpleName();
    }

    public abstract Element toKML();
}
