package io.turntabl.ui.java_application.statistics;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.model.metrics.ThreadAllocationStatistics;
import io.turntabl.utils.JsonUtility;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class ThreadAllocationStatisticsPanel {
    private static final JsonUtility jsonUtil = new JsonUtility();
    private JBPanel panel;
    private JTable table;


    public ThreadAllocationStatisticsPanel(TableModel tableModel) {
        panel = new JBPanel(new BorderLayout());
        table = new JBTable(tableModel);

        table.setRowSelectionAllowed(true);

        table.getColumnModel().getColumn(0).setPreferredWidth(350);
        table.getColumnModel().getColumn(1).setPreferredWidth(350);
        table.getColumnModel().getColumn(2).setPreferredWidth(350);
        table.getColumnModel().getColumn(3).setPreferredWidth(350);

        panel.add(new JBScrollPane(table, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
    }

    public JBPanel getThreadAllocationStatisticsComponent() {
        return panel;
    }

    public JTable getTable() {
        return this.table;
    }

    public static class ThreadAllocationStatisticsTableModel extends AbstractTableModel {

        String[] columnNames = {"Timestamp", "Value", "Thread OS Name", "Thread Name"};
        String[][] data;
        private List<ThreadAllocationStatistics> threadAllocationStatisticsList;

        public ThreadAllocationStatisticsTableModel(List<ThreadAllocationStatistics> threadAllocationStatisticsList) {
            this.threadAllocationStatisticsList = threadAllocationStatisticsList;
            data = new String[threadAllocationStatisticsList.size()][columnNames.length];
            for (int i = 0; i < threadAllocationStatisticsList.size(); i++) {
                ThreadAllocationStatistics threadAllocationStatistics = threadAllocationStatisticsList.get(i);

                data[i][0] = jsonUtil.getTime(threadAllocationStatistics.getTimestamp());
                data[i][1] = String.valueOf(threadAllocationStatistics.getValue());
                data[i][2] = threadAllocationStatistics.getAttributes().get("thread.osName") == null ? "" : threadAllocationStatistics.getAttributes().get("thread.osName");
                data[i][3] = threadAllocationStatistics.getAttributes().get("thread.name") == null ? "" : threadAllocationStatistics.getAttributes().get("thread.name");

            }
        }

        @Override
        public int getRowCount() {
            return threadAllocationStatisticsList.size();
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

