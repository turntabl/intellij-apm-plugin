package io.turntabl.model.metrics;

import java.util.HashMap;

public class JfrSocketReadDuration extends JfrSocketRead {
    public JfrSocketReadDuration(String name, Long timestamp, String type, HashMap<String, Double> value, int interval, HashMap<String, String> attributes) {
        super(name, timestamp, type, value, interval, attributes);
    }

    public JfrSocketReadDuration() {
    }

    @Override
    public String toString() {
        return "JfrSocketReadDuration{" +
                "name='" + name + '\'' +
                ", timestamp=" + timestamp +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", interval=" + interval +
                ", attributes=" + attributes +
                '}';
    }
}
