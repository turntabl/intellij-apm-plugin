package io.turntabl.ui.java_virtual_machine.garbage_collection;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.ui.model.GCMinorDuration;
import io.turntabl.utils.JsonUtility;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class GCMinorDurationPanel {
    private static JBPanel panel;
    private JTable table;
    private static final JsonUtility jsonUtil = new JsonUtility();

    public GCMinorDurationPanel(TableModel tableModel) {
        panel = new JBPanel(new BorderLayout());
        table = new JBTable(tableModel);

        table.setRowSelectionAllowed(true);
//        table.setRowSelectionInterval(0, 0);


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

    public JBPanel getGCMinorDurationComponent() {
        return panel;
    }

    public static class GCMinorDurationTableModel extends AbstractTableModel {
        String[] columnNames = {"Timestamp", "Type", "Value Count", "Value Sum", "Value Min", "Value Max", "Interval ms", "Attribute Name", "Attribute Cause"};
        String[][] data;

        private List<GCMinorDuration> gcMinorDurationList;

        public GCMinorDurationTableModel(List<GCMinorDuration> gcMinorDurationList) {
            this.gcMinorDurationList = gcMinorDurationList;

            data = new String[gcMinorDurationList.size()][columnNames.length];
            for (int i = 0; i < gcMinorDurationList.size(); i++) {
                GCMinorDuration gcMinorDuration = gcMinorDurationList.get(i);


                data[i][0] = jsonUtil.getTime(gcMinorDuration.getTimestamp());
                data[i][1] = gcMinorDuration.getType();
                data[i][2] = String.valueOf(gcMinorDuration.getValue().get("count") == null ? "" : gcMinorDuration.getValue().get("count"));
                data[i][3] = String.valueOf(gcMinorDuration.getValue().get("sum") == null ? "" : gcMinorDuration.getValue().get("sum"));
                data[i][4] = String.valueOf(gcMinorDuration.getValue().get("min") == null ? "" : gcMinorDuration.getValue().get("min"));
                data[i][5] = String.valueOf(gcMinorDuration.getValue().get("max") == null ? "" : gcMinorDuration.getValue().get("max"));
                data[i][6] = String.valueOf(gcMinorDuration.getInterval());
                data[i][7] = String.valueOf(gcMinorDuration.getAttributes().get("name") == null ? "" : gcMinorDuration.getAttributes().get("name"));
                data[i][8] = String.valueOf(gcMinorDuration.getAttributes().get("cause") == null ? "" : gcMinorDuration.getAttributes().get("cause"));
            }
        }

        @Override
        public int getRowCount() {
            return gcMinorDurationList.size();
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
