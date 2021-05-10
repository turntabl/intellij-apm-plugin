package io.turntabl.model;

import java.util.HashMap;

public class JfrSocketReadBytesRead extends JfrSocketRead{
    public JfrSocketReadBytesRead(String name, Long startTime, String type, HashMap<String, Double> value, int interval, HashMap<String, String> attributes) {
        super(name, startTime, type, value, interval, attributes);
    }

    public JfrSocketReadBytesRead() {
    }

    @Override
    public String toString() {
        return "JfrSocketReadBytesRead{" +
                "name='" + name + '\'' +
                ", startTime=" + startTime +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", interval=" + interval +
                ", attributes=" + attributes +
                '}';
    }
}
