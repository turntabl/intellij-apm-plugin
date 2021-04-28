package io.turntabl.ui;

import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.treeStructure.Tree;
import io.turntabl.ui.flight_recorder.MetaspaceSummaryDataspaceCommittedPanel;
import io.turntabl.ui.flight_recorder.MetaspaceSummaryMetaspaceUsedPanel;
import io.turntabl.ui.flight_recorder.ThreadContextSwitchRatePanel;
import io.turntabl.ui.flight_recorder.DataLossPanel;
import io.turntabl.ui.model.MetaspaceSummaryDataspaceCommitted;
import io.turntabl.ui.model.SummaryMetaspaceUsed;
import io.turntabl.ui.model.ThreadContextSwitchRate;
import io.turntabl.ui.model.DataLoss;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MetricsTree {
    private JPanel treePanel;
    private JTree tree;
    private String rootNodeName = "Metrics by type";
    private String flightRecorderNodeName = "Flight Recorder";
    private String jdkNodeName = "Java Development Kit";
    private String jvmNodeName = "Java Virtual Machine";
    private String osNodeName = "Operating System";
    private JScrollPane treeScrollPane;
    private String[] flightRecorderSubNodes = {"Flight Recording", "Recording Reason", "Recording Setting"};

    private String[] jdkSecurityNodes = {"Security Property Modification", "TLS Handshake", "X509 Certificate",
            "X509 Validation"};

    private String[] jvmNodes = {"Initial System Property", "JVM Information"};

    private String[] osNodes = {"Initial Environment Variable", "OS Information", "System Process"};
    private final NewRelicJavaProfilerToolWindow newRelicJavaProfilerToolWindow;
    private Map<String, JComponent> componentMap;

    public MetricsTree(NewRelicJavaProfilerToolWindow newRelicJavaProfilerToolWindow) {
        this.newRelicJavaProfilerToolWindow = newRelicJavaProfilerToolWindow;
        componentMap = new HashMap<>();
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootNodeName);

        DefaultMutableTreeNode flightRecorderNode = new DefaultMutableTreeNode(flightRecorderNodeName);
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

        //addThreadContextSwitchRatePanel to flight recorder branch node
        flightRecorderNode.add(new DefaultMutableTreeNode("Thread Context Switch Rate"));
        ThreadContextSwitchRatePanel threadContextSwitchRatePanel = new ThreadContextSwitchRatePanel(
                new ThreadContextSwitchRatePanel.ThreadContextSwitchRateTableModel(Arrays.asList(

                        new ThreadContextSwitchRate("jfr.ThreadCPULoad.user","gauge",0.004792887717485428,  Timestamp.valueOf("2014-01-01 00:00:00"), new HashMap<>() ),
                        new ThreadContextSwitchRate("jfr.ThreadCPULoad.user","gauge",0.004792986717485428,  Timestamp.valueOf("2014-01-01 01:00:00"), new HashMap<>()),
                        new ThreadContextSwitchRate("jfr.ThreadCPULoad.user","gauge",0.004793797717485428,  Timestamp.valueOf("2014-01-01 02:00:00"), new HashMap<>())
                )));

        componentMap.put("Thread Context Switch Rate", threadContextSwitchRatePanel.getThreadContextSwitchRateComponent());
        for (String nodeName : flightRecorderSubNodes) {
            flightRecorderNode.add(new DefaultMutableTreeNode(nodeName));
            componentMap.put(nodeName, threadContextSwitchRatePanel.getThreadContextSwitchRateComponent());

        }

        //add MetaspaceSummaryDataspaceCommittedPanel to flight recorder branch node
        flightRecorderNode.add(new DefaultMutableTreeNode("MetaspaceSummaryDataspaceCommitted"));
        MetaspaceSummaryDataspaceCommittedPanel summaryMetaspaceCommittedPanel = new MetaspaceSummaryDataspaceCommittedPanel(
                new MetaspaceSummaryDataspaceCommittedPanel.MetaspaceSummaryMetaspaceCommittedTableModel(Arrays.asList(

                        new MetaspaceSummaryDataspaceCommitted("jfr.MetaspaceSummary.metaspace.committed","gauge",0.25646382570266724, Timestamp.valueOf("2014-01-01 00:00:00"), new HashMap<>())
                        //new MetaspaceSummaryMetaspaceCommitted("jfr.ThreadCPULoad.user","gauge",0.004792987717485428,  Timestamp.valueOf("2014-01-01 00:00:00"), new HashMap<>()),
                        //new MetaspaceSummaryMetaspaceCommitted("jfr.ThreadCPULoad.user","gauge",0.004793787717485428,  Timestamp.valueOf("2014-01-01 00:00:00"), new HashMap<>())
                )));

        componentMap.put("Metaspace Summary Dataspace Committed", summaryMetaspaceCommittedPanel.getMetaspaceSummaryMetaspaceCommittedComponent());
        for (String nodeName : flightRecorderSubNodes) {
            flightRecorderNode.add(new DefaultMutableTreeNode(nodeName));
            componentMap.put(nodeName, summaryMetaspaceCommittedPanel.getMetaspaceSummaryMetaspaceCommittedComponent());

        }


        //add MetaspaceSummaryMetaspaceUsedPanel to flight recorder branch node
        flightRecorderNode.add(new DefaultMutableTreeNode("Summary Metaspace Used"));
        MetaspaceSummaryMetaspaceUsedPanel summaryMetaspaceUsedPanel = new MetaspaceSummaryMetaspaceUsedPanel(
                new MetaspaceSummaryMetaspaceUsedPanel.MetaspaceSummaryMetaspaceCommittedTableModel(Arrays.asList(

                        new SummaryMetaspaceUsed("jfr.MetaspaceSummary.metaspace.committed","gauge",0.25646382570266724, Timestamp.valueOf("2014-01-01 00:00:010"), new HashMap<>()),
                        new SummaryMetaspaceUsed("jfr.ThreadCPULoad.user","gauge",0.004792987717485428,  Timestamp.valueOf("2014-01-01 00:00:30"), new HashMap<>()),
                        new SummaryMetaspaceUsed("jfr.ThreadCPULoad.user","gauge",0.004793787717485428,  Timestamp.valueOf("2014-01-01 00:10:50"), new HashMap<>())
                )));

        componentMap.put("Summary Metaspace Used", summaryMetaspaceCommittedPanel.getMetaspaceSummaryMetaspaceCommittedComponent());
        for (String nodeName : flightRecorderSubNodes) {
            flightRecorderNode.add(new DefaultMutableTreeNode(nodeName));
            componentMap.put(nodeName, summaryMetaspaceCommittedPanel.getMetaspaceSummaryMetaspaceCommittedComponent());

        }

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
