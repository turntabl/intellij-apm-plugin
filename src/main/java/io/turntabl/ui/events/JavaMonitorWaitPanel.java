package io.turntabl.ui.events;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.model.events.JavaMonitorWait;
import io.turntabl.utils.JsonUtility;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

public class JavaMonitorWaitPanel {
    private static final JsonUtility jsonUtil = new JsonUtility();
    private JBPanel panel;
    private JTable table;

    public JavaMonitorWaitPanel(TableModel tableModel) {
        panel = new JBPanel(new BorderLayout());
        table = new JBTable(tableModel);
        table.setRowSelectionAllowed(true);

        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
        table.getColumnModel().getColumn(5).setPreferredWidth(200);
        table.getColumnModel().getColumn(6).setPreferredWidth(200);
        table.getColumnModel().getColumn(7).setPreferredWidth(200);
        table.getColumnModel().getColumn(8).setPreferredWidth(200);

        panel.add(new JBScrollPane(table, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
    }

    public JBPanel getJavaMonitorWaitComponent() {
        return panel;
    }

    public JTable getTable() {
        return this.table;
    }

    public static class JavaMonitorWaitTableModel extends AbstractTableModel {
        String[] columnNames = {"Timestamp", "Duration", "Stack Trace", "Instrumentation Name", "Host Name", "Thread Name", "Class", "Collector Name", "Instrumentation Provider"};
        String[][] data;
        private List<JavaMonitorWait> javaMonitorWaitList;

        public JavaMonitorWaitTableModel(List<JavaMonitorWait> javaMonitorWaitList) {
            this.javaMonitorWaitList = javaMonitorWaitList;
            data = new String[javaMonitorWaitList.size()][columnNames.length];
            for (int i = 0; i < javaMonitorWaitList.size(); i++) {
                JavaMonitorWait javaMonitorWait = javaMonitorWaitList.get(i);
                data[i][0] = jsonUtil.getTime(javaMonitorWait.getTimestamp());
                data[i][1] = String.valueOf(javaMonitorWait.getDuration());
                data[i][2] = javaMonitorWait.getStackTrace();
                data[i][3] = javaMonitorWait.getInstrumentationName();
                data[i][4] = javaMonitorWait.getHostName();
                data[i][5] = javaMonitorWait.getThreadName();
                data[i][6] = javaMonitorWait.getClassName();
                data[i][7] = javaMonitorWait.getCollectorName();
                data[i][8] = javaMonitorWait.getInstrumentationProvider();
            }
        }

        @Override
        public int getRowCount() {
            return javaMonitorWaitList.size();
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