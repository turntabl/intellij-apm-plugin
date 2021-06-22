package io.turntabl.utils.flame_graph_util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Profile {
    private Node rootNode;
    private List<String> stack = new ArrayList<>();

    public Profile(Node rootNode, List<String> stack) {
        this.rootNode = rootNode;
        this.stack = stack;
    }

    public Profile() {
    }

    public void openStack(){
        this.stack = new ArrayList<>();
    }

    public void closeStack(){
        this.rootNode.add(this.stack, stack.size()- 1, 1);
        this.stack = new ArrayList<>();
    }

    public void addFrame(String name){
        Pattern pattern = Pattern.compile("^\\(");
        Matcher matcher = pattern.matcher(name);

        if(!matcher.find()){
            name = name.replace(";", ":");
            name = name.replace("<", "");
            name = name.replace(">", "");
            name = name.replace("\\", "");
            name = name.replace("\"", "");

            int index = name.indexOf("(");
            if (index != -1){
                name = name.substring(0, index);
            }
            this.stack.add(name);
        }
    }

    public Node getRootNode() {
        return rootNode;
    }

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
    }

    public List<String> getStack() {
        return stack;
    }

    public void setStack(List<String> stack) {
        this.stack = stack;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "rootNode=" + rootNode +
                ", stack=" + stack +
                '}';
    }
}
