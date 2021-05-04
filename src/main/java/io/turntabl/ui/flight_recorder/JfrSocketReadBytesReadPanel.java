package io.turntabl.ui.flight_recorder;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.ui.model.JfrSocketReadBytesRead;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class JfrSocketReadBytesReadPanel {
    JBPanel panel;
    JTable table;

    TableModel myData;
    DefaultTableColumnModel columnModel;

    public JfrSocketReadBytesReadPanel(TableModel tableModel) {
        panel = new JBPanel(new BorderLayout());
        table = new JBTable(tableModel);

        table.setRowSelectionAllowed(true);
        table.setRowSelectionInterval(0, 0);

        table.getColumnModel().getColumn(0).setPreferredWidth(350);
        table.getColumnModel().getColumn(1).setPreferredWidth(350);
        table.getColumnModel().getColumn(2).setPreferredWidth(350);
        table.getColumnModel().getColumn(3).setPreferredWidth(350);
        table.getColumnModel().getColumn(4).setPreferredWidth(350);
        table.getColumnModel().getColumn(5).setPreferredWidth(350);
        table.getColumnModel().getColumn(6).setPreferredWidth(350);
        table.getColumnModel().getColumn(7).setPreferredWidth(350);
        table.getColumnModel().getColumn(8).setPreferredWidth(350);

        panel.add(new JBScrollPane(table, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER),  BorderLayout.CENTER);
    }

    public JBPanel getJfrSocketReadBytesReadComponent() {
        return panel;
    }

    public static class JfrSocketReadBytesReadTableModel extends AbstractTableModel {
        String[] columnNames = {"Start Time", "Type", "Count", "Sum", "Min", "Max", "Interval ms", "Thread OS Name", "Thread Name"};
        String[][] data;
        private java.util.List<JfrSocketReadBytesRead> jfrSocketReadBytesReadList;

        public JfrSocketReadBytesReadTableModel(List<JfrSocketReadBytesRead> jfrSocketReadBytesReadList) {
            this.jfrSocketReadBytesReadList = jfrSocketReadBytesReadList;
            data = new String[jfrSocketReadBytesReadList.size()][columnNames.length];
            for (int i = 0; i < jfrSocketReadBytesReadList.size(); i++) {
                JfrSocketReadBytesRead jfrSocketReadBytesRead = jfrSocketReadBytesReadList.get(i);
                data[i][0] = String.valueOf(jfrSocketReadBytesRead.getStartTime());
                data[i][1] = jfrSocketReadBytesRead.getType();
                data[i][2] = jfrSocketReadBytesRead.getValue().get("count") == null ? "" : String.valueOf(jfrSocketReadBytesRead.getValue().get("count"));
                data[i][3] = jfrSocketReadBytesRead.getValue().get("sum") == null ? "" : String.valueOf(jfrSocketReadBytesRead.getValue().get("sum"));
                data[i][4] = jfrSocketReadBytesRead.getValue().get("min") == null ? "" : String.valueOf(jfrSocketReadBytesRead.getValue().get("min"));
                data[i][5] = jfrSocketReadBytesRead.getValue().get("max") == null ? "" : String.valueOf(jfrSocketReadBytesRead.getValue().get("max"));
                data[i][6] = String.valueOf(jfrSocketReadBytesRead.getInterval());
                data[i][7] = jfrSocketReadBytesRead.getAttributes().get("thread.osName") == null ? "" : jfrSocketReadBytesRead.getAttributes().get("thread.osName");
                data[i][8] = jfrSocketReadBytesRead.getAttributes().get("thread.name") == null ? "" : jfrSocketReadBytesRead.getAttributes().get("thread.name");

            }
        }

        @Override
        public int getRowCount() {
            return jfrSocketReadBytesReadList.size();
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
