package io.turntabl.ui;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.treeStructure.Tree;
import com.intellij.util.ui.components.BorderLayoutPanel;
import io.turntabl.ui.flame_graph.FlameGraphPanel;
import io.turntabl.ui.flame_graph.FlameGraphWithoutThreadNamesPanel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FlameGraphTree {
    private JBPanel treePanel;
    private JTree tree;
    private String rootNodeName = "Flame graph by view";
    private String[] viewNodes = {"With Thread Names", "Without Thread Names"};
    private final NewRelicJavaProfilerToolWindow newRelicJavaProfilerToolWindow;
    private Map<String, JComponent> componentMap;
    private FlameGraphPanel flameGraphPanel;
    private FlameGraphWithoutThreadNamesPanel flameGraphWithoutThreadNamesPanel;



    public FlameGraphTree(NewRelicJavaProfilerToolWindow newRelicJavaProfilerToolWindow) {
        this.newRelicJavaProfilerToolWindow = newRelicJavaProfilerToolWindow;
        componentMap = new HashMap<>();

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootNodeName);

        flameGraphPanel = new FlameGraphPanel();
        flameGraphWithoutThreadNamesPanel = new FlameGraphWithoutThreadNamesPanel();


        JBPanel[] flameGraphPanels = {flameGraphPanel.getComponent(), flameGraphWithoutThreadNamesPanel.getComponent()};

        for (int i = 0; i < viewNodes.length; i++) {
            rootNode.add(new DefaultMutableTreeNode(viewNodes[i]));
            componentMap.put(viewNodes[i], flameGraphPanels[i]);
        }

        treePanel = new BorderLayoutPanel(0, 0);

        tree = new Tree(rootNode);
        tree.setBackground(treePanel.getBackground());
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        treePanel.add(new JBScrollPane(tree), BorderLayout.CENTER);

        tree.getSelectionModel().addTreeSelectionListener(e -> {

            Object[] pathArray = e.getPath().getPath();
            String selectedNode = pathArray[pathArray.length - 1].toString();

            JComponent selected = componentMap.get(selectedNode);
            if (selected != null) {
                newRelicJavaProfilerToolWindow.setFlameGraphSecondComponent(selected);
            } else {
                newRelicJavaProfilerToolWindow.clearFlameGraphPanelText();
                newRelicJavaProfilerToolWindow.updateFlameGraphPanelText(e.getPath().toString());
            }
        });
    }

    public JBPanel getFlameGraphTree() {
        return this.treePanel;
    }

    public void updateComponentMap(String key, JBPanel panel) {
        this.componentMap.put(key, panel);
    }
}

