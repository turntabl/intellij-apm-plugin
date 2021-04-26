package io.turntabl.ui.flight_recorder;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TestNode {
    private JPanel panel;

    public TestNode() {

        panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Test Node Label"), BorderLayout.CENTER);

    }

    public JPanel getComponent() {
        return panel;
    }

    @Override
    public String toString() {
        return "Test Node";
    }

    public void sayHi() {
        System.out.println("Hello Team mates");
    }
}
