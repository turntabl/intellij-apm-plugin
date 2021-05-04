package io.turntabl.ui.java_virtual_machine.garbage_collection;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.ui.model.G1GarbageCollectionDuration;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class G1GarbageCollectionDurationPanel {
    static JBPanel panel;
    JTable table;

    TableModel myData;
    DefaultTableColumnModel columnModel;

    public G1GarbageCollectionDurationPanel(TableModel tableModel) {
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

    public JBPanel getG1GarbageCollectionDurationComponent() {
        return panel;
    }

    public static class G1GarbageCollectionDurationTableModel extends AbstractTableModel {
        String[] columnNames = {"Timestamp", "Type", "Value Count", "Value Sum", "Value Min", "Value Max", "Interval", "Attribute Name", "Attribute Cause"};
        String[][] data;
        private List<G1GarbageCollectionDuration> g1GarbageCollectionDurationList;

        public G1GarbageCollectionDurationTableModel(List<G1GarbageCollectionDuration> g1GarbageCollectionDurationList) {
            this.g1GarbageCollectionDurationList = g1GarbageCollectionDurationList;

            data = new String[g1GarbageCollectionDurationList.size()][columnNames.length];
            for (int i = 0; i < g1GarbageCollectionDurationList.size(); i++) {
                G1GarbageCollectionDuration g1GarbageCollectionDuration = g1GarbageCollectionDurationList.get(i);
                data[i][0] = String.valueOf(g1GarbageCollectionDuration.getTimestamp());
                data[i][1] = g1GarbageCollectionDuration.getType();
                data[i][2] = String.valueOf(g1GarbageCollectionDuration.getValues().get("values.count") == null ? "" : g1GarbageCollectionDuration.getValues().get("values.count"));
                data[i][3] = String.valueOf(g1GarbageCollectionDuration.getValues().get("values.sum") == null ? "" : g1GarbageCollectionDuration.getValues().get("values.sum"));
                data[i][4] = String.valueOf(g1GarbageCollectionDuration.getValues().get("values.min") == null ? "" : g1GarbageCollectionDuration.getValues().get("values.min"));
                data[i][5] = String.valueOf(g1GarbageCollectionDuration.getValues().get("values.max") == null ? "" : g1GarbageCollectionDuration.getValues().get("values.max"));
                data[i][6] = String.valueOf(g1GarbageCollectionDuration.getInterval());
                data[i][7] = String.valueOf(g1GarbageCollectionDuration.getAttributes().get("name") == null ? "" : g1GarbageCollectionDuration.getAttributes().get("name"));
                data[i][8] = String.valueOf(g1GarbageCollectionDuration.getAttributes().get("cause") == null ? "" : g1GarbageCollectionDuration.getAttributes().get("cause"));

            }
        }


        @Override
        public int getRowCount() {
            return g1GarbageCollectionDurationList.size();
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
