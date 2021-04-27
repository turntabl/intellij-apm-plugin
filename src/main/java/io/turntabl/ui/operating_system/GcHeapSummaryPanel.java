package io.turntabl.ui.operating_system;

import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.ui.model.GcHeapSummary;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class GcHeapSummaryPanel {
    JPanel panel;
    JTable table;

    TableModel myData;
    DefaultTableColumnModel columnModel;

    public GcHeapSummaryPanel(TableModel tableModel) {
        panel = new JPanel(new BorderLayout());
        table = new JBTable(tableModel);

        table.setRowSelectionAllowed(true);
        table.setRowSelectionInterval(0, 0);

        table.getColumnModel().getColumn(0).setPreferredWidth(350);
        table.getColumnModel().getColumn(1).setPreferredWidth(350);
        table.getColumnModel().getColumn(2).setPreferredWidth(350);
        table.getColumnModel().getColumn(3).setPreferredWidth(350);


        panel.add(new JBScrollPane(table, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER),  BorderLayout.CENTER);
    }

    public JPanel getGcHeapSummaryComponent() {
        return panel;
    }

    public static class GcHeapSummaryTableModel extends AbstractTableModel {
        String[] columnNames = {"StartTime", "Heap Committed Size", "Reserved Size", "Heap Used"};
        String[][] data;
        private java.util.List<GcHeapSummary> gcHeapSummaryList;

        public GcHeapSummaryTableModel(List<GcHeapSummary> gcHeapSummaryList) {
            this.gcHeapSummaryList = gcHeapSummaryList;
            data = new String[gcHeapSummaryList.size()][columnNames.length];
            for (int i = 0; i < gcHeapSummaryList.size(); i++) {
                GcHeapSummary gcHeapSummary = gcHeapSummaryList.get(i);
                data[i][0] = String.valueOf(gcHeapSummary.getStartTime());
                data[i][1] = String.valueOf(gcHeapSummary.getHeapCommittedSize());
                data[i][2] = String.valueOf(gcHeapSummary.getReservedSize());
                data[i][3] = String.valueOf(gcHeapSummary.getHeapUsed());
            }
        }

        @Override
        public int getRowCount() {
            return gcHeapSummaryList.size();
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
