package io.turntabl.ui.flame_graph;

import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;

import java.awt.*;

public class FlameGraphPanel {
    private JBPanel panel;

    public FlameGraphPanel() {
        panel = new JBPanel(new BorderLayout());
        JBLabel label = new JBLabel("Flame Graph Panel Here");
        panel.add(label);
    }

    public JBPanel getComponent() {
        return this.panel;
    }
}
