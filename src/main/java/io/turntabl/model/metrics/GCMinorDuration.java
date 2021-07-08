package io.turntabl.model.metrics;

import java.util.HashMap;

public class GCMinorDuration extends GarbageCollection {
    public GCMinorDuration(String name, String type, HashMap<String, Double> value, Long timestamp, int interval, HashMap<String, String> attributes) {
        super(name, type, value, timestamp, interval, attributes);
    }

    public GCMinorDuration() {
    }

    @Override
    public String toString() {
        return "GCMinorDuration{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", timestamp=" + timestamp +
                ", interval=" + interval +
                ", attributes=" + attributes +
                '}';
    }
}
