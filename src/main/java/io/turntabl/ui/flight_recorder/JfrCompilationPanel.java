package io.turntabl.ui.flight_recorder;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.model.events.JfrCompilation;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class JfrCompilationPanel {
    JBPanel panel;
    JTable table;

    TableModel myData;
    DefaultTableColumnModel columnModel;

    public JfrCompilationPanel(TableModel tableModel) {
        panel = new JBPanel(new BorderLayout());
        table = new JBTable(tableModel);

        table.setRowSelectionAllowed(true);

        table.getColumnModel().getColumn(0).setPreferredWidth(350);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(700);
//        table.getColumnModel().getColumn(3).setPreferredWidth(350);
//        table.getColumnModel().getColumn(5).setPreferredWidth(200);
//        table.getColumnModel().getColumn(6).setPreferredWidth(700);
//        table.getColumnModel().getColumn(7).setPreferredWidth(350);
//        table.getColumnModel().getColumn(8).setPreferredWidth(200);
//        table.getColumnModel().getColumn(9).setPreferredWidth(700);

        panel.add(new JBScrollPane(table, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER),  BorderLayout.CENTER);
    }

    public JBPanel getJfrCompilationComponent() {
        return panel;
    }

    public static class JfrCompilationTableModel extends AbstractTableModel {
        String[] columnNames = {"timestamp",
                "duration",
                "instrumentationName",
                "hostHostname",
                "threadName",
                "collectorName",
                "desc",
                "succeeded",
                "instrumentationProvider"};

        String[][] data;
        private List<JfrCompilation> jfrCompilationList;

        public JfrCompilationTableModel(List<JfrCompilation> jfrCompilationList) {
            this.jfrCompilationList = jfrCompilationList;
            data = new String[jfrCompilationList.size()][columnNames.length];
            for (int i = 0; i < jfrCompilationList.size(); i++) {
                JfrCompilation jfrCompilation = jfrCompilationList.get(i);
                data[i][0] = jfrCompilation.getTimestamp().toString();
                data[i][1] = jfrCompilation.getDuration().toString();
                data[i][2] = jfrCompilation.getInstrumentationName();
//                data[i][3] = jfrCompilation.getHostHostname();
//                data[i][4] = jfrCompilation.getThreadName();
//                data[i][5] = jfrCompilation.getCollectorName();
//                data[i][6] = jfrCompilation.getDesc();
//                data[i][7] = jfrCompilation.getSucceeded().toString();
//                data[i][8] = jfrCompilation.getInstrumentationProvider();
//                data[i][9] = jfrCompilation.getHostHostname();

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
    }
}

