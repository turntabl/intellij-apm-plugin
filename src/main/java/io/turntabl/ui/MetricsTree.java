package io.turntabl.ui;

import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.treeStructure.Tree;
import io.turntabl.ui.flight_recorder.JfrSocketReadBytesReadPanel;
import io.turntabl.ui.flight_recorder.JfrSocketReadDurationPanel;
import io.turntabl.ui.flight_recorder.SummaryMetaspacePanel;
import io.turntabl.ui.flight_recorder.ThreadContextSwitchRatePanel;
import io.turntabl.ui.model.*;
import io.turntabl.ui.operating_system.CpuLoadPanel;
import io.turntabl.ui.operating_system.GcHeapSummaryPanel;
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
    private String osNodeName = "Operating System";
    private final NewRelicJavaProfilerToolWindow newRelicJavaProfilerToolWindow;
    private Map<String, JComponent> componentMap;
    private CpuLoadPanel cpuLoadPanel;
    private String[] socketNodes = {"Bytes Read", "Duration"};

    public MetricsTree(NewRelicJavaProfilerToolWindow newRelicJavaProfilerToolWindow) {
        this.newRelicJavaProfilerToolWindow = newRelicJavaProfilerToolWindow;
        componentMap = new HashMap<>();
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(rootNodeName);

        DefaultMutableTreeNode flightRecorderNode = new DefaultMutableTreeNode(flightRecorderNodeName);
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

        //addThreadContextSwitchRatePanel to flight recorder branch node
        flightRecorderNode.add(new DefaultMutableTreeNode("Thread Context Switch Rate"));
        ThreadContextSwitchRatePanel threadContextSwitchRatePanel = new ThreadContextSwitchRatePanel(
                new ThreadContextSwitchRatePanel.ThreadContextSwitchRateTableModel(Arrays.asList(
                        new ThreadContextSwitchRate("jfr.ThreadCPULoad.user", "gauge", 0.004792887717485428, 41233434L, new HashMap<>())
                )));

        componentMap.put("Thread Context Switch Rate", threadContextSwitchRatePanel.getThreadContextSwitchRateComponent());

        //add MetaspaceSummaryMetaspaceUsedPanel to flight recorder branch node
        flightRecorderNode.add(new DefaultMutableTreeNode("Summary Metaspace"));
        SummaryMetaspacePanel summaryMetaspacePanel = new SummaryMetaspacePanel(
                new SummaryMetaspacePanel.SummaryMetaspaceTableModel(Arrays.asList(
                        new SummaryMetaspace("jfr.MetaspaceSummary.dataSpace.committed", "type", 0.209283, 0.209283, 0.209283, 28364347L, new HashMap<>())
                )));

        componentMap.put("Summary Metaspace", summaryMetaspacePanel.getSummaryMetaspaceComponent());


        //add sub node to os branch node
        osNode.add(new DefaultMutableTreeNode("GC Heap Summary"));
        GcHeapSummaryPanel gcHeapSummaryPanel = new GcHeapSummaryPanel(
                new GcHeapSummaryPanel.GcHeapSummaryTableModel(Arrays.asList(
                        new GcHeapSummary("jfr.GCHeapSummary.heapCommittedSize", 1619441634271L, "gauge", 2.65289728E8, 3.204448256E9, 1.39961312E8, new HashMap<>())
                ))
        );
        componentMap.put("GC Heap Summary", gcHeapSummaryPanel.getGcHeapSummaryComponent());

        //add sub node to os branch node
        osNode.add(new DefaultMutableTreeNode("Thread CPU Load"));
        ThreadCpuLoadPanel threadCpuLoadPanel = new ThreadCpuLoadPanel(
                new ThreadCpuLoadPanel.ThreadCpuLoadTableModel(Arrays.asList(
                        new ThreadCpuLoad("jfr.ThreadCPULoad.user", 1619441626468L, "gauge", 0.04082856327295303, 0.0010207140585407615, new HashMap<>())
                ))
        );

        componentMap.put("Thread CPU Load", threadCpuLoadPanel.getThreadCpuLoadComponent());

        osNode.add(new DefaultMutableTreeNode("CPU Load"));
        cpuLoadPanel = new CpuLoadPanel(
                new CpuLoadPanel.CpuLoadTableModel(Arrays.asList(
                        new CpuLoad("jfr.CPULoad", 1619441627925L, "gauge", 0.25646382570266724, 0.031001122668385506, 0.3926701843738556, new HashMap<>())

                ))
        );

        componentMap.put("CPU Load", cpuLoadPanel.getCpuLoadComponent());

        rootNode.add(flightRecorderNode);
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
