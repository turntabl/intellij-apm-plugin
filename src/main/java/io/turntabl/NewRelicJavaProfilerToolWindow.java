package io.turntabl;

import com.intellij.openapi.vcs.changes.PreviewDiffSplitterComponent;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBTabbedPane;

import javax.swing.*;
import java.awt.*;

public class NewRelicJavaProfilerToolWindow {

    private JLabel label, flameGraphLabel, callTreeLabel, methodListLabel, eventsLabel;
    private JPanel mainPanel, flameGraphPanel, callTreePanel, methodListPanel, eventsPanel;
    private PreviewDiffSplitterComponent splitterComponent;
    private JTabbedPane tabbedPane;

    public NewRelicJavaProfilerToolWindow(ToolWindow toolWindow) {
        label = new JLabel("NewRelic Profiler");

        mainPanel = new JPanel(new GridLayout(1, 1));

        flameGraphPanel = new JPanel();
        callTreePanel = new JPanel();
        methodListPanel = new JPanel();
        eventsPanel = new JPanel();

        flameGraphLabel = new JLabel("Flame Graph is displayed here!!!!!!!!!!!");
        callTreeLabel = new JLabel("Call Tree is displayed here!!!!!!!!!!!");
        methodListLabel = new JLabel("Method List is displayed here!!!!!!!!!!!");
        eventsLabel = new JLabel("Events are displayed here!!!!!!!!!!!");

        flameGraphPanel.add(flameGraphLabel);
        callTreePanel.add(callTreeLabel);
        methodListPanel.add(methodListLabel);
        eventsPanel.add(eventsLabel);

        tabbedPane = new JBTabbedPane();

        tabbedPane.add("Flame Graph", flameGraphPanel);
        tabbedPane.add("Call Tree", callTreePanel);
        tabbedPane.add("Method List", methodListPanel);
        tabbedPane.add("Events", eventsPanel);

        mainPanel.add(tabbedPane);

    }

    private void sayHello() {
        label.setText("Hello New Relic");
    }

    public JComponent getContent() {
        return mainPanel;
    }
}
