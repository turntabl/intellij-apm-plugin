package io.turntabl.ui;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.treeStructure.Tree;
import com.intellij.util.ui.components.BorderLayoutPanel;
import io.turntabl.ui.metric_graph.CpuLoadGraphPanel;
import io.turntabl.ui.metric_graph.ThreadContextSwitchratePanel;
import io.turntabl.ui.metric_graph.HeapSummaryAfterGCPanel;
import io.turntabl.ui.metric_graph.HeapSummaryBeforeGCPanel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class MetricsGraphTree {
    private final NewRelicJavaProfilerToolWindow newRelicJavaProfilerToolWindow;
    private JBPanel treePanel;
    private JTree tree;
    private String rootNodeName = "Graphs by metric";
    private String[] viewNodes = {"CPU Load Graph", "Thread ContextSwitch Rate Graph"};
    private Map<String, JComponent> componentMap;
    private CpuLoadGraphPanel cpuGraphPanel;
    private ThreadContextSwitchratePanel threadContextSwitchratePanel;
    private HeapSummaryBeforeGCPanel heapSummaryBeforeGCPanel;
    private HeapSummaryAfterGCPanel heapSummaryAfterGCPanel;

    public MetricsGraphTree(NewRelicJavaProfilerToolWindow newRelicJavaProfilerToolWindow) {
        this.newRelicJavaProfilerToolWindow = newRelicJavaProfilerToolWindow;
        componentMap = new HashMap<>();

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootNodeName);


        DefaultMutableTreeNode heapSummaryNode = new DefaultMutableTreeNode("GC Heap Summary");
        String[] heapNodes = {"Before GC", "After GC"};

        heapSummaryBeforeGCPanel = new HeapSummaryBeforeGCPanel();
        heapSummaryAfterGCPanel = new HeapSummaryAfterGCPanel();

        JComponent[] heapComponents = {heapSummaryBeforeGCPanel.getComponent(), heapSummaryAfterGCPanel.getComponent()};

        for (int i = 0; i < heapNodes.length; i++) {
            heapSummaryNode.add(new DefaultMutableTreeNode(heapNodes[i]));
            componentMap.put(heapNodes[i], heapComponents[i]);
        }
        rootNode.add(heapSummaryNode);


        cpuGraphPanel = new CpuLoadGraphPanel();
        threadContextSwitchratePanel = new ThreadContextSwitchratePanel();
        JComponent[] metricGraphPanels = {cpuGraphPanel.getComponent(), threadContextSwitchratePanel.getComponent()};

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
}
