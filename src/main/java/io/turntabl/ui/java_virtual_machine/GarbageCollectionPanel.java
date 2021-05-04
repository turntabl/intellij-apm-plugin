package io.turntabl.ui.java_virtual_machine;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.ui.model.GarbageCollection;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class GarbageCollectionPanel {
    JBPanel panel;
    JTable table;

    TableModel myData;
    DefaultTableColumnModel columnModel;


    public GarbageCollectionPanel(TableModel tableModel) {
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

    public JBPanel getGarbageCollectionComponent() {
        return panel;
    }

    public static class GarbageCollectionTableModel extends AbstractTableModel {

        String[] columnNames = {"Timestamp", "Type", "Value Count", "Value Sum", "Value Min", "Value Max", "Interval", "Attribute Name", "Attribute Cause"};
        String[][] data;
        private List<GarbageCollection> garbageCollectionList;

        public GarbageCollectionTableModel(List<GarbageCollection> garbageCollectionList) {
            this.garbageCollectionList = garbageCollectionList;
            data = new String[garbageCollectionList.size()][columnNames.length];
            for (int i = 0; i < garbageCollectionList.size(); i++) {
                GarbageCollection garbageCollection = garbageCollectionList.get(i);
                data[i][0] = String.valueOf(garbageCollection.getTimestamp());
                data[i][1] = garbageCollection.getType();
                data[i][2] = String.valueOf(garbageCollection.getValues().get("value.count") == null ? "" : garbageCollection.getValues().get("value.count"));
                data[i][3] = String.valueOf(garbageCollection.getValues().get("value.sum") == null ? "" : garbageCollection.getValues().get("value.sum"));
                data[i][4] = String.valueOf(garbageCollection.getValues().get("value.min") == null ? "" : garbageCollection.getValues().get("value.min"));
                data[i][5] = String.valueOf(garbageCollection.getValues().get("value.max") == null ? "" : garbageCollection.getValues().get("value.max"));
                data[i][6] = String.valueOf(garbageCollection.getInterval());
                data[i][7] = String.valueOf(garbageCollection.getAttributes().get("name") == null ? "" : garbageCollection.getAttributes().get("name"));
                data[i][8] = String.valueOf(garbageCollection.getAttributes().get("cause") == null ? "" : garbageCollection.getAttributes().get("cause"));
            }
        }


        @Override
        public int getRowCount() {
            return garbageCollectionList.size();
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
