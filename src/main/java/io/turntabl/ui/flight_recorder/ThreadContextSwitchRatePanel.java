package io.turntabl.ui.flight_recorder;

import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.ui.model.ThreadContextSwitchRate;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class ThreadContextSwitchRatePanel {

        JPanel panel;
        JTable table;

        TableModel myData;
        DefaultTableColumnModel columnModel;

        public ThreadContextSwitchRatePanel(TableModel tableModel) {
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

        public JPanel getCpuLoadUserComponent() {
            return panel;
        }

        public static class CpuLoadUserTableModel extends AbstractTableModel {
            String[] columnNames = {"name", "type", "value","timestamp","attributes"};
            String[][] data;
            private java.util.List<ThreadContextSwitchRate> threadContextSwitchRateList;

            public CpuLoadUserTableModel(List<ThreadContextSwitchRate> threadContextSwitchRateList) {
                this.threadContextSwitchRateList = threadContextSwitchRateList;
                data = new String[threadContextSwitchRateList.size()][columnNames.length];
                for (int i = 0; i < threadContextSwitchRateList.size(); i++) {
                    ThreadContextSwitchRate threadContextSwitchRate = threadContextSwitchRateList.get(i);
                    data[i][0] = threadContextSwitchRate.getName();
                    data[i][1] = threadContextSwitchRate.getType();
                    data[i][2] = threadContextSwitchRate.getValue().toString();
                    data[i][3] = threadContextSwitchRate.getTimestamp().toString();
                    data[i][4] = threadContextSwitchRate.getAttributes().toString();
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
        }


}
