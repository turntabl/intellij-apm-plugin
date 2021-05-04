package io.turntabl.ui.java_virtual_machine.garbage_collection;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.ui.model.GCDuration;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class GCDurationPanel {
    static JBPanel panel;
    JTable table;

    TableModel myData;
    DefaultTableColumnModel columnModel;

    public GCDurationPanel(TableModel tableModel) {
        panel = new JBPanel(new BorderLayout());
        table = new JBTable(tableModel);

        table.setRowSelectionAllowed(true);
        table.setRowSelectionInterval(0, 0);


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

    public JBPanel getGCDurationComponent() {
        return panel;
    }

    public static class GCDurationTableModel extends AbstractTableModel {
        String[] columnNames = {"Timestamp", "Type", "Value Count", "Value Sum", "Value Min", "Value Max", "Interval", "Attribute Name", "Attribute Cause"};
        String[][] data;

        private List<GCDuration> gcDurationList;

        public GCDurationTableModel(List<GCDuration> gcDurationList) {
            this.gcDurationList = gcDurationList;
            data = new String[gcDurationList.size()][columnNames.length];
            for (int i = 0; i < gcDurationList.size(); i++) {
                GCDuration gcDuration = gcDurationList.get(i);


                data[i][0] = String.valueOf(gcDuration.getTimestamp());
                data[i][1] = gcDuration.getType();
                data[i][2] = String.valueOf(gcDuration.getValues().get("values.count") == null ? "" : gcDuration.getValues().get("values.count"));
                data[i][3] = String.valueOf(gcDuration.getValues().get("values.sum") == null ? "" : gcDuration.getValues().get("values.sum"));
                data[i][4] = String.valueOf(gcDuration.getValues().get("values.min") == null ? "" : gcDuration.getValues().get("values.min"));
                data[i][5] = String.valueOf(gcDuration.getValues().get("values.max") == null ? "" : gcDuration.getValues().get("values.max"));
                data[i][6] = String.valueOf(gcDuration.getInterval());
                data[i][7] = String.valueOf(gcDuration.getAttributes().get("name") == null ? "" : gcDuration.getAttributes().get("name"));
                data[i][8] = String.valueOf(gcDuration.getAttributes().get("cause") == null ? "" : gcDuration.getAttributes().get("cause"));
            }
        }


        @Override
        public int getRowCount() {
            return gcDurationList.size();
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

}
