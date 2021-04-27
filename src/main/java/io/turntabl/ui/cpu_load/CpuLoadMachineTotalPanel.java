package io.turntabl.ui.cpu_load;

import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.ui.model.CpuLoadMachineTotal;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class CpuLoadMachineTotalPanel {
    JPanel panel;
    JTable table;

    TableModel myData;
    DefaultTableColumnModel columnModel;

    public CpuLoadMachineTotalPanel(TableModel tableModel) {
        panel = new JPanel(new BorderLayout());
        table = new JBTable(tableModel);

        table.setRowSelectionAllowed(true);
        table.setRowSelectionInterval(0, 0);

        table.getColumnModel().getColumn(0).setPreferredWidth(350);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(700);

        panel.add(new JBScrollPane(table, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER),  BorderLayout.CENTER);
    }

    public JPanel getCpuLoadMachineTotalComponent() {
        return panel;
    }

    public static class CpuLoadMachineTotalTableModel extends AbstractTableModel {
        String[] columnNames = {"Timestamp", "Type", "Value"};
        String[][] data;
        private java.util.List<CpuLoadMachineTotal> cpuLoadMachineTotalList;

        public CpuLoadMachineTotalTableModel(List<CpuLoadMachineTotal> cpuLoadMachineTotalList) {
            this.cpuLoadMachineTotalList = cpuLoadMachineTotalList;
            data = new String[cpuLoadMachineTotalList.size()][columnNames.length];
            for (int i = 0; i < cpuLoadMachineTotalList.size(); i++) {
                CpuLoadMachineTotal cpuLoadMachineTotal = cpuLoadMachineTotalList.get(i);
                data[i][0] = String.valueOf(cpuLoadMachineTotal.getTimestamp());
                data[i][1] = cpuLoadMachineTotal.getType();
                data[i][2] = String.valueOf(cpuLoadMachineTotal.getValue());
            }
        }

        @Override
        public int getRowCount() {
            return cpuLoadMachineTotalList.size();
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
