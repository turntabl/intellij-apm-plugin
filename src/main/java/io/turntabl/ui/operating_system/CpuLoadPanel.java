package io.turntabl.ui.operating_system;

import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.ui.model.CpuLoad;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class CpuLoadPanel {
    private JPanel panel;
    private JTable table;
    private TableModel myData;
    private DefaultTableColumnModel columnModel;

    public CpuLoadPanel(TableModel tableModel) {
        panel = new JPanel(new BorderLayout());
        table = new JBTable(tableModel);

        table.setRowSelectionAllowed(true);

        table.getColumnModel().getColumn(0).setPreferredWidth(550);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(350);
        table.getColumnModel().getColumn(3).setPreferredWidth(350);
        table.getColumnModel().getColumn(4).setPreferredWidth(350);
        table.getColumnModel().getColumn(5).setPreferredWidth(350);
        table.getColumnModel().getColumn(6).setPreferredWidth(350);

        panel.add(new JBScrollPane(table, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
    }

    public JPanel getCpuLoadComponent() {
        return panel;
    }

    public static class CpuLoadTableModel extends AbstractTableModel {
        String[] columnNames = {"Start Time", "Type", "JVM User", "JVM System", "Machine Total", "Thread OS Name", "Thread Name"};
        String[][] data;
        private java.util.List<CpuLoad> cpuLoadList;

        public CpuLoadTableModel(List<CpuLoad> cpuLoadList) {
            this.cpuLoadList = cpuLoadList;
            data = new String[cpuLoadList.size()][columnNames.length];
            for (int i = 0; i < cpuLoadList.size(); i++) {
                CpuLoad cpuLoad = cpuLoadList.get(i);

                data[i][0] = String.valueOf(new Date(cpuLoad.getStartTime()));
                data[i][1] = cpuLoad.getType();
                data[i][2] = String.valueOf(cpuLoad.getJvmUserValue() == null ? "" : cpuLoad.getJvmUserValue());
                data[i][3] = String.valueOf(cpuLoad.getJvmSystemValue() == null ? "" : cpuLoad.getJvmSystemValue());
                data[i][4] = String.valueOf(cpuLoad.getMachineTotalValue() == null ? "" : cpuLoad.getMachineTotalValue());
                data[i][5] = cpuLoad.getAttributes().get("thread.osName") == null ? "" : cpuLoad.getAttributes().get("thread.osName");
                data[i][6] = cpuLoad.getAttributes().get("thread.name") == null ? "" : cpuLoad.getAttributes().get("thread.name");
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

    public JTable getTable() {
        return this.table;
    }
}
