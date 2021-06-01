package io.turntabl.ui.flame_graph;

import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.svg.SVGLoadEventDispatcherAdapter;


import javax.swing.*;
import java.awt.*;

public class FlameGraphPanel {
    private JBPanel panel;

    public FlameGraphPanel() {
        panel = new JBPanel(new BorderLayout());

        JSVGCanvas svg = new JSVGCanvas();
        svg.setURI("file:/C:/flamegraph/java_trace.svg");
        svg.setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);

        panel.add(svg, BorderLayout.CENTER);

    }

    public JComponent getComponent() {
        return new JBScrollPane(this.panel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
}
