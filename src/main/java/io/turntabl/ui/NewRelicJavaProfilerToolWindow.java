package io.turntabl.ui;

import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.JBSplitter;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTabbedPane;
import com.intellij.ui.components.JBTextArea;

import javax.swing.*;
import java.awt.*;

public class NewRelicJavaProfilerToolWindow {

    private JLabel flameGraphLabel, callTreeLabel, methodListLabel;
    private JBPanel mainPanel, flameGraphPanel,
            callTreePanel, methodListPanel, eventsPanel,
            metricsPanel;
    private JTabbedPane tabbedPane;
    private JTextArea eventTextArea, metricsTextArea;
    private EventsTree eventsTree;
    private MetricsTree metricsTree;
    private JBSplitter eventSplitter, metricsSplitter;

    public NewRelicJavaProfilerToolWindow(ToolWindow toolWindow) {

        mainPanel = new JBPanel(new GridLayout(1, 1));

        flameGraphPanel = new JBPanel();
        callTreePanel = new JBPanel();
        methodListPanel = new JBPanel();
        eventsPanel = new JBPanel(new BorderLayout());
        metricsPanel = new JBPanel(new BorderLayout());

        eventTextArea = new JBTextArea();
        eventTextArea.setLineWrap(true);

        metricsTextArea = new JBTextArea();
        metricsTextArea.setLineWrap(true);

        flameGraphLabel = new JLabel("Flame Graph is displayed here!!!!!!!!!!!");
        callTreeLabel = new JLabel("Call Tree is displayed here!!!!!!!!!!!");
        methodListLabel = new JLabel("Method List is displayed here!!!!!!!!!!!");

        flameGraphPanel.add(flameGraphLabel);
        callTreePanel.add(callTreeLabel);
        methodListPanel.add(methodListLabel);

        eventTextArea.setBackground(eventsPanel.getBackground());
        metricsTextArea.setBackground(eventsPanel.getBackground());

        //events view
        eventSplitter = new JBSplitter(false, 0.12f);
        eventSplitter.setDividerWidth(2);

        metricsSplitter = new JBSplitter(false, 0.12f);
        metricsSplitter.setDividerWidth(2);

        eventsTree = new EventsTree(this);
        metricsTree = new MetricsTree(this);

        eventsPanel.add(new JBScrollPane(eventTextArea), BorderLayout.CENTER);
        metricsPanel.add(new JBScrollPane(metricsTextArea), BorderLayout.CENTER);

        eventSplitter.setFirstComponent(new JBScrollPane(eventsTree.getEventTree(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        eventSplitter.setSecondComponent(new JBScrollPane(eventsPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

        metricsSplitter.setFirstComponent(new JBScrollPane(metricsTree.getMetricsTree(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        metricsSplitter.setSecondComponent(new JBScrollPane(metricsPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

        tabbedPane = new JBTabbedPane();

        tabbedPane.add("Flame Graph", flameGraphPanel);
        tabbedPane.add("Call Tree", callTreePanel);
        tabbedPane.add("Method List", methodListPanel);
        tabbedPane.add("Events", eventSplitter);
        tabbedPane.add("Metrics", metricsSplitter);
        tabbedPane.setSelectedIndex(4);

        mainPanel.add(tabbedPane);

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
        eventSplitter.setSecondComponent(component);
    }

    public MetricsTree getMetricsTree() {
        return this.metricsTree;
    }

    public void setMetricsSecondComponent(JComponent component) {
        metricsSplitter.setSecondComponent(component);
    }
}