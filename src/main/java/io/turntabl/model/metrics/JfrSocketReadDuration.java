<<<<<<< HEAD:src/main/java/io/turntabl/model/metrics/JfrSocketReadDuration.java
package io.turntabl.model.metrics;
=======
package io.turntabl.model;
>>>>>>> main:src/main/java/io/turntabl/model/JfrSocketReadDuration.java


import java.util.HashMap;

public class JfrSocketReadDuration extends JfrSocketRead{
    public JfrSocketReadDuration(String name, Long startTime, String type, HashMap<String, Double> value, int interval, HashMap<String, String> attributes) {
        super(name, startTime, type, value, interval, attributes);
    }

    public JfrSocketReadDuration() {
    }

    @Override
    public String toString() {
        return "JfrSocketReadDuration{" +
                "name='" + name + '\'' +
                ", startTime=" + startTime +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", interval=" + interval +
                ", attributes=" + attributes +
                '}';
    }
}
