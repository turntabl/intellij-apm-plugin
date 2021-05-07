package io.turntabl.ui.java_virtual_machine;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.ui.model.GcHeapSummary;
import io.turntabl.utils.JsonUtility;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class GcHeapSummaryPanel {
    private JBPanel panel;
    private JTable table;
    private static final JsonUtility jsonUtil = new JsonUtility();

    public GcHeapSummaryPanel(TableModel tableModel) {
        panel = new JBPanel(new BorderLayout());
        table = new JBTable(tableModel);

//        table.setRowSelectionAllowed(true);

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

    public JBPanel getGcHeapSummaryComponent() {
        return panel;
    }

    public JTable getTable() {
        return this.table;
    }

    public static class GcHeapSummaryTableModel extends AbstractTableModel {
        String[] columnNames = {"Start Time", "Type", "Heap Committed Size", "Reserved Size", "Heap Used", "Heap Start", "Reserved End", "Committed End", "When"};
        String[][] data;
        private java.util.List<GcHeapSummary> gcHeapSummaryList;

        public GcHeapSummaryTableModel(List<GcHeapSummary> gcHeapSummaryList) {
            this.gcHeapSummaryList = gcHeapSummaryList;
            data = new String[gcHeapSummaryList.size()][columnNames.length];
            for (int i = 0; i < gcHeapSummaryList.size(); i++) {
                GcHeapSummary gcHeapSummary = gcHeapSummaryList.get(i);
                data[i][0] = jsonUtil.getTime(gcHeapSummary.getStartTime());
                data[i][1] = gcHeapSummary.getType();
                data[i][2] = String.valueOf(gcHeapSummary.getHeapCommittedSize());
                data[i][3] = String.valueOf(gcHeapSummary.getReservedSize());
                data[i][4] = String.valueOf(gcHeapSummary.getHeapUsed());
                data[i][5] = String.valueOf(gcHeapSummary.getAttributes().get("heapStart") == null ? "" : gcHeapSummary.getAttributes().get("heapStart"));
                data[i][6] = String.valueOf(gcHeapSummary.getAttributes().get("reservedEnd") == null ? "" : gcHeapSummary.getAttributes().get("reservedEnd"));
                data[i][7] = String.valueOf(gcHeapSummary.getAttributes().get("committedEnd") == null ? "" : gcHeapSummary.getAttributes().get("committedEnd"));
                data[i][8] = gcHeapSummary.getAttributes().get("when") == null ? "" : gcHeapSummary.getAttributes().get("when");
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
