<<<<<<< HEAD:src/main/java/io/turntabl/model/metrics/GCDuration.java
package io.turntabl.model.metrics;
=======
package io.turntabl.model;
>>>>>>> main:src/main/java/io/turntabl/model/GCDuration.java

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
