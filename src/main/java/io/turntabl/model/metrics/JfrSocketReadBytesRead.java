package io.turntabl.model.metrics;

import java.util.HashMap;

public class JfrSocketReadBytesRead extends JfrSocketRead {
    public JfrSocketReadBytesRead(String name, Long timestamp, String type, HashMap<String, Double> value, int interval, HashMap<String, String> attributes) {
        super(name, timestamp, type, value, interval, attributes);
    }

    public JfrSocketReadBytesRead() {
    }

    @Override
    public String toString() {
        return "JfrSocketReadBytesRead{" +
                "name='" + name + '\'' +
                ", timestamp=" + timestamp +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", interval=" + interval +
                ", attributes=" + attributes +
                '}';
    }
}
