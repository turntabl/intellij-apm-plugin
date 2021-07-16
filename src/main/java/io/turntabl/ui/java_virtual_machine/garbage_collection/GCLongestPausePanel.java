package io.turntabl.ui.java_virtual_machine.garbage_collection;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.model.metrics.GCLongestPause;
import io.turntabl.utils.JsonUtility;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class GCLongestPausePanel {
    private static final JsonUtility jsonUtil = new JsonUtility();
    private static JBPanel panel;
    private JTable table;

    public GCLongestPausePanel(TableModel tableModel) {
        panel = new JBPanel(new BorderLayout());
        table = new JBTable(tableModel);

        table.setRowSelectionAllowed(true);

        table.getColumnModel().getColumn(0).setPreferredWidth(350);
        table.getColumnModel().getColumn(1).setPreferredWidth(350);
        table.getColumnModel().getColumn(2).setPreferredWidth(350);
        table.getColumnModel().getColumn(3).setPreferredWidth(350);
        table.getColumnModel().getColumn(4).setPreferredWidth(350);


        panel.add(new JBScrollPane(table, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
    }

    public JBPanel getGCLongestPauseComponent() {
        return panel;
    }

    public JTable getTable() {
        return this.table;
    }

    public static class GCLongestPauseTableModel extends AbstractTableModel {

        String[] columnNames = {"Timestamp", "Type", "Value", "Attribute Name", "Attribute Cause"};
        String[][] data;
        private List<GCLongestPause> gcLongestPauseList;

        public GCLongestPauseTableModel(List<GCLongestPause> gcLongestPauseList) {
            this.gcLongestPauseList = gcLongestPauseList;

            data = new String[gcLongestPauseList.size()][columnNames.length];
            for (int i = 0; i < gcLongestPauseList.size(); i++) {
                GCLongestPause gcLongestPause = gcLongestPauseList.get(i);

                data[i][0] = jsonUtil.getTime(gcLongestPause.getTimestamp());
                data[i][1] = gcLongestPause.getType();
                data[i][2] = String.valueOf(gcLongestPause.getValue());
                data[i][3] = String.valueOf(gcLongestPause.getAttributes().get("name") == null ? "" : gcLongestPause.getAttributes().get("name"));
                data[i][4] = String.valueOf(gcLongestPause.getAttributes().get("cause") == null ? "" : gcLongestPause.getAttributes().get("cause"));
            }
        }

        @Override
        public int getRowCount() {
            return gcLongestPauseList.size();
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
