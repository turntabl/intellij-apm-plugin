package io.turntabl.utils.flame_graph_util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Folded {
    public static Profile parseFolded(File file) throws FileNotFoundException {
        Node rootNode = new Node("root", 0, new HashMap<>());

        Scanner scanner = new Scanner(file);
        List<String> newStack = new ArrayList<>();

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
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
        scanner.close();

        return new Profile(rootNode, newStack);
    }
}
