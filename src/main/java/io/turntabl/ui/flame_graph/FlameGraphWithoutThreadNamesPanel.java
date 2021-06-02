package io.turntabl.ui.flame_graph;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTextArea;
import org.apache.batik.dom.svg.SVGDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.swing.svg.SVGLoadEventDispatcherAdapter;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;


import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FlameGraphWithoutThreadNamesPanel {
    private JBPanel panel;

    public FlameGraphWithoutThreadNamesPanel() {
        panel = new JBPanel(new BorderLayout());

        File file = new File("C:/flamegraph/java_trace_no_thread_name.svg");
        if (!file.exists()) {
            panel.add(new JBTextArea("No flame graph generated yet"), BorderLayout.CENTER);
        } else {
            JSVGCanvas svg = new JSVGCanvas();
            svg.setURI("file:/C:/flamegraph/java_trace_no_thread_name.svg");
            svg.setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);

            panel.add(svg, BorderLayout.CENTER);
        }
    }

    public JComponent getComponent() {
        return new JBScrollPane(this.panel, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
}
