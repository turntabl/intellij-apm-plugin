package io.turntabl.ui.flight_recorder;

import javax.swing.*;
import java.awt.*;

public class DataLoss {
    JPanel panel;

    public DataLoss() {
        panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Data Loss"), BorderLayout.CENTER);
    }

    public JPanel getDataLossComponent() {
        return panel;
    }
}
