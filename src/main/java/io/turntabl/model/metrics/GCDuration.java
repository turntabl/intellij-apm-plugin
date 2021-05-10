package io.turntabl.model.metrics;


import java.util.HashMap;

public class GCDuration extends GarbageCollection{

    public GCDuration(String name, String type, HashMap<String, Double> value, Long timestamp, Long interval, HashMap<String, String> attributes) {
        super(name, type, value, timestamp, interval, attributes);
    }

    public GCDuration() {
    }

    @Override
    public String toString() {
        return "GCDuration{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", timestamp=" + timestamp +
                ", interval=" + interval +
                ", attributes=" + attributes +
                '}';
    }
}
