package io.turntabl.utils.flame_graph_util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Node {
    private String name;
    private int value;
    private HashMap<String, Node> children;

    public Node(String name, int value, HashMap<String, Node> children) {
        this.name = name;
        this.value = value;
        this.children = children;
    }

    public Node() {
    }

    public void add(List<String> stack, int index, int value) {
        this.value += value;

        if (index >= 0) {
            String head = stack.get(index);

            Node child = this.children.get(head);

            if (child == null) {
                child = new Node(head, 0, new HashMap<>());
                this.children.put(head, child);
            }
            child.add(stack, index - 1, value);
        }
    }

    public String MarshalJSON() throws JsonProcessingException {
        JsonNode jsonNode = convertToArray(this);

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(jsonNode);
    }

    public String MarshalIndentJSON() throws JsonProcessingException {
        JsonNode jsonNode = convertToArray(this);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        return mapper.writeValueAsString(jsonNode);
    }

    public JsonNode convertToArray(Node node) {
        List<Node> nodes = new ArrayList<>(node.getChildren().values());
        JsonNode rootJsonNode = new JsonNode(node.getName(), node.getValue());
        List<JsonNode> jsonNodes = new ArrayList<>();

        for (Node n : nodes) {
            JsonNode newNode = convertToArray(n);
            jsonNodes.add(newNode);
        }

        rootJsonNode.setChildren(jsonNodes);
        return rootJsonNode;
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

    public HashMap<String, Node> getChildren() {
        return children;
    }

    public void setChildren(HashMap<String, Node> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", children=" + children +
                '}';
    }
}
