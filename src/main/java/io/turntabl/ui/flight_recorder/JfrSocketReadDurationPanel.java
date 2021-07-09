package io.turntabl.ui.flight_recorder;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.model.metrics.JfrSocketReadDuration;
import io.turntabl.utils.JsonUtility;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class JfrSocketReadDurationPanel {
    private JBPanel panel;
    private JTable table;
    private static final JsonUtility jsonUtil = new JsonUtility();

    public JfrSocketReadDurationPanel(TableModel tableModel) {
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
        table.getColumnModel().getColumn(8).setPreferredWidth(350);

        panel.add(new JBScrollPane(table, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER),  BorderLayout.CENTER);
    }

    public JBPanel getJfrSocketReadDurationComponent() {
        return panel;
    }

    public static class JfrSocketReadDurationTableModel extends AbstractTableModel {
        String[] columnNames = {"Timestamp", "Type", "Count", "Sum", "Min", "Max", "Interval ms", "Thread OS Name", "Thread Name"};
        String[][] data;
        private java.util.List<JfrSocketReadDuration> jfrSocketReadDurationList;

        public JfrSocketReadDurationTableModel(List<JfrSocketReadDuration> jfrSocketReadDurationList) {
            this.jfrSocketReadDurationList = jfrSocketReadDurationList;
            data = new String[jfrSocketReadDurationList.size()][columnNames.length];
            for (int i = 0; i < jfrSocketReadDurationList.size(); i++) {
                JfrSocketReadDuration jfrSocketReadDuration = jfrSocketReadDurationList.get(i);
                data[i][0] = jsonUtil.getTime(jfrSocketReadDuration.getTimestamp());
                data[i][1] = jfrSocketReadDuration.getType();
                data[i][2] = jfrSocketReadDuration.getValue().get("count") == null ? "" : String.valueOf(jfrSocketReadDuration.getValue().get("count"));
                data[i][3] = jfrSocketReadDuration.getValue().get("sum") == null ? "" : String.valueOf(jfrSocketReadDuration.getValue().get("sum"));
                data[i][4] = jfrSocketReadDuration.getValue().get("min") == null ? "" : String.valueOf(jfrSocketReadDuration.getValue().get("min"));
                data[i][5] = jfrSocketReadDuration.getValue().get("max") == null ? "" : String.valueOf(jfrSocketReadDuration.getValue().get("max"));
                data[i][6] = String.valueOf(jfrSocketReadDuration.getInterval());
                data[i][7] = jfrSocketReadDuration.getAttributes().get("thread.osName") == null ? "" : jfrSocketReadDuration.getAttributes().get("thread.osName");
                data[i][8] = jfrSocketReadDuration.getAttributes().get("thread.name") == null ? "" : jfrSocketReadDuration.getAttributes().get("thread.name");

            }
        }

        @Override
        public int getRowCount() {
            return jfrSocketReadDurationList.size();
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
