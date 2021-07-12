package io.turntabl.ui;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.treeStructure.Tree;
import com.intellij.util.ui.components.BorderLayoutPanel;
import io.turntabl.ui.metric_graph.CpuLoadGraphPanel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MetricsGraphTree {
    private JBPanel treePanel;
    private JTree tree;
    private String rootNodeName = "Graphs by metric";
    private String[] viewNodes = {"CPU Load Graph"};
    private final NewRelicJavaProfilerToolWindow newRelicJavaProfilerToolWindow;
    private Map<String, JComponent> componentMap;
    private CpuLoadGraphPanel cpuGraphPanel;

    public MetricsGraphTree(NewRelicJavaProfilerToolWindow newRelicJavaProfilerToolWindow) {
        this.newRelicJavaProfilerToolWindow = newRelicJavaProfilerToolWindow;
        componentMap = new HashMap<>();

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootNodeName);

        cpuGraphPanel = new CpuLoadGraphPanel();

        JComponent[] metricGraphPanels = {cpuGraphPanel.getComponent()};

        for (int i = 0; i < viewNodes.length; i++) {
            rootNode.add(new DefaultMutableTreeNode(viewNodes[i]));
            componentMap.put(viewNodes[i], metricGraphPanels[i]);
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
                newRelicJavaProfilerToolWindow.setMetricsGraphSecondComponent(selected);
            } else {
                newRelicJavaProfilerToolWindow.clearMetricsGraphPanelText();
                newRelicJavaProfilerToolWindow.updateMetricsGraphPanelText(e.getPath().toString());
            }
        });

    }

    public Component getMetricsGraphTree() {
        return this.treePanel;
    }

    public void updateComponentMap(String key, JComponent panel) {
        this.componentMap.put(key, panel);
    }
}
