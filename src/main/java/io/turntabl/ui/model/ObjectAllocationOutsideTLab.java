package io.turntabl.ui.model;

import java.util.HashMap;

public class ObjectAllocationOutsideTLab extends ObjectAllocation{
    public ObjectAllocationOutsideTLab() {
    }

    public ObjectAllocationOutsideTLab(String name, String type, HashMap<String, Double> value, Long timestamp, int interval, HashMap<String, String> attributes) {
        super(name, type, value, timestamp, interval, attributes);
    }

    @Override
    public String toString() {
        return "ObjectAllocationOutsideTLab{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", timestamp=" + timestamp +
                ", interval=" + interval +
                ", attributes=" + attributes +
                '}';
    }
}

