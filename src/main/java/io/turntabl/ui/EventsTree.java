package io.turntabl.ui;


import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.treeStructure.Tree;
import com.intellij.util.ui.components.BorderLayoutPanel;
import io.turntabl.ui.flight_recorder.*;
import io.turntabl.ui.java_application.JavaMonitorWaitPanel;
import io.turntabl.model.*;
import io.turntabl.ui.java_virtual_machine.JVMInfoEventPanel;
import org.jfree.data.xy.XYDatasetTableModel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EventsTree {
    private JBPanel treePanel;
    private JTree tree;
    private String rootNodeName = "Events by type";
    private String jvmNodeName = "Java Virtual Machine";
    private String[] jvmNodes = {"JVM Information"};
    private final NewRelicJavaProfilerToolWindow newRelicJavaProfilerToolWindow;
    private Map<String, JComponent> componentMap;
    private JVMInfoEventPanel jvmInfoEventPanel;

    public EventsTree(NewRelicJavaProfilerToolWindow newRelicJavaProfilerToolWindow) {
        this.newRelicJavaProfilerToolWindow = newRelicJavaProfilerToolWindow;
        componentMap = new HashMap<>();
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootNodeName);

        DefaultMutableTreeNode jvmNode = new DefaultMutableTreeNode(jvmNodeName);
        jvmInfoEventPanel = new JVMInfoEventPanel(new JVMInfoEventPanel.JVMInfoEventTableModel(Arrays.asList(new JVMInfoEvent())));
        for (int i = 0; i < jvmNodes.length; i++) {
            jvmNode.add(new DefaultMutableTreeNode(jvmNodes[i]));
            componentMap.put(jvmNodes[i], jvmInfoEventPanel.getJVMInfoEventComponent());
        }

        rootNode.add(jvmNode);

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
                newRelicJavaProfilerToolWindow.setEventSecondComponent(selected);
            } else {
                newRelicJavaProfilerToolWindow.clearEventPanelText();
                newRelicJavaProfilerToolWindow.updateEventPanelText(e.getPath().toString());

            }

        });

    }

    public JBPanel getEventsTree() {
        return this.treePanel;
    }

    public JTable getJVMInfoTable() {
        return jvmInfoEventPanel.getTable();
    }

    public void updateComponentMap(String key, JBPanel panel) {
        this.componentMap.put(key, panel);
    }
}
