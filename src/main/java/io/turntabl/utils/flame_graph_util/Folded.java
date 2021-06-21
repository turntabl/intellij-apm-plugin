package io.turntabl.utils.flame_graph_util;

import java.util.*;

public class Folded {
    public static Profile parseFolded(List<String> foldedStack) {
        Node rootNode = new Node("root", 0, new HashMap<>());

        List<String> newStack = new ArrayList<>();

        for (String line : foldedStack) {
            int sep = line.lastIndexOf(" ");

            String s = line.substring(0, sep);
            String v = line.substring(sep+1);

            String[] stack = s.split(";");

            Collections.addAll(newStack, stack);
            Collections.reverse(newStack);

            int i = Integer.parseInt(v);

            rootNode.add(newStack, newStack.size() - 1, i);
            newStack.clear();
        }

        return new Profile(rootNode, newStack);
    }
}
