package io.turntabl.ui.flight_recorder;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.model.metrics.ThreadContextSwitchRate;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class ThreadContextSwitchRatePanel {

        JBPanel panel;
        JTable table;

        TableModel myData;
        DefaultTableColumnModel columnModel;

        public ThreadContextSwitchRatePanel(TableModel tableModel) {
            panel = new JBPanel(new BorderLayout());
            table = new JBTable(tableModel);

            table.setRowSelectionAllowed(true);

            table.getColumnModel().getColumn(0).setPreferredWidth(350);
            table.getColumnModel().getColumn(1).setPreferredWidth(350);
            table.getColumnModel().getColumn(2).setPreferredWidth(350);
            table.getColumnModel().getColumn(3).setPreferredWidth(350);
            table.getColumnModel().getColumn(4).setPreferredWidth(350);

            panel.add(new JBScrollPane(table, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER),  BorderLayout.CENTER);
        }

        public JBPanel getThreadContextSwitchRateComponent() {
            return panel;
        }

        public static class ThreadContextSwitchRateTableModel extends AbstractTableModel {
            String[] columnNames = {"Timestamp", "Type", "Value", "Thread OS Name", "Thread Name"};
            String[][] data;
            private java.util.List<ThreadContextSwitchRate> threadContextSwitchRateList;

            public ThreadContextSwitchRateTableModel(List<ThreadContextSwitchRate> threadContextSwitchRateList) {
                this.threadContextSwitchRateList = threadContextSwitchRateList;
                data = new String[threadContextSwitchRateList.size()][columnNames.length];
                for (int i = 0; i < threadContextSwitchRateList.size(); i++) {
                    ThreadContextSwitchRate threadContextSwitchRate = threadContextSwitchRateList.get(i);
                    data[i][0] = threadContextSwitchRate.getTimestamp().toString();
                    data[i][1] = threadContextSwitchRate.getType();
                    data[i][2] = threadContextSwitchRate.getValue().toString();
                    data[i][3] = String.valueOf(threadContextSwitchRate.getAttributes().get("thread.osName") == null ? "" : threadContextSwitchRate.getAttributes().get("thread.osName"));
                    data[i][4] = String.valueOf(threadContextSwitchRate.getAttributes().get("thread.name") == null ? "" : threadContextSwitchRate.getAttributes().get("thread.name"));
                }
            }

            @Override
            public int getRowCount() {
                return threadContextSwitchRateList.size();
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
