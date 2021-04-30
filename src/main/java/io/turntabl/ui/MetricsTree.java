package io.turntabl.ui;

import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.treeStructure.Tree;
import io.turntabl.ui.flight_recorder.JfrSocketReadBytesReadPanel;
import io.turntabl.ui.flight_recorder.JfrSocketReadDurationPanel;
import io.turntabl.ui.model.JfrSocketReadBytesRead;
import io.turntabl.ui.model.JfrSocketReadDuration;
import io.turntabl.ui.model.GcHeapSummary;
import io.turntabl.ui.operating_system.CpuLoadPanel;
import io.turntabl.ui.flight_recorder.DataLossPanel;
import io.turntabl.ui.java_application.statistics.ThreadAllocationStatisticsPanel;
import io.turntabl.ui.model.ThreadAllocationStatistics;
import io.turntabl.ui.model.CpuLoad;
import io.turntabl.ui.model.DataLoss;
import io.turntabl.ui.operating_system.GcHeapSummaryPanel;
import io.turntabl.ui.model.ThreadCpuLoad;
import io.turntabl.ui.operating_system.ThreadCpuLoadPanel;
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

    private String[] jvmNodes = {"Initial System Property", "JVM Information"};
    private String[] javaAppStatisticsNodes = {"Class Loader Statistics", "Class Loading Statistics",
            "Exception Statistics", "Thread Allocated Statistics"};

    private String[] osNodes = {"Initial Environment Variable", "OS Information", "System Process"};

    private final NewRelicJavaProfilerToolWindow newRelicJavaProfilerToolWindow;
    private Map<String, JComponent> componentMap;
    private CpuLoadPanel cpuLoadPanel;
    private String[] socketNodes = {"Bytes Read", "Duration"};

    public MetricsTree(NewRelicJavaProfilerToolWindow newRelicJavaProfilerToolWindow) {
        this.newRelicJavaProfilerToolWindow = newRelicJavaProfilerToolWindow;
        componentMap = new HashMap<>();
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootNodeName);

        DefaultMutableTreeNode flightRecorderNode = new DefaultMutableTreeNode(flightRecorderNodeName);
        DefaultMutableTreeNode javaApplicationNode = new DefaultMutableTreeNode(javaAppNodeName);
        DefaultMutableTreeNode jdkNode = new DefaultMutableTreeNode(jdkNodeName);
        DefaultMutableTreeNode jvmNode = new DefaultMutableTreeNode(jvmNodeName);
        DefaultMutableTreeNode osNode = new DefaultMutableTreeNode(osNodeName);


        // add sub node to flight recorder branch node
        DefaultMutableTreeNode socketNode = new DefaultMutableTreeNode("JFR Socket Read");
        for (String nodeName : socketNodes) {
            socketNode.add(new DefaultMutableTreeNode(nodeName));
        }
        flightRecorderNode.add(socketNode);

        // add sub node to socket sub node of flight recorder branch node
        JfrSocketReadBytesReadPanel jfrSocketReadBytesReadPanel = new JfrSocketReadBytesReadPanel(
                new JfrSocketReadBytesReadPanel.JfrSocketReadBytesReadTableModel(Arrays.asList(
                        new JfrSocketReadBytesRead("jfr.SocketRead.bytesRead", 1619441645442L, "summary", new HashMap<>(), 46, new HashMap<>())
                ))
        );
        componentMap.put("Bytes Read", jfrSocketReadBytesReadPanel.getJfrSocketReadBytesReadComponent());

        // add sub node to socket sub node of flight recorder branch node
        JfrSocketReadDurationPanel jfrSocketReadDurationPanel = new JfrSocketReadDurationPanel(
                new JfrSocketReadDurationPanel.JfrSocketReadDurationTableModel(Arrays.asList(
                        new JfrSocketReadDuration("jfr.SocketRead.duration", 1619441645442L, "summary", new HashMap<>(), 50, new HashMap<>())
                ))
        );
        componentMap.put("Duration", jfrSocketReadDurationPanel.getJfrSocketReadDurationComponent());


        //add sub node to flight recorder branch node
        flightRecorderNode.add(new DefaultMutableTreeNode("Data Loss"));
        DataLossPanel dataLoss = new DataLossPanel(
                new DataLossPanel.DataLossTableModel(Arrays.asList(
                        new DataLoss("2021-06-01 11:08:12:20", "10", "10", new HashMap<String, String>()),
                        new DataLoss("2021-06-01 11:08:12:21", "15", "25", new HashMap<String, String>()),
                        new DataLoss("2021-06-01 11:08:12:22", "20", "45", new HashMap<String, String>())
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

        for (String nodeName : jvmNodes) {
            jvmNode.add(new DefaultMutableTreeNode(nodeName));
        }

        //add sub node to os branch node
        osNode.add(new DefaultMutableTreeNode("GC Heap Summary"));
        GcHeapSummaryPanel gcHeapSummaryPanel = new GcHeapSummaryPanel(
                new GcHeapSummaryPanel.GcHeapSummaryTableModel(Arrays.asList(
                        new GcHeapSummary("jfr.GCHeapSummary.heapCommittedSize",1619441634271L, "gauge", 2.65289728E8, 3.204448256E9, 1.39961312E8, new HashMap<>())
                ))
        );
        componentMap.put("GC Heap Summary", gcHeapSummaryPanel.getGcHeapSummaryComponent());

        //add sub node to os branch node
        osNode.add(new DefaultMutableTreeNode("Thread CPU Load"));
        ThreadCpuLoadPanel threadCpuLoadPanel = new ThreadCpuLoadPanel(
                new ThreadCpuLoadPanel.ThreadCpuLoadTableModel(Arrays.asList(
                        new ThreadCpuLoad("jfr.ThreadCPULoad.user",1619441626468L, "gauge", 0.04082856327295303, 0.0010207140585407615, new HashMap<>())
                ))
        );

        componentMap.put("Thread CPU Load", threadCpuLoadPanel.getThreadCpuLoadComponent());

        osNode.add(new DefaultMutableTreeNode("CPU Load"));
        CpuLoadPanel cpuLoadPanel = new CpuLoadPanel(
                new CpuLoadPanel.CpuLoadTableModel(Arrays.asList(
                        new CpuLoad(new Timestamp(1619441627925L), "gauge", 0.25646382570266724, 0.031001122668385506, 0.3926701843738556, new HashMap<>())

                ))
        );

        componentMap.put("CPU Load", cpuLoadPanel.getCpuLoadComponent());

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
                newRelicJavaProfilerToolWindow.setMetricsSecondComponent(selected);
            } else { 
                newRelicJavaProfilerToolWindow.clearMetricsPanelText();
                newRelicJavaProfilerToolWindow.updateMetricsPanelText(e.getPath().toString());
            }

        });

    }

    public JPanel getMetricsTree() {
        return this.treePanel;
    }

    public void updateComponentMap(String key, JComponent component) {
        componentMap.put(key, component);
        newRelicJavaProfilerToolWindow.setMetricsSecondComponent(component);
    }

    public JTable getCpuLoadTable() {
        return this.cpuLoadPanel.getTable();
    }

}
