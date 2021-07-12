package io.turntabl.ui.metric_graph;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTextArea;

import javax.swing.*;
import java.awt.*;

public class CpuLoadGraphPanel {
    private JBPanel panel;

    public CpuLoadGraphPanel() {
        panel = new JBPanel(new BorderLayout());
        panel.add(new JBTextArea("CPU Load Graph Not Generated Yet"));
    }

    public JComponent getComponent() {
        return new JBScrollPane(this.panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
}
