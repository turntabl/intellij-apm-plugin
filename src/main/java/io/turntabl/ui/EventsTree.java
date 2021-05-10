package io.turntabl.ui;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.treeStructure.Tree;
import com.intellij.util.ui.components.BorderLayoutPanel;
import io.turntabl.model.events.JVMInfoEvent;
import io.turntabl.ui.java_virtual_machine.JVMInfoEventPanel;
import io.turntabl.model.events.JavaMonitorWait;
import io.turntabl.ui.java_virtual_machine.JavaMonitorWaitPanel;


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
    private String[] eventNodes = {"JVM Information", "JFR Method Sample", "Java Monitor Wait", "JFR Compilation"};
    private final NewRelicJavaProfilerToolWindow newRelicJavaProfilerToolWindow;
    private Map<String, JComponent> componentMap;
    private JVMInfoEventPanel jvmInfoEventPanel;
    private JavaMonitorWaitPanel javaMonitorWaitPanel;

    public EventsTree(NewRelicJavaProfilerToolWindow newRelicJavaProfilerToolWindow) {
        this.newRelicJavaProfilerToolWindow = newRelicJavaProfilerToolWindow;
        componentMap = new HashMap<>();

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootNodeName);

        jvmInfoEventPanel = new JVMInfoEventPanel(new JVMInfoEventPanel.JVMInfoEventTableModel(Arrays.asList(new JVMInfoEvent())));
        javaMonitorWaitPanel = new JavaMonitorWaitPanel(new JavaMonitorWaitPanel.JavaMonitorWaitTableModel(Arrays.asList(new JavaMonitorWait())));

        JBPanel[] eventsPanel = {jvmInfoEventPanel.getJVMInfoEventComponent(), javaMonitorWaitPanel.getJavaMonitorWaitComponent()};//add other panels here...........
        //add events nodes to root node
        for (int i = 0; i < eventNodes.length; i++) {
            rootNode.add(new DefaultMutableTreeNode(eventNodes[i]));
            if ((i + 1) <= eventsPanel.length) {
                componentMap.put(eventNodes[i], eventsPanel[i]);
            } else {
                componentMap.put(eventNodes[i], new JBPanel());
            }
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

    public JTable getJavaMonitorWaitTable() {
        return javaMonitorWaitPanel.getTable();
    }

    public void updateComponentMap(String key, JBPanel panel) {
        this.componentMap.put(key, panel);
    }
}