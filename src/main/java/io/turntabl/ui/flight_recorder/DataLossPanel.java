package io.turntabl.ui.flight_recorder;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.model.metrics.DataLoss;
import io.turntabl.utils.JsonUtility;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class DataLossPanel {
    private JBPanel panel;
    private JTable table;
    private static final JsonUtility jsonUtil = new JsonUtility();

    public DataLossPanel(TableModel tableModel) {
        panel = new JBPanel(new BorderLayout());
        table = new JBTable(tableModel);

        table.setRowSelectionAllowed(true);

        table.getColumnModel().getColumn(0).setPreferredWidth(350);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(350);
        table.getColumnModel().getColumn(3).setPreferredWidth(350);


        panel.add(new JBScrollPane(table, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
    }

    public JBPanel getDataLossComponent() {
        return panel;
    }

    public static class DataLossTableModel extends AbstractTableModel {
        String[] columnNames = {"Start Time", "Amount", "Total", "Attributes"};
        String[][] data;

        private List<DataLoss> dataLossList;

        public DataLossTableModel(List<DataLoss> dataLossList) {
            this.dataLossList = dataLossList;
            data = new String[dataLossList.size()][columnNames.length];
            for (int i = 0; i < dataLossList.size(); i++) {
                DataLoss dataLoss = dataLossList.get(i);
                data[i][0] = jsonUtil.getTime(dataLoss.getStartTime());
                data[i][1] = String.valueOf(dataLoss.getAmount());
                data[i][2] = String.valueOf(dataLoss.getTotal());
                data[i][3] = dataLoss.getAttributes().get("")  == null ? "" : dataLoss.getAttributes().get("");
            }
        }

        @Override
        public int getRowCount() {
            return dataLossList.size();
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