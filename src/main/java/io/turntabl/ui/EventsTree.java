package io.turntabl.ui;


import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.treeStructure.Tree;
import com.intellij.util.Time;
import io.turntabl.ui.flight_recorder.DataLossPanel;
import io.turntabl.ui.flight_recorder.JfrCompilationPanel;
import io.turntabl.ui.flight_recorder.JvmInformationPanel;
import io.turntabl.ui.model.DataLoss;
import io.turntabl.ui.model.JfrCompilation;
import io.turntabl.ui.model.JvmInformation;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EventsTree {
    private JPanel treePanel;
    private JTree tree;
    private String rootNodeName = "Events by type";
    private String flightRecorderNodeName = "Flight Recorder";
    private String javaAppNodeName = "Java Application";
    private String jdkNodeName = "Java Development Kit";
    private String jvmNodeName = "Java Virtual Machine";
    private String osNodeName = "Operating System";
    private JScrollPane treeScrollPane;
    private String[] flightRecorderSubNodes = {"Flight Recording", "Recording Reason", "Recording Setting"};

    private String[] javaAppStatisticsNodes = {"Class Loader Statistics", "Class Loading Statistics",
            "Exception Statistics", "Java Thread Statistics"};

    private String[] javaAppSubNodes = {"Allocation in new TLAB", "Allocation outside TLAB", "File Force",
            "File Read", "File Write", "Java Error", "Java Exception", "Java Monitor Blocked", "Java Monitor Inflated",
            "Java Monitor Wait", "Java Thread End", "Java Thread Park", "Java Thread Sleep", "Java Thread Start",
            "Socket Read", "Socket Write"};

    private String[] jdkSecurityNodes = {"Security Property Modification", "TLS Handshake", "X509 Certificate",
            "X509 Validation"};

    private String[] jvmNodes = {"Initial System Property", "JVM Information"};

    private String[] osNodes = {"Initial Environment Variable", "OS Information", "System Process"};
    private final NewRelicJavaProfilerToolWindow newRelicJavaProfilerToolWindow;
    private Map<String, JComponent> componentMap;

    public EventsTree(NewRelicJavaProfilerToolWindow newRelicJavaProfilerToolWindow) {
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

        //add jvminfo noode to flight recorder branch node
        flightRecorderNode.add(new DefaultMutableTreeNode("Jvm Information"));
        JvmInformationPanel jvmInformation = new JvmInformationPanel(
                new JvmInformationPanel.JvmInformationTableModel(Arrays.asList(
                        new JvmInformation(new java.util.Date(),"jvmproperty","propertyValue","intruName", "hostName", "collectorName", "Intruprovider" ),
                        new  JvmInformation(new java.util.Date(),"jvmproperty","propertyValue","intruName", "hostName", "collectorName", "Intruprovider" ),
                        new JvmInformation(new java.util.Date(),"jvmproperty","propertyValue","intruName", "hostName", "collectorName", "Intruprovider" )
                )));

        componentMap.put("Jvm Information", jvmInformation.getJvmInformationComponent());
        for (String nodeName : flightRecorderSubNodes) {
            flightRecorderNode.add(new DefaultMutableTreeNode(nodeName));
            componentMap.put(nodeName, jvmInformation.getJvmInformationComponent());

        }

        //add jvmCompilation noode to flight recorder branch node
        flightRecorderNode.add(new DefaultMutableTreeNode("Jvm Compilation"));
        JfrCompilationPanel jfrCompilation= new JfrCompilationPanel(
                new JfrCompilationPanel.JfrCompilationTableModel(Arrays.asList(
                        new JfrCompilation(new java.util.Date(), 2,
                                "instrumentationName", "hostHstname", "treadName","collectorName","desc",true ,"instruProvider"),
                        new JfrCompilation(new java.util.Date(), 3,
                                "instrumentationName", "hostHostname", "treadName","collectorName","desc",true ,"instruProvider"),
                        new JfrCompilation(new java.util.Date(),
                                4,
                                "instrumentationName",
                                "hostHostname", "threadName","collectorName","desc",true ,"instruProvider")
                )));

        componentMap.put("Jvm Compilation", jfrCompilation.getJfrCompilationComponent());
        for (String nodeName : flightRecorderSubNodes) {
            flightRecorderNode.add(new DefaultMutableTreeNode(nodeName));
            componentMap.put(nodeName, jfrCompilation.getJfrCompilationComponent());

        }

        //add statistics branch node and its leaf nodes
        DefaultMutableTreeNode javaAppStatisticsNode = new DefaultMutableTreeNode("Statistics");
        for (String nodeName : javaAppStatisticsNodes) {
            javaAppStatisticsNode.add(new DefaultMutableTreeNode(nodeName));
        }
        javaApplicationNode.add(javaAppStatisticsNode);


        DefaultMutableTreeNode jdkSecurityNode = new DefaultMutableTreeNode("Security");
        for (String nodeName : jdkSecurityNodes) {
            jdkSecurityNode.add(new DefaultMutableTreeNode(nodeName));
        }
        jdkNode.add(jdkSecurityNode);

        for (String nodeName : jvmNodes) {
            jvmNode.add(new DefaultMutableTreeNode(nodeName));
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

        treeScrollPane = new JBScrollPane(tree);

        treePanel.add(treeScrollPane, BorderLayout.CENTER);

        tree.getSelectionModel().addTreeSelectionListener(e -> {

            Object[] pathArray = e.getPath().getPath();
            String selectedNode = pathArray[pathArray.length - 1].toString();

            JComponent selected = componentMap.get(selectedNode);
            if (selected != null) {
                System.out.println("not null");
                newRelicJavaProfilerToolWindow.setEventSecondComponent(selected);
            } else {
                System.out.println("null");
                newRelicJavaProfilerToolWindow.clearEventPanelText();
                newRelicJavaProfilerToolWindow.updateEventPanelText(e.getPath().toString());

            }

        });

    }

    public JPanel getEventTree() {
        return this.treePanel;
    }

}
