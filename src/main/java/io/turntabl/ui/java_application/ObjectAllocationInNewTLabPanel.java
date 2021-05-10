package io.turntabl.ui.java_application;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.model.metrics.ObjectAllocationInNewTLab;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

public class ObjectAllocationInNewTLabPanel {
    JBPanel panel;
    JTable table;

    TableModel myData;
    DefaultTableColumnModel columnModel;

    public ObjectAllocationInNewTLabPanel(TableModel tableModel) {
        panel = new JBPanel(new BorderLayout());
        table = new JBTable(tableModel);

        table.setRowSelectionAllowed(true);

        table.getColumnModel().getColumn(0).setPreferredWidth(350);
        table.getColumnModel().getColumn(1).setPreferredWidth(350);
        table.getColumnModel().getColumn(2).setPreferredWidth(350);
        table.getColumnModel().getColumn(3).setPreferredWidth(350);
        table.getColumnModel().getColumn(4).setPreferredWidth(350);
        table.getColumnModel().getColumn(5).setPreferredWidth(350);
        table.getColumnModel().getColumn(6).setPreferredWidth(350);
        table.getColumnModel().getColumn(7).setPreferredWidth(350);


        panel.add(new JBScrollPane(table, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
    }

    public JBPanel getObjectAllocationInNewTLabComponent() {
        return panel;
    }

    public static class ObjectAllocationInNewTLabTableModel extends AbstractTableModel {
        String[] columnNames = {"Start Time", "Type", "Count", "Sum", "Min", "Max", "Interval ms", "Thread Name"};
        String[][] data;
        private List<ObjectAllocationInNewTLab> ObjectAllocationInNewTLabList;

        public ObjectAllocationInNewTLabTableModel(List<ObjectAllocationInNewTLab> ObjectAllocationInNewTLabList) {
            this.ObjectAllocationInNewTLabList = ObjectAllocationInNewTLabList;
            data = new String[ObjectAllocationInNewTLabList.size()][columnNames.length];
            for (int i = 0; i < ObjectAllocationInNewTLabList.size(); i++) {
                ObjectAllocationInNewTLab ObjectAllocationInNewTLab = ObjectAllocationInNewTLabList.get(i);
                data[i][0] = String.valueOf(ObjectAllocationInNewTLab.getTimestamp());
                data[i][1] = ObjectAllocationInNewTLab.getType();
                data[i][2] = String.valueOf(ObjectAllocationInNewTLab.getValue().get("count") == null ? "" : ObjectAllocationInNewTLab.getValue().get("count"));
                data[i][3] = ObjectAllocationInNewTLab.getValue().get("sum") == null ? "" : String.valueOf(ObjectAllocationInNewTLab.getValue().get("sum"));
                data[i][4] = ObjectAllocationInNewTLab.getValue().get("min") == null ? "" : String.valueOf(ObjectAllocationInNewTLab.getValue().get("min"));
                data[i][5] = ObjectAllocationInNewTLab.getValue().get("max") == null ? "" : String.valueOf(ObjectAllocationInNewTLab.getValue().get("max"));
                data[i][6] = String.valueOf(ObjectAllocationInNewTLab.getInterval());
                data[i][7] = ObjectAllocationInNewTLab.getAttributes().get("thread.name") == null ? "" : String.valueOf(ObjectAllocationInNewTLab.getAttributes().get("thread.name"));


            }


        }

        @Override
        public int getRowCount() {
            return ObjectAllocationInNewTLabList.size();
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
