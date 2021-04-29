package io.turntabl.ui;

import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.treeStructure.Tree;
import io.turntabl.ui.flight_recorder.DataLossPanel;
import io.turntabl.ui.java_application.statistics.ThreadAllocationStatisticsPanel;
import io.turntabl.ui.java_virtual_machine.GarbageCollectionPanel;
import io.turntabl.ui.model.DataLoss;
import io.turntabl.ui.model.GarbageCollection;
import io.turntabl.ui.model.ThreadAllocationStatistics;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MetricsTree {
    private JPanel treePanel;
    private JTree tree;
    private String rootNodeName = "Metrics by type";
    private String flightRecorderNodeName = "Flight Recorder";
    private String javaAppNodeName = "Java Application";
    private String jdkNodeName = "Java Development Kit";
    private String jvmNodeName = "Java Virtual Machine";
    private String osNodeName = "Operating System";
    private JScrollPane treeScrollPane;
    private String[] flightRecorderSubNodes = {"Flight Recording", "Recording Reason", "Recording Setting"};

    private String[] jdkSecurityNodes = {"Security Property Modification", "TLS Handshake", "X509 Certificate",
            "X509 Validation"};

    private String[] jvmNodes = {"Initial System Property", "JVM Information","Garbage Collection"};
    private String[] javaAppStatisticsNodes = {"Class Loader Statistics", "Class Loading Statistics",
            "Exception Statistics", "Thread Allocated Statistics"};

    private String[] osNodes = {"Initial Environment Variable", "OS Information", "System Process"};
    private final NewRelicJavaProfilerToolWindow newRelicJavaProfilerToolWindow;
    private Map<String, JComponent> componentMap;

    public MetricsTree(NewRelicJavaProfilerToolWindow newRelicJavaProfilerToolWindow) {
        this.newRelicJavaProfilerToolWindow = newRelicJavaProfilerToolWindow;
        componentMap = new HashMap<>();
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootNodeName);

        DefaultMutableTreeNode flightRecorderNode = new DefaultMutableTreeNode(flightRecorderNodeName);
        DefaultMutableTreeNode javaApplicationNode = new DefaultMutableTreeNode(javaAppNodeName);
        DefaultMutableTreeNode jdkNode = new DefaultMutableTreeNode(jdkNodeName);
        DefaultMutableTreeNode jvmNode = new DefaultMutableTreeNode(jvmNodeName);
        DefaultMutableTreeNode osNode = new DefaultMutableTreeNode(osNodeName);

        //add sub node to flight recorder branch node
        flightRecorderNode.add(new DefaultMutableTreeNode("Data Loss"));
        DataLossPanel dataLoss = new DataLossPanel(
                new DataLossPanel.DataLossTableModel(Arrays.asList(
                        new DataLoss("2021-06-01 11:08:12:20", "10", "10"),
                        new DataLoss("2021-06-01 11:08:12:21", "15", "25"),
                        new DataLoss("2021-06-01 11:08:12:22", "20", "45")
                )));

        componentMap.put("Data Loss", dataLoss.getDataLossComponent());
        for (String nodeName : flightRecorderSubNodes) {
            flightRecorderNode.add(new DefaultMutableTreeNode(nodeName));
            componentMap.put(nodeName, dataLoss.getDataLossComponent());

        }

        ThreadAllocationStatisticsPanel threadAllocationStatisticsPanel = new ThreadAllocationStatisticsPanel(
                new ThreadAllocationStatisticsPanel.ThreadAllocationStatisticsTableModel(Arrays.asList(
                        new ThreadAllocationStatistics("2021-06-01 11:08:12:20", "18.4 MiB", "Main", new HashMap<String, String>())

                )));

        componentMap.put("Thread Allocated Statistics", threadAllocationStatisticsPanel.getThreadAllocationStatisticsComponent());
        //add statistics branch node and its leaf nodes
        DefaultMutableTreeNode javaAppStatisticsNode = new DefaultMutableTreeNode("Statistics");
        for (String nodeName : javaAppStatisticsNodes) {
            javaAppStatisticsNode.add(new DefaultMutableTreeNode(nodeName));
            componentMap.put(nodeName, threadAllocationStatisticsPanel.getThreadAllocationStatisticsComponent());
        }
        javaApplicationNode.add(javaAppStatisticsNode);

        DefaultMutableTreeNode jdkSecurityNode = new DefaultMutableTreeNode("Security");
        for (String nodeName : jdkSecurityNodes) {
            jdkSecurityNode.add(new DefaultMutableTreeNode(nodeName));
        }
        jdkNode.add(jdkSecurityNode);

        GarbageCollectionPanel garbageCollectionPanel = new GarbageCollectionPanel(
                new GarbageCollectionPanel.GarbageCollectionTableModel(Arrays.asList(
                        new GarbageCollection("jfr.GarbageCollection.majorDuration","summary",new HashMap<String,String>(), 1619441613596L,-1619441613596L,new HashMap<String, String>())
                ))
        );
        componentMap.put("Garbage Collection",garbageCollectionPanel.getGarbageCollectionComponent());
        for (String nodeName : jvmNodes) {
            jvmNode.add(new DefaultMutableTreeNode(nodeName));
            componentMap.put(nodeName,garbageCollectionPanel.getGarbageCollectionComponent());
        }

        for (String nodeName : osNodes) {
            osNode.add(new DefaultMutableTreeNode(nodeName));
        }

        rootNode.add(flightRecorderNode);
        rootNode.add(javaApplicationNode);
        rootNode.add(jdkNode);
        rootNode.add(jvmNode);
        rootNode.add(osNode);

        treePanel = new JPanel(new BorderLayout());
        tree = new Tree(rootNode);
        tree.setBackground(treePanel.getBackground());
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        treePanel.add(new JBScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);

        tree.getSelectionModel().addTreeSelectionListener(e -> {

            Object[] pathArray = e.getPath().getPath();
            String selectedNode = pathArray[pathArray.length - 1].toString();

            JComponent selected = componentMap.get(selectedNode);
            if (selected != null) {
                System.out.println("not null");
                newRelicJavaProfilerToolWindow.setMetricsSecondComponent(selected);
            } else {
                System.out.println("null");
                newRelicJavaProfilerToolWindow.clearMetricsPanelText();
                newRelicJavaProfilerToolWindow.updateMetricsPanelText(e.getPath().toString());

            }

        });

    }

    public JPanel getMetricsTree() {
        return this.treePanel;
    }
}
