package io.turntabl.utils.flame_graph_util;

import java.util.ArrayList;
import java.util.List;

public class JsonNode {
    private String name;
    private int value;
    private List<JsonNode> children = new ArrayList<>();

    public JsonNode(String name, int value, List<JsonNode> children) {
        this.name = name;
        this.value = value;
        this.children = children;
    }

    public JsonNode(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public JsonNode() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<JsonNode> getChildren() {
        return children;
    }

    public void setChildren(List<JsonNode> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "JsonNode{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", children=" + children +
                '}';
    }
}
