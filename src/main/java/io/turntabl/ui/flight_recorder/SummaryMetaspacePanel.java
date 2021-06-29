package io.turntabl.ui.flight_recorder;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.model.metrics.SummaryMetaspace;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;


public class SummaryMetaspacePanel {
    private JBPanel panel;
    private JTable table;

    public SummaryMetaspacePanel(TableModel tableModel) {
        panel = new JBPanel(new BorderLayout());
        table = new JBTable(tableModel);

        table.setRowSelectionAllowed(true);

        table.getColumnModel().getColumn(0).setPreferredWidth(350);
        table.getColumnModel().getColumn(1).setPreferredWidth(350);
        table.getColumnModel().getColumn(2).setPreferredWidth(350);
        table.getColumnModel().getColumn(3).setPreferredWidth(350);
        table.getColumnModel().getColumn(4).setPreferredWidth(350);
        table.getColumnModel().getColumn(5).setPreferredWidth(350);

        panel.add(new JBScrollPane(table, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER),  BorderLayout.CENTER);
    }

    public JBPanel getSummaryMetaspaceComponent() {
        return panel;
    }


    public static class SummaryMetaspaceTableModel extends AbstractTableModel {
        String[] columnNames = {"Timestamp", "Type", "Committed Value", "Used Value", "Reserved Value", "When"};
        String[][] data;
        private java.util.List<SummaryMetaspace> summaryMetaspaceList;

        public SummaryMetaspaceTableModel(List<SummaryMetaspace> summaryMetaspaceList) {
            this.summaryMetaspaceList = summaryMetaspaceList;
            data = new String[summaryMetaspaceList.size()][columnNames.length];
            for (int i = 0; i < summaryMetaspaceList.size(); i++) {
                SummaryMetaspace summaryMetaspace = summaryMetaspaceList.get(i);
                data[i][0] = summaryMetaspace.getTimestamp().toString();
                data[i][1] = summaryMetaspace.getType();
                data[i][2] = summaryMetaspace.getCommittedValue().toString();
                data[i][3] = summaryMetaspace.getUsedValue().toString();
                data[i][4] = summaryMetaspace.getReservedValue().toString();
                data[i][5] = String.valueOf(summaryMetaspace.getAttributes().get("when") == null ? "" : summaryMetaspace.getAttributes().get("when"));
            }
        }


        @Override
        public int getRowCount() {
            return summaryMetaspaceList.size();
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

