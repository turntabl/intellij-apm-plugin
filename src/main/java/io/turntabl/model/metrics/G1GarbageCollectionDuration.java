<<<<<<< HEAD:src/main/java/io/turntabl/model/metrics/G1GarbageCollectionDuration.java
package io.turntabl.model.metrics;
=======
package io.turntabl.model;
>>>>>>> main:src/main/java/io/turntabl/model/G1GarbageCollectionDuration.java

import java.util.HashMap;


public class G1GarbageCollectionDuration extends GarbageCollection{

    public G1GarbageCollectionDuration(String name, String type, HashMap<String, Double> value, Long timestamp, Long interval, HashMap<String, String> attributes) {
        super(name, type, value, timestamp, interval, attributes);
    }

    public G1GarbageCollectionDuration() {
    }

    @Override
    public String toString() {
        return "G1GarbageCollectionDuration{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", timestamp=" + timestamp +
                ", interval=" + interval +
                ", attributes=" + attributes +
                '}';
    }
}
