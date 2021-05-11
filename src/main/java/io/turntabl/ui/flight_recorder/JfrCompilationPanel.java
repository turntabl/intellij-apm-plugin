package io.turntabl.ui.flight_recorder;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.model.events.JfrCompilation;
import io.turntabl.utils.JsonUtility;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class JfrCompilationPanel {
    private JBPanel panel;
    private JTable table;

    public JfrCompilationPanel(TableModel tableModel) {
        panel = new JBPanel(new BorderLayout());
        table = new JBTable(tableModel);

        table.setRowSelectionAllowed(true);

        table.getColumnModel().getColumn(0).setPreferredWidth(350);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(350);
        table.getColumnModel().getColumn(3).setPreferredWidth(350);
        table.getColumnModel().getColumn(5).setPreferredWidth(350);
        table.getColumnModel().getColumn(6).setPreferredWidth(350);
        table.getColumnModel().getColumn(7).setPreferredWidth(350);
        table.getColumnModel().getColumn(8).setPreferredWidth(350);
        table.getColumnModel().getColumn(9).setPreferredWidth(350);

        panel.add(new JBScrollPane(table, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER),  BorderLayout.CENTER);
    }

    public JBPanel getJfrCompilationComponent() {
        return panel;
    }

    public static class JfrCompilationTableModel extends AbstractTableModel {
        String[] columnNames = {"Event Type", "TimeStamp",
                "Duration", "Instrumentation Name", "Host Name", "Thread Name","Collector Name",
                "Desc", "Succeeded", "Instrumentation Provider"};

        String[][] data;
        private List<JfrCompilation> jfrCompilationList;

        public JfrCompilationTableModel(List<JfrCompilation> jfrCompilationList) {
            this.jfrCompilationList = jfrCompilationList;
            data = new String[jfrCompilationList.size()][columnNames.length];
            for (int i = 0; i < jfrCompilationList.size(); i++) {
                JfrCompilation jfrCompilation = jfrCompilationList.get(i);
                data[i][0] = jfrCompilation.getEventType();
                data[i][1] = String.valueOf(jfrCompilation.getTimestamp());
                data[i][2] = String.valueOf(jfrCompilation.getDuration());
                data[i][3] = jfrCompilation.getInstrumentationName();
                data[i][4] = jfrCompilation.getHostName();
                data[i][5] = jfrCompilation.getThreadName();
                data[i][6] = jfrCompilation.getCollectorName();
                data[i][7] = jfrCompilation.getDesc();
                data[i][8] = String.valueOf(jfrCompilation.getSucceeded());
                data[i][9] = jfrCompilation.getInstrumentationProvider();

            }
        }

        @Override
        public int getRowCount() {
            return jfrCompilationList.size();
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

