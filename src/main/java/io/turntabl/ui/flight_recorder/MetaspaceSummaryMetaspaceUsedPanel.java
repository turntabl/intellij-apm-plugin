package io.turntabl.ui.flight_recorder;

import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.ui.model.MetaspaceSummaryDataspaceCommitted;
import io.turntabl.ui.model.SummaryMetaspaceUsed;
import io.turntabl.ui.model.ThreadContextSwitchRate;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;


public class MetaspaceSummaryMetaspaceUsedPanel {
    JPanel panel;
    JTable table;

    TableModel myData;
    DefaultTableColumnModel columnModel;

    public MetaspaceSummaryMetaspaceUsedPanel(TableModel tableModel) {
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

    public JPanel getMetaspaceSummaryMetaspaceUsedComponent() {
        return panel;
    }

    public static class MetaspaceSummaryMetaspaceCommittedTableModel extends AbstractTableModel {
        String[] columnNames = {  "Name", "Type", "Value", "Timestamp", "Attributes"};
        String[][] data;
        private java.util.List<SummaryMetaspaceUsed> summaryMetaspaceUsedList;

        public MetaspaceSummaryMetaspaceCommittedTableModel(List<SummaryMetaspaceUsed> summaryMetaspaceUsedList) {
            this.summaryMetaspaceUsedList = summaryMetaspaceUsedList;
            data = new String[summaryMetaspaceUsedList.size()][columnNames.length];
            for (int i = 0; i < summaryMetaspaceUsedList.size(); i++) {
                SummaryMetaspaceUsed summaryMetaspaceUsed = summaryMetaspaceUsedList.get(i);
                data[i][0] = summaryMetaspaceUsed.getName();
                data[i][1] = summaryMetaspaceUsed.getType();
                data[i][2] = summaryMetaspaceUsed.getValue().toString();
                data[i][3] = summaryMetaspaceUsed.getTimestamp().toString();
                data[i][4] = summaryMetaspaceUsed.getAttributes().toString() == null ? "": summaryMetaspaceUsed.getAttributes().toString();
                //data[i][5] = threadContextSwitchRate.getAttributes().toString();
            }
        }

        @Override
        public int getRowCount() {
            return summaryMetaspaceUsedList.size();
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

