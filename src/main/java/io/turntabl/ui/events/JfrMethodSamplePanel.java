package io.turntabl.ui.events;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.model.events.JfrMethodSample;
import Java.utils.JsonUtility;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class JfrMethodSamplePanel {
    private JBPanel panel;
    private JTable table;
    private static final JsonUtility jsonUtil = new JsonUtility();

    public JfrMethodSamplePanel(TableModel tableModel) {
        panel = new JBPanel(new BorderLayout());
        table = new JBTable(tableModel);

        table.setRowSelectionAllowed(true);

        table.getColumnModel().getColumn(0).setPreferredWidth(350);
        table.getColumnModel().getColumn(1).setPreferredWidth(350);
        table.getColumnModel().getColumn(2).setPreferredWidth(350);
        table.getColumnModel().getColumn(3).setPreferredWidth(350);
        table.getColumnModel().getColumn(4).setPreferredWidth(350);
        table.getColumnModel().getColumn(5).setPreferredWidth(350);
        table.getColumnModel().getColumn(6).setPreferredWidth(350);
        table.getColumnModel().getColumn(7).setPreferredWidth(350);

        panel.add(new JBScrollPane(table, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER),  BorderLayout.CENTER);
    }

    public JBPanel getJfrMethodSampleComponent() {
        return panel;
    }

    public static class JfrMethodSampleTableModel extends AbstractTableModel {
        String[] columnNames = {"Timestamp", "Thread State", "Stack Trace", "Instrumentation Name", "Host Hostname", "Thread Name", "Collector Name", "Instrumentation Provider"};
        String[][] data;
        private java.util.List<JfrMethodSample> jfrMethodSampleList;

        public JfrMethodSampleTableModel(List<JfrMethodSample> jfrMethodSampleList) {
            this.jfrMethodSampleList = jfrMethodSampleList;
            data = new String[jfrMethodSampleList.size()][columnNames.length];
            for (int i = 0; i < jfrMethodSampleList.size(); i++) {
                JfrMethodSample jfrMethodSample = jfrMethodSampleList.get(i);
                data[i][0] = jsonUtil.getTime(jfrMethodSample.getTimestamp());
                data[i][1] = jfrMethodSample.getThreadState();
                data[i][2] = jfrMethodSample.getStackTrace();
                data[i][3] = jfrMethodSample.getInstrumentationName();
                data[i][4] = jfrMethodSample.getHostName();
                data[i][5] = jfrMethodSample.getThreadName();
                data[i][6] = jfrMethodSample.getCollectorName();
                data[i][7] = jfrMethodSample.getInstrumentationProvider();
            }
        }

        @Override
        public int getRowCount() {
            return jfrMethodSampleList.size();
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
