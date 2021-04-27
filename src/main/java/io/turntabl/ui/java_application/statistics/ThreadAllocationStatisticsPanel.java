package io.turntabl.ui.java_application.statistics;

import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.ui.model.ThreadAllocationStatistics;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class ThreadAllocationStatisticsPanel {
    JPanel panel;
    JTable table;

    TableModel myData;
    DefaultTableColumnModel columnModel;

    public ThreadAllocationStatisticsPanel (TableModel tableModel){
        panel = new JPanel(new BorderLayout());
        table = new JBTable(tableModel);

        table.setRowSelectionAllowed(true);
        table.setRowSelectionInterval(0, 0);

        table.getColumnModel().getColumn(0).setPreferredWidth(350);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(700);

        panel.add(new JBScrollPane(table, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER),  BorderLayout.CENTER);
    }
    public JPanel getThreadAllocationStatisticsComponent() {
        return panel;
    }

    public static class ThreadAllocationStatisticsTableModel extends AbstractTableModel {

        String[] columnNames = {"Start Time", "Allocated", "Thread"};
        String[][] data;
        private List<ThreadAllocationStatistics> threadAllocationStatisticsList;

        public ThreadAllocationStatisticsTableModel(List<ThreadAllocationStatistics> threadAllocationStatisticsList){
            this.threadAllocationStatisticsList = threadAllocationStatisticsList;
            data = new String[threadAllocationStatisticsList.size()][columnNames.length];
            for(int i = 0; i < threadAllocationStatisticsList.size(); i++){
                ThreadAllocationStatistics threadAllocationStatistics = threadAllocationStatisticsList.get(i);
                data[i][0]= threadAllocationStatistics.getStartTime();
                data[i][1]= threadAllocationStatistics.getAllocated();
                data[i][2]= threadAllocationStatistics.getThread();
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

