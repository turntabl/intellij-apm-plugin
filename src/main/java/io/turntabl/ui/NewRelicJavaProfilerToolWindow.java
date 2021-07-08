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
import com.intellij.util.SVGLoader;
import com.intellij.util.ui.components.BorderLayoutPanel;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class NewRelicJavaProfilerToolWindow implements Disposable {
    private CpuGraph cpuGraph;
    private JBPanel mainPanel, flameGraphRootPanel, cpuLoadGraphPanel,
            eventsPanel,
            metricsPanel, metricsRootPanel, eventsRootPanel, flameGraphPanel;
    private JBRunnerTabs runnerTab;
    private JTextArea eventTextArea, metricsTextArea, flameGraphTextArea;
    private FlameGraphTree flameGraphTree;
    private EventsTree eventsTree;
    private MetricsTree metricsTree;
    private JBSplitter eventsSplitter, metricsSplitter, flameGraphSplitter;

    public NewRelicJavaProfilerToolWindow(ToolWindow toolWindow, Project project) {
        mainPanel = new BorderLayoutPanel(0, 0);
        cpuGraph = new CpuGraph(createDataSet(), "CPU Load Metrics", "StartTime", "Values");

        flameGraphRootPanel = new BorderLayoutPanel(0, 0);
        flameGraphPanel = new BorderLayoutPanel(0, 0);
        eventsPanel = new BorderLayoutPanel(0, 0);
        eventsRootPanel = new BorderLayoutPanel(0, 0);
        metricsRootPanel = new BorderLayoutPanel(0, 0);
        metricsPanel = new BorderLayoutPanel(0, 0);
        cpuLoadGraphPanel = new BorderLayoutPanel(0, 0);
        
        flameGraphTextArea = new JBTextArea();
        flameGraphTextArea.setLineWrap(true);
        
        eventTextArea = new JBTextArea();
        eventTextArea.setLineWrap(true);

        metricsTextArea = new JBTextArea();
        metricsTextArea.setLineWrap(true);

        flameGraphTextArea.setBackground(flameGraphPanel.getBackground());
        eventTextArea.setBackground(eventsPanel.getBackground());
        metricsTextArea.setBackground(metricsPanel.getBackground());

        flameGraphSplitter = new OnePixelSplitter(false, 0.12f);
        eventsSplitter = new OnePixelSplitter(false, 0.12f);
        metricsSplitter = new OnePixelSplitter(false, 0.12f);

        flameGraphTree = new FlameGraphTree(this);
        eventsTree = new EventsTree(this);
        metricsTree = new MetricsTree(this);

        flameGraphRootPanel.add(new JBScrollPane(flameGraphTextArea), BorderLayout.CENTER);
        eventsRootPanel.add(new JBScrollPane(eventTextArea), BorderLayout.CENTER);
        metricsPanel.add(new JBScrollPane(metricsTextArea), BorderLayout.CENTER);

        flameGraphSplitter.setFirstComponent(new JBScrollPane(flameGraphTree.getFlameGraphTree(), JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        flameGraphSplitter.setSecondComponent(new JBScrollPane(flameGraphPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        
        eventsSplitter.setFirstComponent(new JBScrollPane(eventsTree.getEventsTree(), JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        eventsSplitter.setSecondComponent(new JBScrollPane(eventsPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

        metricsSplitter.setFirstComponent(new JBScrollPane(metricsTree.getMetricsTree(), JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        metricsSplitter.setSecondComponent(new JBScrollPane(metricsPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

        flameGraphRootPanel.add(flameGraphSplitter, BorderLayout.CENTER);
        eventsRootPanel.add(eventsSplitter, BorderLayout.CENTER);
        metricsRootPanel.add(metricsSplitter, BorderLayout.CENTER);

        runnerTab = new JBRunnerTabs(project, this);

        JBPanel[] tabPanels = {flameGraphRootPanel, eventsRootPanel, metricsRootPanel};
        String[] tabNames = {"Flame Graph", "Events", "Metrics"};

        for (int i = 0; i < tabPanels.length; ++i) {
            runnerTab.addTab(new TabInfo(tabPanels[i]).setText(tabNames[i]));
        }
        runnerTab.select(runnerTab.getTabAt(0), true);
        runnerTab.setBorder(new EmptyBorder(0, 2, 0, 0));

        mainPanel.add(runnerTab);

    }

    public void updateFlameGraphPanelText(String text) {
        flameGraphTextArea.append(text);
    }

    public void updateEventPanelText(String text) {
        eventTextArea.append(text);
    }

    public void updateMetricsPanelText(String text) {
        metricsTextArea.append(text);
    }

    public void clearFlameGraphPanelText() {
        flameGraphTextArea.setText("");
    }

    public void clearEventPanelText() {
        eventTextArea.setText("");
    }

    public void clearMetricsPanelText() {
        metricsTextArea.setText("");
    }

    public void setFlameGraphSecondComponent(JComponent component) {
        flameGraphSplitter.setSecondComponent(component);
    }

    public void setEventSecondComponent(JComponent component) {
        eventsSplitter.setSecondComponent(component);
    }

    public void setMetricsSecondComponent(JComponent component) {
        metricsSplitter.setSecondComponent(component);
    }

    public FlameGraphTree getFlameGraphTree() {
        return this.flameGraphTree;
    }

    public MetricsTree getMetricsTree() {
        return this.metricsTree;
    }

    public EventsTree getEventsTree() {
        return this.eventsTree;
    }

    public JComponent getContent() {
        return mainPanel;
    }


    @Override
    public void dispose() {
    }

    public XYDataset createDataSet() {
        XYSeries jvmUserSeries = new XYSeries("JVM User");
        XYSeries jvmSystemSeries = new XYSeries("JVM System");
        XYSeries machineTotalSeries = new XYSeries("Machine Total");

        jvmUserSeries.add(0.193, 23056);
        jvmUserSeries.add(0.395, 33076);
        jvmUserSeries.add(0.596, 43090);

        jvmSystemSeries.add(0.199, 43076);
        jvmSystemSeries.add(0.301, 53016);
        jvmSystemSeries.add(0.591, 63030);

        machineTotalSeries.add(0.189, 53076);
        machineTotalSeries.add(0.311, 6056);
        machineTotalSeries.add(0.500, 73093);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(jvmUserSeries);
        dataset.addSeries(jvmSystemSeries);
        dataset.addSeries(machineTotalSeries);

        return dataset;
    }
}