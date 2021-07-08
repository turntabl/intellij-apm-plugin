package io.turntabl.ui.java_application;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.model.metrics.ObjectAllocationOutsideTLab;
import io.turntabl.utils.JsonUtility;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

public class ObjectAllocationOutsideTLabPanel {
    private JBPanel panel;
    private JTable table;
    private static final JsonUtility jsonUtil = new JsonUtility();

    TableModel myData;
    DefaultTableColumnModel columnModel;

    public ObjectAllocationOutsideTLabPanel(TableModel tableModel) {
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

    public JBPanel getObjectAllocationOutsideTLabComponent() {
        return panel;
    }

    public static class ObjectAllocationOutsideTLabTableModel extends AbstractTableModel {
        String[] columnNames = {"Start Time", "Type", "Count", "Sum", "Min", "Max", "Interval ms", "Thread Name"};
        String[][] data;
        private List<ObjectAllocationOutsideTLab> ObjectAllocationOutsideTLabList;

        public ObjectAllocationOutsideTLabTableModel(List<ObjectAllocationOutsideTLab> ObjectAllocationOutsideTLabList) {
            this.ObjectAllocationOutsideTLabList = ObjectAllocationOutsideTLabList;
            data = new String[ObjectAllocationOutsideTLabList.size()][columnNames.length];
            for (int i = 0; i < ObjectAllocationOutsideTLabList.size(); i++) {
                ObjectAllocationOutsideTLab ObjectAllocationOutsideTLab = ObjectAllocationOutsideTLabList.get(i);
                data[i][0] = jsonUtil.getTime(ObjectAllocationOutsideTLab.getTimestamp());
                data[i][1] = ObjectAllocationOutsideTLab.getType();
                data[i][2] = String.valueOf(ObjectAllocationOutsideTLab.getValue().get("count") == null ? "" : ObjectAllocationOutsideTLab.getValue().get("count"));
                data[i][3] = ObjectAllocationOutsideTLab.getValue().get("sum") == null ? "" : String.valueOf(ObjectAllocationOutsideTLab.getValue().get("sum"));
                data[i][4] = ObjectAllocationOutsideTLab.getValue().get("min") == null ? "" : String.valueOf(ObjectAllocationOutsideTLab.getValue().get("min"));
                data[i][5] = ObjectAllocationOutsideTLab.getValue().get("max") == null ? "" : String.valueOf(ObjectAllocationOutsideTLab.getValue().get("max"));
                data[i][6] = String.valueOf(ObjectAllocationOutsideTLab.getInterval());
                data[i][7] = ObjectAllocationOutsideTLab.getAttributes().get("thread.name") == null ? "" : String.valueOf(ObjectAllocationOutsideTLab.getAttributes().get("thread.name"));
            }
        }

        @Override
        public int getRowCount() {
            return ObjectAllocationOutsideTLabList.size();
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
