package io.turntabl;

import com.intellij.openapi.vcs.changes.PreviewDiffSplitterComponent;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTabbedPane;
import com.intellij.ui.components.JBTextArea;

import javax.swing.*;
import java.awt.*;

public class NewRelicJavaProfilerToolWindow {

    private JLabel label, flameGraphLabel, callTreeLabel, methodListLabel, eventsLabel;
    private JPanel mainPanel, flameGraphPanel, callTreePanel, methodListPanel, eventsPanel;
    private PreviewDiffSplitterComponent splitterComponent;
    private JTabbedPane tabbedPane;
    private JTextArea eventTextArea;
    private JScrollPane scrollPane;

    public NewRelicJavaProfilerToolWindow(ToolWindow toolWindow) {
        label = new JLabel("NewRelic Profiler");

        mainPanel = new JPanel(new GridLayout(1, 1));

        flameGraphPanel = new JPanel();
        callTreePanel = new JPanel();
        methodListPanel = new JPanel();
        eventsPanel = new JPanel();
        eventTextArea = new JBTextArea(5,200);
        eventTextArea.setLineWrap(true);


        scrollPane = new JBScrollPane(eventTextArea);

        flameGraphLabel = new JLabel("Flame Graph is displayed here!!!!!!!!!!!");
        callTreeLabel = new JLabel("Call Tree is displayed here!!!!!!!!!!!");
        methodListLabel = new JLabel("Method List is displayed here!!!!!!!!!!!");
        eventsLabel = new JLabel("Events are displayed here!!!!!!!!!!!");

        flameGraphPanel.add(flameGraphLabel);
        callTreePanel.add(callTreeLabel);
        methodListPanel.add(methodListLabel);

        eventTextArea.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim" +
                " ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip " +
                "ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate" +
                " velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat" +
                " cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        eventTextArea.setBackground(eventsPanel.getBackground());
//        eventsPanel.add(eventsLabel);//adds events label
        eventsPanel.add(scrollPane);//adds scrollpane with text area

        tabbedPane = new JBTabbedPane();

        tabbedPane.add("Flame Graph", flameGraphPanel);
        tabbedPane.add("Call Tree", callTreePanel);
        tabbedPane.add("Method List", methodListPanel);
        tabbedPane.add("Events", eventsPanel);
        tabbedPane.setSelectedIndex(3);

        mainPanel.add(tabbedPane);

    }

    private void sayHello() {
        label.setText("Hello New Relic");
    }

    public void setEventlabelText(String text) {
        eventTextArea.setText(text);
    }

    public JComponent getContent() {
        return mainPanel;
    }
}
