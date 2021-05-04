package Feature;

import java.util.LinkedList;
import java.util.List;

public class FeaturesCollection {
    private final List<Feature> featureList = new LinkedList<>();

    public List<Feature> getFeatureList() {
        return featureList;
    }

    public void add(Feature feature) {
        featureList.add(feature);
    }
}
