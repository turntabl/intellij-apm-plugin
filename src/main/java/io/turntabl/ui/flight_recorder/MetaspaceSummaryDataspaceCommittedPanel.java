package io.turntabl.ui.flight_recorder;

import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.ui.model.MetaspaceSummaryDataspaceCommitted;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class MetaspaceSummaryDataspaceCommittedPanel {
    JPanel panel;
    JTable table;

    TableModel myData;
    DefaultTableColumnModel columnModel;

    public MetaspaceSummaryDataspaceCommittedPanel(TableModel tableModel) {
        panel = new JPanel(new BorderLayout());
        table = new JBTable(tableModel);

        table.setRowSelectionAllowed(true);
        table.setRowSelectionInterval(0, 0);

        table.getColumnModel().getColumn(0).setPreferredWidth(350);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(700);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(700);

        panel.add(new JBScrollPane(table, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER),  BorderLayout.CENTER);
    }

    public JPanel getMetaspaceSummaryMetaspaceCommittedComponent() {
        return panel;
    }

    public static class MetaspaceSummaryMetaspaceCommittedTableModel extends AbstractTableModel {
        String[] columnNames = {"Name", "Type", "Value", "Timestamp", "Attributes"};
        String[][] data;
        private java.util.List<MetaspaceSummaryDataspaceCommitted> metaspaceSummaryDataspaceCommittedList;

        public MetaspaceSummaryMetaspaceCommittedTableModel(List<MetaspaceSummaryDataspaceCommitted> metaspaceSummaryDataspaceCommittedList) {
            this.metaspaceSummaryDataspaceCommittedList = metaspaceSummaryDataspaceCommittedList;
            data = new String[metaspaceSummaryDataspaceCommittedList.size()][columnNames.length];
            for (int i = 0; i < metaspaceSummaryDataspaceCommittedList.size(); i++) {
                MetaspaceSummaryDataspaceCommitted MetaspaceSummaryDataspaceCommitted = metaspaceSummaryDataspaceCommittedList.get(i);
                data[i][0] = MetaspaceSummaryDataspaceCommitted.getName();
                data[i][1] = MetaspaceSummaryDataspaceCommitted.getType();
                data[i][2] = MetaspaceSummaryDataspaceCommitted.getValue().toString();
                data[i][3] = MetaspaceSummaryDataspaceCommitted.getTimestamp().toString();
                data[i][4] = MetaspaceSummaryDataspaceCommitted.getAttributes().get("when") == null ? "": MetaspaceSummaryDataspaceCommitted.getAttributes().get("when");
            }
        }

        @Override
        public int getRowCount() {
            return metaspaceSummaryDataspaceCommittedList.size();
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

