package io.turntabl.ui.flame_graph;


import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.jcef.JBCefBrowser;


import javax.swing.*;
import java.awt.*;


public class FlameGraphPanel {
    private JBPanel panel;

    public FlameGraphPanel() {
        panel = new JBPanel(new BorderLayout());
        panel.add(new JBCefBrowser("http://localhost:8787/flame-graph/").getComponent(), BorderLayout.CENTER);
        panel.setVisible(true);
    }

    public JComponent getComponent() {
        return new JBScrollPane(this.panel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
}
