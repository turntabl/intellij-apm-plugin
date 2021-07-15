package io.turntabl.ui;

import com.intellij.execution.ui.layout.impl.JBRunnerTabs;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.JBSplitter;
import com.intellij.ui.OnePixelSplitter;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTextArea;
import com.intellij.ui.tabs.TabInfo;
import com.intellij.util.ui.components.BorderLayoutPanel;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class NewRelicJavaProfilerToolWindow implements Disposable {
    private JBPanel mainPanel, flameGraphRootPanel, metricsGraphRootPanel, metricsGraphPanel,
            eventsPanel,
            metricsPanel, metricsRootPanel, eventsRootPanel, flameGraphPanel;
    private JBRunnerTabs runnerTab;
    private JTextArea eventTextArea, metricsTextArea, flameGraphTextArea, metricsGraphTextArea;
    private FlameGraphTree flameGraphTree;
    private EventsTree eventsTree;
    private MetricsTree metricsTree;
    private MetricsGraphTree metricsGraphTree;
    private JBSplitter eventsSplitter, metricsSplitter, flameGraphSplitter, metricsGraphSplitter;

    public NewRelicJavaProfilerToolWindow(ToolWindow toolWindow, Project project) {
        mainPanel = new BorderLayoutPanel(0, 0);
        flameGraphRootPanel = new BorderLayoutPanel(0, 0);
        flameGraphPanel = new BorderLayoutPanel(0, 0);
        eventsPanel = new BorderLayoutPanel(0, 0);
        eventsRootPanel = new BorderLayoutPanel(0, 0);
        metricsRootPanel = new BorderLayoutPanel(0, 0);
        metricsPanel = new BorderLayoutPanel(0, 0);
        metricsGraphRootPanel = new BorderLayoutPanel(0, 0);
        metricsGraphPanel = new BorderLayoutPanel(0, 0);

        flameGraphTextArea = new JBTextArea();
        flameGraphTextArea.setLineWrap(true);
        
        eventTextArea = new JBTextArea();
        eventTextArea.setLineWrap(true);

        metricsTextArea = new JBTextArea();
        metricsTextArea.setLineWrap(true);

        metricsGraphTextArea = new JBTextArea();
        metricsGraphTextArea.setLineWrap(true);

        flameGraphTextArea.setBackground(flameGraphPanel.getBackground());
        eventTextArea.setBackground(eventsPanel.getBackground());
        metricsTextArea.setBackground(metricsPanel.getBackground());
        metricsGraphTextArea.setBackground(metricsGraphPanel.getBackground());

        flameGraphSplitter = new OnePixelSplitter(false, 0.17f);
        eventsSplitter = new OnePixelSplitter(false, 0.17f);
        metricsSplitter = new OnePixelSplitter(false, 0.19f);
        metricsGraphSplitter = new OnePixelSplitter(false, 0.17f);

        flameGraphTree = new FlameGraphTree(this);
        eventsTree = new EventsTree(this);
        metricsTree = new MetricsTree(this);
        metricsGraphTree = new MetricsGraphTree(this);

        flameGraphRootPanel.add(new JBScrollPane(flameGraphTextArea), BorderLayout.CENTER);
        eventsRootPanel.add(new JBScrollPane(eventTextArea), BorderLayout.CENTER);
        metricsRootPanel.add(new JBScrollPane(metricsTextArea), BorderLayout.CENTER);
        metricsGraphRootPanel.add(new JBScrollPane(metricsGraphTextArea), BorderLayout.CENTER);


        flameGraphSplitter.setFirstComponent(new JBScrollPane(flameGraphTree.getFlameGraphTree(), JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        flameGraphSplitter.setSecondComponent(new JBScrollPane(flameGraphPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        
        eventsSplitter.setFirstComponent(new JBScrollPane(eventsTree.getEventsTree(), JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        eventsSplitter.setSecondComponent(new JBScrollPane(eventsPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

        metricsSplitter.setFirstComponent(new JBScrollPane(metricsTree.getMetricsTree(), JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        metricsSplitter.setSecondComponent(new JBScrollPane(metricsPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

        metricsGraphSplitter.setFirstComponent(new JBScrollPane(metricsGraphTree.getMetricsGraphTree(), JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        metricsGraphSplitter.setSecondComponent(new JBScrollPane(metricsGraphPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

        flameGraphRootPanel.add(flameGraphSplitter, BorderLayout.CENTER);
        eventsRootPanel.add(eventsSplitter, BorderLayout.CENTER);
        metricsRootPanel.add(metricsSplitter, BorderLayout.CENTER);
        metricsGraphRootPanel.add(metricsGraphSplitter, BorderLayout.CENTER);

        runnerTab = new JBRunnerTabs(project, this);

        JBPanel[] tabPanels = {flameGraphRootPanel, metricsGraphRootPanel, eventsRootPanel, metricsRootPanel};
        String[] tabNames = {"Flame Graph", "Metric Graphs", "Events", "Metrics"};

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

    public void updateMetricsGraphPanelText(String text) {
        metricsGraphTextArea.append(text);
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

    public void clearMetricsGraphPanelText() {
        metricsGraphTextArea.setText("");
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

    public void setMetricsGraphSecondComponent(JComponent component) {
        metricsGraphSplitter.setSecondComponent(component);
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
}