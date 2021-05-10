package io.turntabl.ui.operating_system;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.model.ThreadCpuLoad;
import io.turntabl.utils.JsonUtility;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class ThreadCpuLoadPanel {
    private JBPanel panel;
    private JTable table;
    private static final JsonUtility jsonUtil = new JsonUtility();

    public ThreadCpuLoadPanel(TableModel tableModel) {
        panel = new JBPanel(new BorderLayout());
        table = new JBTable(tableModel);

        table.setRowSelectionAllowed(true);

        table.getColumnModel().getColumn(0).setPreferredWidth(350);
        table.getColumnModel().getColumn(1).setPreferredWidth(350);
        table.getColumnModel().getColumn(2).setPreferredWidth(350);
        table.getColumnModel().getColumn(3).setPreferredWidth(350);
        table.getColumnModel().getColumn(4).setPreferredWidth(350);
        table.getColumnModel().getColumn(5).setPreferredWidth(350);

        panel.add(new JBScrollPane(table, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
    }

    public JBPanel getThreadCpuLoadComponent() {
        return panel;
    }

    public static class ThreadCpuLoadTableModel extends AbstractTableModel {
        String[] columnNames = {"Start Time", "Type", "User", "System", "Thread OS Name", "Thread Name"};
        String[][] data;
        private java.util.List<ThreadCpuLoad> threadCpuLoadList;

        public ThreadCpuLoadTableModel(List<ThreadCpuLoad> threadCpuLoadList) {
            this.threadCpuLoadList = threadCpuLoadList;
            data = new String[threadCpuLoadList.size()][columnNames.length];
            for (int i = 0; i < threadCpuLoadList.size(); i++) {
                ThreadCpuLoad threadCpuLoad = threadCpuLoadList.get(i);
                data[i][0] = String.valueOf(jsonUtil.getTime(threadCpuLoad.getStartTime()));
                data[i][1] = threadCpuLoad.getType();
                data[i][2] = String.valueOf(threadCpuLoad.getUserValue());
                data[i][3] = String.valueOf(threadCpuLoad.getSystemValue());
                data[i][4] = threadCpuLoad.getAttributes().get("thread.osName") == null ? "" : threadCpuLoad.getAttributes().get("thread.osName");
                data[i][5] = threadCpuLoad.getAttributes().get("thread.name") == null ? "" : threadCpuLoad.getAttributes().get("thread.name");
            }
        }

        @Override
        public int getRowCount() {
            return threadCpuLoadList.size();
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
