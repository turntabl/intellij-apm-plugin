<<<<<<< HEAD:src/main/java/io/turntabl/model/metrics/ObjectAllocationInNewTLab.java
package io.turntabl.model.metrics;
=======
package io.turntabl.model;
>>>>>>> main:src/main/java/io/turntabl/model/ObjectAllocationInNewTLab.java

import java.util.HashMap;

public class ObjectAllocationInNewTLab extends  ObjectAllocation{
    public ObjectAllocationInNewTLab() {
    }

    public ObjectAllocationInNewTLab(String name, String type, HashMap<String, Double> value, Long timestamp, int interval, HashMap<String, String> attributes) {
        super(name, type, value, timestamp, interval, attributes);
    }

    @Override
    public String toString() {
        return "ObjectAllocationInNewTLab{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", timestamp=" + timestamp +
                ", interval=" + interval +
                ", attributes=" + attributes +
                '}';
    }
}
