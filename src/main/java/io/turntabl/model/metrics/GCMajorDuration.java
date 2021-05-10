<<<<<<< HEAD:src/main/java/io/turntabl/model/metrics/GCMajorDuration.java
package io.turntabl.model.metrics;
=======
package io.turntabl.model;
>>>>>>> main:src/main/java/io/turntabl/model/GCMajorDuration.java

import java.util.HashMap;

public class GCMajorDuration extends GarbageCollection{

    public GCMajorDuration(String name, String type, HashMap<String, Double> value, Long timestamp, Long interval, HashMap<String, String> attributes) {
        super(name, type, value, timestamp, interval, attributes);
    }

    public GCMajorDuration() {
    }

    @Override
    public String toString() {
        return "GCMajorDuration{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", timestamp=" + timestamp +
                ", interval=" + interval +
                ", attributes=" + attributes +
                '}';
    }
}
