package io.turntabl.ui;

import com.intellij.execution.ui.layout.impl.JBRunnerTabs;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.IdeFocusManager;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.JBSplitter;
import com.intellij.ui.OnePixelSplitter;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTextArea;
import com.intellij.ui.tabs.TabInfo;
import com.intellij.util.ui.components.BorderLayoutPanel;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class NewRelicJavaProfilerToolWindow implements Disposable {
    private CpuGraph cpuGraph;
    private JBPanel mainPanel, flameGraphPanel, cpuLoadGraphPanel,
            callTreePanel, methodListPanel, eventsPanel,
            metricsPanel, metricsRootPanel, eventsRootPanel;
    private JBRunnerTabs runnerTab;
    private JTextArea eventTextArea, metricsTextArea;
    private EventsTree eventsTree;
    private MetricsTree metricsTree;
    private JBSplitter eventsSplitter, metricsSplitter;

    public NewRelicJavaProfilerToolWindow(ToolWindow toolWindow, Project project) {
        mainPanel = new BorderLayoutPanel(0, 0);
        cpuGraph = new CpuGraph(createDataSet(), "CPU Load Metrics", "Values", "Start Time");

        flameGraphPanel = new BorderLayoutPanel(0, 0);
        callTreePanel = new BorderLayoutPanel(0, 0);
        methodListPanel = new BorderLayoutPanel(0, 0);
        eventsPanel = new BorderLayoutPanel(0, 0);
        eventsRootPanel = new BorderLayoutPanel(0, 0);
        metricsRootPanel = new BorderLayoutPanel(0, 0);
        metricsPanel = new BorderLayoutPanel(0, 0);
        cpuLoadGraphPanel = new BorderLayoutPanel(0, 0);

        flameGraphPanel.add(cpuGraph.getChart(), BorderLayout.CENTER);

        eventTextArea = new JBTextArea();
        eventTextArea.setLineWrap(true);

        metricsTextArea = new JBTextArea();
        metricsTextArea.setLineWrap(true);

        eventTextArea.setBackground(eventsPanel.getBackground());
        eventTextArea.setText("This is a dummy text..............");
        metricsTextArea.setBackground(eventsPanel.getBackground());
        metricsTextArea.setText("This is another dummy text..............");

        //events view
        eventsSplitter = new OnePixelSplitter(false, 0.12f);
        metricsSplitter = new OnePixelSplitter(false, 0.12f);

        eventsTree = new EventsTree(this);
        metricsTree = new MetricsTree(this);

        eventsRootPanel.add(new JBScrollPane(eventTextArea), BorderLayout.CENTER);
        metricsPanel.add(new JBScrollPane(metricsTextArea), BorderLayout.CENTER);

        eventsSplitter.setFirstComponent(new JBScrollPane(eventsTree.getEventTree(), JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        eventsSplitter.setSecondComponent(new JBScrollPane(eventsPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

        metricsSplitter.setFirstComponent(new JBScrollPane(metricsTree.getMetricsTree(), JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        metricsSplitter.setSecondComponent(new JBScrollPane(metricsPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

        eventsRootPanel.add(eventsSplitter, BorderLayout.CENTER);
        metricsRootPanel.add(metricsSplitter, BorderLayout.CENTER);

        runnerTab = new JBRunnerTabs(project, ActionManager.getInstance(), IdeFocusManager.findInstance(), this);

        JBPanel[] tabPanels = new JBPanel[]{flameGraphPanel, callTreePanel, methodListPanel, eventsRootPanel, metricsRootPanel};
        String[] tabNames = new String[]{"Flame Graph", "CallTree", "Method List", "Events", "Metrics"};

        for (int i = 0; i < tabPanels.length; ++i) {
            runnerTab.addTab(new TabInfo(tabPanels[i]).setText(tabNames[i]));
        }
        runnerTab.select(runnerTab.getTabAt(4), true);
        runnerTab.setBorder(new EmptyBorder(0, 2, 0, 0));

        mainPanel.add(runnerTab);

    }

    public void updateEventPanelText(String text) {
        eventTextArea.append(text);
    }

    public void clearEventPanelText() {
        eventTextArea.setText("");
    }

    public void clearMetricsPanelText() {
        metricsTextArea.setText("");
    }

    public void updateMetricsPanelText(String text) {
        metricsTextArea.append(text);
    }

    public JComponent getContent() {
        return mainPanel;
    }

    public void setEventSecondComponent(JComponent component) {
        eventsSplitter.setSecondComponent(component);
    }

    public MetricsTree getMetricsTree() {
        return this.metricsTree;
    }

    public void setMetricsSecondComponent(JComponent component) {
        metricsSplitter.setSecondComponent(component);
    }

    @Override
    public void dispose() {
    }

    public XYDataset createDataSet() {
        XYSeries jvmUserSeries = new XYSeries("JVM User");
        XYSeries jvmSystemSeries = new XYSeries("JVM System");
        XYSeries machineTotalSeries = new XYSeries("Machine Total");

        jvmUserSeries.add(0.193,23056);
        jvmUserSeries.add(0.395, 33076);
        jvmUserSeries.add(0.596, 43090);

        jvmSystemSeries.add(0.199, 43076);
        jvmSystemSeries.add(0.301,53016);
        jvmSystemSeries.add(0.591, 63030);

        machineTotalSeries.add(0.189, 53076);
        machineTotalSeries.add(0.311,6056);
        machineTotalSeries.add(0.500, 73093);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(jvmUserSeries);
        dataset.addSeries(jvmSystemSeries);
        dataset.addSeries(machineTotalSeries);

        return dataset;
    }
}