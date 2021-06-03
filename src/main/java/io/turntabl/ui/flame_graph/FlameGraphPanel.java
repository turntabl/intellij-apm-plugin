package io.turntabl.ui.flame_graph;

import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTextArea;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.svg.SVGLoadEventDispatcherAdapter;


import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FlameGraphPanel {
    private JBPanel panel;

    public FlameGraphPanel() {
        panel = new JBPanel(new BorderLayout());

        File file = new File("C:/flamegraph/java_trace.svg");
        if (!file.exists()) {
            panel.add(new JBTextArea("No flame graph generated yet"), BorderLayout.CENTER);
        } else {
            JSVGCanvas svg = new JSVGCanvas();
            svg.setURI("file:/C:/flamegraph/java_trace.svg");
            svg.setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);
            svg.setSize(500, 500);
            panel.setSize(500, 500);
            panel.add(svg, BorderLayout.CENTER);
        }
    }

    public JComponent getComponent() {
        return new JBScrollPane(this.panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
}
