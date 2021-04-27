package io.turntabl.ui.operating_system;

import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.ui.model.CpuLoad;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class CpuLoadPanel {
    JPanel panel;
    JTable table;

    TableModel myData;
    DefaultTableColumnModel columnModel;

    public CpuLoadPanel(TableModel tableModel) {
        panel = new JPanel(new BorderLayout());
        table = new JBTable(tableModel);

        table.setRowSelectionAllowed(true);
        table.setRowSelectionInterval(0, 0);

        table.getColumnModel().getColumn(0).setPreferredWidth(350);
        table.getColumnModel().getColumn(1).setPreferredWidth(350);
        table.getColumnModel().getColumn(2).setPreferredWidth(350);
        table.getColumnModel().getColumn(3).setPreferredWidth(350);
        table.getColumnModel().getColumn(4).setPreferredWidth(350);



        panel.add(new JBScrollPane(table, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER),  BorderLayout.CENTER);
    }

    public JPanel getCpuLoadComponent() {
        return panel;
    }

    public static class CpuLoadTableModel extends AbstractTableModel {
        String[] columnNames = {"StartTime", "Type", "JVM User", "JVM System", "Machine Total"};
        String[][] data;
        private java.util.List<CpuLoad> cpuLoadList;

        public CpuLoadTableModel(List<CpuLoad> cpuLoadList) {
            this.cpuLoadList = cpuLoadList;
            data = new String[cpuLoadList.size()][columnNames.length];
            for (int i = 0; i < cpuLoadList.size(); i++) {
                CpuLoad cpuLoad = cpuLoadList.get(i);
                data[i][0] = String.valueOf(cpuLoad.getStartTime());
                data[i][1] = String.valueOf(cpuLoad.getType());
                data[i][2] = String.valueOf(cpuLoad.getJvmUserValue());
                data[i][3] = String.valueOf(cpuLoad.getJvmSystemValue());
                data[i][4] = String.valueOf(cpuLoad.getMachineTotalValue());
            }
        }

        @Override
        public int getRowCount() {
            return cpuLoadList.size();
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
