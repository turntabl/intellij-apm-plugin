package io.turntabl.ui;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.treeStructure.Tree;
import com.intellij.util.ui.components.BorderLayoutPanel;
import io.turntabl.ui.flight_recorder.JfrSocketReadBytesReadPanel;
import io.turntabl.ui.flight_recorder.JfrSocketReadDurationPanel;
import io.turntabl.ui.flight_recorder.SummaryMetaspacePanel;
import io.turntabl.ui.flight_recorder.ThreadContextSwitchRatePanel;
import io.turntabl.ui.java_application.ObjectAllocationInNewTLabPanel;
import io.turntabl.ui.java_application.ObjectAllocationOutsideTLabPanel;
import io.turntabl.ui.java_application.statistics.ThreadAllocationStatisticsPanel;
import io.turntabl.ui.java_virtual_machine.garbage_collection.*;
import io.turntabl.ui.model.*;
import io.turntabl.ui.operating_system.CpuLoadPanel;
import io.turntabl.ui.operating_system.GcHeapSummaryPanel;
import io.turntabl.ui.operating_system.ThreadCpuLoadPanel;
import org.jfree.data.xy.XYDatasetTableModel;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MetricsTree {
    private JBPanel treePanel;
    private JTree tree;
    private String rootNodeName = "Metrics by type";
    private String flightRecorderNodeName = "Flight Recorder";
    private String javaAppNodeName = "Java Application";
    private String jvmNodeName = "Java Virtual Machine";
    private String osNodeName = "Operating System";
    private CpuGraph cpuGraph;
    private final NewRelicJavaProfilerToolWindow newRelicJavaProfilerToolWindow;
    private Map<String, JComponent> componentMap;
    private CpuLoadPanel cpuLoadPanel;
    private ObjectAllocationInNewTLabPanel objectAllocationInNewTLabPanel;

    public MetricsTree(NewRelicJavaProfilerToolWindow newRelicJavaProfilerToolWindow) {
        this.newRelicJavaProfilerToolWindow = newRelicJavaProfilerToolWindow;
        componentMap = new HashMap<>();
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootNodeName);

        DefaultMutableTreeNode flightRecorderNode = new DefaultMutableTreeNode(flightRecorderNodeName);
        DefaultMutableTreeNode javaApplicationNode = new DefaultMutableTreeNode(javaAppNodeName);
        DefaultMutableTreeNode jvmNode = new DefaultMutableTreeNode(jvmNodeName);
        DefaultMutableTreeNode osNode = new DefaultMutableTreeNode(osNodeName);

        // define a sub node for flight recorder
        DefaultMutableTreeNode socketNode = new DefaultMutableTreeNode("JFR Socket Read");
        // define sub nodes of socket branch
        String[] socketNodes = {"Bytes Read", "Duration"};
        // defining table info for socket branch sub nodes
        JfrSocketReadBytesReadPanel jfrSocketReadBytesReadPanel = new JfrSocketReadBytesReadPanel(
                new JfrSocketReadBytesReadPanel.JfrSocketReadBytesReadTableModel(Arrays.asList(
                        new JfrSocketReadBytesRead("jfr.SocketRead.bytesRead", 1619441645442L, "summary", new HashMap<>(), 46, new HashMap<>())
                ))
        );

        // defining table info for socket branch sub nodes
        JfrSocketReadDurationPanel jfrSocketReadDurationPanel = new JfrSocketReadDurationPanel(
                new JfrSocketReadDurationPanel.JfrSocketReadDurationTableModel(Arrays.asList(
                        new JfrSocketReadDuration("jfr.SocketRead.duration", 1619441645442L, "summary", new HashMap<>(), 50, new HashMap<>())
                ))
        );

        // collect socket branch node components into an array
        JBPanel[] socketComponents = {jfrSocketReadBytesReadPanel.getJfrSocketReadBytesReadComponent(),
                jfrSocketReadDurationPanel.getJfrSocketReadDurationComponent()};

        for (int i = 0; i < socketNodes.length; i++) {
            socketNode.add(new DefaultMutableTreeNode(socketNodes[i]));
            componentMap.put(socketNodes[i], socketComponents[i]);
        }
        flightRecorderNode.add(socketNode);

        // flight recorder sub nodes without children
        String[] flightRecorderNodes = {"Thread Context Switch Rate", "Summary Metaspace"};
        ThreadContextSwitchRatePanel threadContextSwitchRatePanel = new ThreadContextSwitchRatePanel(
                new ThreadContextSwitchRatePanel.ThreadContextSwitchRateTableModel(Arrays.asList(
                        new ThreadContextSwitchRate("jfr.ThreadCPULoad.user", "gauge", 0.004792887717485428, 41233434L, new HashMap<>())
                )));

        SummaryMetaspacePanel summaryMetaspacePanel = new SummaryMetaspacePanel(
                new SummaryMetaspacePanel.SummaryMetaspaceTableModel(Arrays.asList(
                        new SummaryMetaspace("jfr.MetaspaceSummary.dataSpace.committed", "type", 0.209283, 0.209283, 0.209283, 28364347L, new HashMap<>())
                )));

        // define components for flight recorder sub nodes without children
        JBPanel[] flightRecorderComponents = {threadContextSwitchRatePanel.getThreadContextSwitchRateComponent(),
                summaryMetaspacePanel.getSummaryMetaspaceComponent()};
        // add sub nodes to flight recorder
        for (int i = 0; i < flightRecorderNodes.length; i++) {
            flightRecorderNode.add(new DefaultMutableTreeNode(flightRecorderNodes[i]));
            componentMap.put(flightRecorderNodes[i], flightRecorderComponents[i]);
        }

        // define java application sub nodes
        // define statistics sub node
        DefaultMutableTreeNode javaAppStatisticsNode = new DefaultMutableTreeNode("Statistics");

        // define sub nodes for statistics sub node
        String[] javaAppStatisticsNodes = {"Thread Allocated Statistics"};
        // define table for statistics sub node
        ThreadAllocationStatisticsPanel threadAllocationStatisticsPanel = new ThreadAllocationStatisticsPanel(
                new ThreadAllocationStatisticsPanel.ThreadAllocationStatisticsTableModel(Arrays.asList(
                        new ThreadAllocationStatistics("2021-06-01 11:08:12:20", "18.4 MiB", 70.52, 5376373L, new HashMap<String, String>())

                )));

        // define statistics components
        JBPanel[] statisticsComponent = {threadAllocationStatisticsPanel.getThreadAllocationStatisticsComponent()};
        // add sub nodes to statistics sub node
        for (int i = 0; i < javaAppStatisticsNodes.length; i++) {
            javaAppStatisticsNode.add(new DefaultMutableTreeNode(javaAppStatisticsNodes[i]));
            componentMap.put(javaAppStatisticsNodes[i], statisticsComponent[i]);
        }

        // define sub nodes without children for java application
        String[] javaAppSubNodes = {"Object Allocation in new TLAB", "Object Allocation outside TLAB"};
        objectAllocationInNewTLabPanel = new ObjectAllocationInNewTLabPanel(
                new ObjectAllocationInNewTLabPanel.ObjectAllocationInNewTLabTableModel(Arrays.asList(
                        new ObjectAllocationInNewTLab("jfr allocation", "Summary", new HashMap<>(), 16667896L, 50, new HashMap<>())
                )));

        ObjectAllocationOutsideTLabPanel objectAllocationOutsideTLabPanel = new ObjectAllocationOutsideTLabPanel(
                new ObjectAllocationOutsideTLabPanel.ObjectAllocationOutsideTLabTableModel(Arrays.asList(
                        new ObjectAllocationOutsideTLab("jfr allocation", "Summary", new HashMap<>(), 16667896L, 51, new HashMap<>())
                )));

        // define components for java application sub nodes without children
        JBPanel[] javaAppComponents = {objectAllocationInNewTLabPanel.getObjectAllocationInNewTLabComponent(), objectAllocationOutsideTLabPanel.getObjectAllocationOutsideTLabComponent()};
        // adding sub nodes to java application root node
        javaApplicationNode.add(javaAppStatisticsNode);
        for (int i = 0; i < javaAppSubNodes.length; i++) {
            javaApplicationNode.add(new DefaultMutableTreeNode(javaAppSubNodes[i]));
            componentMap.put(javaAppSubNodes[i], javaAppComponents[i]);
        }
        // define GC sub nodes
        DefaultMutableTreeNode jvmSubNode = new DefaultMutableTreeNode("Garbage Collections");
        // define sub nodes of socket branch
        String[] jvmSubNodes = {"G1 GC Duration", "GC Duration", "GC Longest Pause", "GC Major Duration", "GC Minor Duration"};

        // defining table info for GC branch sub nodes
        G1GarbageCollectionDurationPanel g1GarbageCollectionDurationPanel = new G1GarbageCollectionDurationPanel(
                new G1GarbageCollectionDurationPanel.G1GarbageCollectionDurationTableModel(Arrays.asList(
                        new G1GarbageCollectionDuration("jfr.G1GarbageCollection.duration", "summary", new HashMap<String, String>(), 1619441613596L, -1619441613596L, new HashMap<String, String>())
                ))
        );

        // defining table info for GC branch sub nodes
        GCDurationPanel gcDurationPanel = new GCDurationPanel(
                new GCDurationPanel.GCDurationTableModel(Arrays.asList(
                        new GCDuration("jfr.GarbageCollection.duration", "summary", new HashMap<String, String>(), 1619441613596L, -1619441613596L, new HashMap<String, String>())
                ))
        );
        // defining table info for GC branch sub nodes
        GCLongestPausePanel gcLongestPausePanel = new GCLongestPausePanel(
                new GCLongestPausePanel.GCLongestPauseTableModel(Arrays.asList(
                        new GCLongestPause("jfr.GarbageCollection.longestPause", "summary", new HashMap<String, String>(), 1619441613596L, -1619441613596L, new HashMap<String, String>())
                ))
        );

        // defining table info for GC branch sub nodes
        GCMajorDurationPanel gcMajorDurationPanel = new GCMajorDurationPanel(
                new GCMajorDurationPanel.GCMajorDurationTableModel(Arrays.asList(
                        new GCMajorDuration("jfr.GarbageCollection.MajorDuration", "summary", new HashMap<String, String>(), 1619441613596L, -1619441613596L, new HashMap<String, String>())
                ))
        );

        // defining table info for GC branch sub nodes
        GCMinorDurationPanel gcMinorDurationPanel = new GCMinorDurationPanel(
                new GCMinorDurationPanel.GCMinorDurationTableModel(Arrays.asList(
                        new GCMinorDuration("jfr.GarbageCollection.MinorDuration", "summary", new HashMap<String, String>(), 1619441613596L, -1619441613596L, new HashMap<String, String>())
                ))
        );

        JBPanel[] gcComponents = {g1GarbageCollectionDurationPanel.getG1GarbageCollectionDurationComponent(),
                gcDurationPanel.getGCDurationComponent(),
                gcLongestPausePanel.getGCLongestPauseComponent(),
                gcMajorDurationPanel.getGCMajorDurationComponent(),
                gcMinorDurationPanel.getGCMinorDurationComponent()
        };
        for (int i = 0; i < jvmSubNodes.length; i++) {
            jvmSubNode.add(new DefaultMutableTreeNode(jvmSubNodes[i]));
            componentMap.put(jvmSubNodes[i], gcComponents[i]);
        }

        jvmNode.add(jvmSubNode);

        // defining table info for socket branch sub nodes
        // define sub nodes for jvm
        String[] jvmNodes = {"GC Heap Summary"};

        GcHeapSummaryPanel gcHeapSummaryPanel = new GcHeapSummaryPanel(
                new GcHeapSummaryPanel.GcHeapSummaryTableModel(Arrays.asList(
                        new GcHeapSummary("jfr.GCHeapSummary.heapCommittedSize", 1619441634271L, "gauge", 2.65289728E8, 3.204448256E9, 1.39961312E8, new HashMap<>())
                ))
        );

        // define components for jvm sub nodes
        JBPanel[] jvmComponents = {gcHeapSummaryPanel.getGcHeapSummaryComponent()};
        // adding sub nodes to jvm root node
        for (int i = 0; i < jvmNodes.length; i++) {
            jvmNode.add(new DefaultMutableTreeNode(jvmNodes[i]));
            componentMap.put(jvmNodes[i], jvmComponents[i]);
        }


        // define sub nodes for os
        String[] osNodes = {"Thread CPU Load", "CPU Load", "CPU Load Graph"};
        ThreadCpuLoadPanel threadCpuLoadPanel = new ThreadCpuLoadPanel(
                new ThreadCpuLoadPanel.ThreadCpuLoadTableModel(Arrays.asList(
                        new ThreadCpuLoad("jfr.ThreadCPULoad.user", 1619441626468L, "gauge", 0.04082856327295303, 0.0010207140585407615, new HashMap<>())
                ))
        );

        cpuLoadPanel = new CpuLoadPanel(
                new CpuLoadPanel.CpuLoadTableModel(Arrays.asList(
                        new CpuLoad("jfr.CPULoad", 1619441627925L, "gauge", 0.25646382570266724, 0.031001122668385506, 0.3926701843738556, new HashMap<>())
                ))
        );

        // define components for os
        JBPanel[] osComponents = {threadCpuLoadPanel.getThreadCpuLoadComponent(),
                cpuLoadPanel.getCpuLoadComponent(), new JBPanel()};

        for (int i = 0; i < osNodes.length; i++) {
            osNode.add(new DefaultMutableTreeNode(osNodes[i]));
            componentMap.put(osNodes[i], osComponents[i]);
        }

        rootNode.add(flightRecorderNode);
        rootNode.add(javaApplicationNode);
        rootNode.add(jvmNode);
        rootNode.add(osNode);

        treePanel = new BorderLayoutPanel(0, 0);

        tree = new Tree(rootNode);
        tree.setBackground(treePanel.getBackground());
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        treePanel.add(new JBScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);

        tree.getSelectionModel().addTreeSelectionListener(e -> {

            Object[] pathArray = e.getPath().getPath();
            String selectedNode = pathArray[pathArray.length - 1].toString();

            JComponent selected = componentMap.get(selectedNode);
            if (selected != null) {
                newRelicJavaProfilerToolWindow.setMetricsSecondComponent(selected);
            } else {
                newRelicJavaProfilerToolWindow.clearMetricsPanelText();
                newRelicJavaProfilerToolWindow.updateMetricsPanelText(e.getPath().toString());
            }

        });

    }

    public JBPanel getMetricsTree() {
        return this.treePanel;
    }

    public void updateComponentMap(String key, JComponent component) {
        componentMap.put(key, component);
        newRelicJavaProfilerToolWindow.setMetricsSecondComponent(component);
    }

    public void updateCpuLoadGraph(CpuGraph cpuGraph) {
        this.cpuGraph = cpuGraph;
        componentMap.put("CPU Load Graph", cpuGraph.getChart());
    }

    public JTable getCpuLoadTable() {
        return this.cpuLoadPanel.getTable();
    }

    public JTable getObjectAllocationInNewTLabTable() {
        return this.objectAllocationInNewTLabPanel.getTable();
    }
}