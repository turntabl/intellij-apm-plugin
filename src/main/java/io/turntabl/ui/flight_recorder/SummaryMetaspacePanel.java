package io.turntabl.ui.flight_recorder;

import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.ui.model.SummaryMetaspace;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;


public class SummaryMetaspacePanel {
    JPanel panel;
    JTable table;

    TableModel myData;
    DefaultTableColumnModel columnModel;

    public SummaryMetaspacePanel(TableModel tableModel) {
        panel = new JPanel(new BorderLayout());
        table = new JBTable(tableModel);

        table.setRowSelectionAllowed(true);
        table.setRowSelectionInterval(0, 0);

        table.getColumnModel().getColumn(0).setPreferredWidth(350);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(700);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(700);
        table.getColumnModel().getColumn(5).setPreferredWidth(200);
        table.getColumnModel().getColumn(6).setPreferredWidth(700);

        panel.add(new JBScrollPane(table, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER),  BorderLayout.CENTER);
    }

    public JPanel getSummaryMetaspaceComponent() {
        return panel;
    }

    public static class SummaryMetaspaceTableModel extends AbstractTableModel {
        String[] columnNames = { "Name", "Type", "CommittedValue","UsedValue", "ReservedValue", "Timestamp", "Attributes"};
        String[][] data;
        private java.util.List<SummaryMetaspace> summaryMetaspaceList;

        public SummaryMetaspaceTableModel(List<SummaryMetaspace> summaryMetaspaceList) {
            this.summaryMetaspaceList = summaryMetaspaceList;
            data = new String[summaryMetaspaceList.size()][columnNames.length];
            for (int i = 0; i < summaryMetaspaceList.size(); i++) {
                SummaryMetaspace summaryMetaspace = summaryMetaspaceList.get(i);
                data[i][0] = summaryMetaspace.getName();
                data[i][1] = summaryMetaspace.getType();
                data[i][2] = summaryMetaspace.getCommittedValue().toString();
                data[i][3] = summaryMetaspace.getUsedValue().toString();
                data[i][4] = summaryMetaspace.getReservedValue().toString();
                data[i][5] = summaryMetaspace.getTimestamp().toString();
                data[i][6] = summaryMetaspace.getAttributes().toString() == null ? "": summaryMetaspace.getAttributes().toString();
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
    }
}

