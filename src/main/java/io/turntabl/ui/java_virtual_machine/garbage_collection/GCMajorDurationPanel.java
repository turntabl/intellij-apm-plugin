package io.turntabl.ui.java_virtual_machine.garbage_collection;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
<<<<<<< HEAD
import io.turntabl.model.metrics.GCMajorDuration;
=======
import io.turntabl.model.GCMajorDuration;
>>>>>>> main
import io.turntabl.utils.JsonUtility;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class GCMajorDurationPanel {
    private static JBPanel panel;
    private JTable table;
    private static final JsonUtility jsonUtil = new JsonUtility();

    public GCMajorDurationPanel(TableModel tableModel) {
        panel = new JBPanel(new BorderLayout());
        table = new JBTable(tableModel);

        table.setRowSelectionAllowed(true);
<<<<<<< HEAD
=======
//

>>>>>>> main

        table.getColumnModel().getColumn(0).setPreferredWidth(350);
        table.getColumnModel().getColumn(1).setPreferredWidth(350);
        table.getColumnModel().getColumn(2).setPreferredWidth(350);
        table.getColumnModel().getColumn(3).setPreferredWidth(350);
        table.getColumnModel().getColumn(4).setPreferredWidth(350);
        table.getColumnModel().getColumn(5).setPreferredWidth(350);
        table.getColumnModel().getColumn(6).setPreferredWidth(350);
        table.getColumnModel().getColumn(7).setPreferredWidth(350);
        table.getColumnModel().getColumn(8).setPreferredWidth(350);

        panel.add(new JBScrollPane(table, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);

    }

    public JBPanel getGCMajorDurationComponent() {
        return panel;
    }

    public static class GCMajorDurationTableModel extends AbstractTableModel {
        String[] columnNames = {"Timestamp", "Type", "Value Count", "Value Sum", "Value Min", "Value Max", "Interval ms", "Attribute Name", "Attribute Cause"};
        String[][] data;

        private List<GCMajorDuration> gcMajorDurationList;

        public GCMajorDurationTableModel(List<GCMajorDuration> gcMajorDurationList) {
            this.gcMajorDurationList = gcMajorDurationList;

            data = new String[gcMajorDurationList.size()][columnNames.length];
            for (int i = 0; i < gcMajorDurationList.size(); i++) {
                GCMajorDuration gcMajorDuration = gcMajorDurationList.get(i);


                data[i][0] = jsonUtil.getTime(gcMajorDuration.getTimestamp());
                data[i][1] = gcMajorDuration.getType();
                data[i][2] = String.valueOf(gcMajorDuration.getValue().get("count") == null ? "" : gcMajorDuration.getValue().get("count"));
                data[i][3] = String.valueOf(gcMajorDuration.getValue().get("sum") == null ? "" : gcMajorDuration.getValue().get("sum"));
                data[i][4] = String.valueOf(gcMajorDuration.getValue().get("min") == null ? "" : gcMajorDuration.getValue().get("min"));
                data[i][5] = String.valueOf(gcMajorDuration.getValue().get("max") == null ? "" : gcMajorDuration.getValue().get("max"));
                data[i][6] = String.valueOf(gcMajorDuration.getInterval());
                data[i][7] = String.valueOf(gcMajorDuration.getAttributes().get("name") == null ? "" : gcMajorDuration.getAttributes().get("name"));
                data[i][8] = String.valueOf(gcMajorDuration.getAttributes().get("cause") == null ? "" : gcMajorDuration.getAttributes().get("cause"));
            }
        }

        @Override
        public int getRowCount() {
            return gcMajorDurationList.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }

    public JTable getTable() {
        return this.table;
    }
}
