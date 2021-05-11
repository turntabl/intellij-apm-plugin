package io.turntabl.ui.java_virtual_machine;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.model.events.JVMInfoEvent;
import Java.utils.JsonUtility;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.List;

public class JVMInfoEventPanel {

    private JBPanel panel;
    private JTable table;
    private static final JsonUtility jsonUtil = new JsonUtility();

    public JVMInfoEventPanel(TableModel tableModel) {
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


        panel.add(new JBScrollPane(table, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
    }

    public JBPanel getJVMInfoEventComponent() {
        return panel;
    }

    public JTable getTable() {
        return this.table;
    }

    public static class JVMInfoEventTableModel extends AbstractTableModel {
        String[] columnNames = {"Event Type", "Start Time", "JVM Property", "Property Value", "JVM Args", "Instrumentation Name", "Host Name", "Collector Name", "Instrumentation Provider"};
        String[][] data;
        private java.util.List<JVMInfoEvent> jvmInfoEventList;

        public JVMInfoEventTableModel(List<JVMInfoEvent> jvmInfoEventList) {
            this.jvmInfoEventList = jvmInfoEventList;
            data = new String[jvmInfoEventList.size()][columnNames.length];
            for (int i = 0; i < jvmInfoEventList.size(); i++) {
                JVMInfoEvent jvmInfoEvent = jvmInfoEventList.get(i);
                data[i][0] = jvmInfoEvent.getEventType();
                data[i][1] = jsonUtil.getTime(jvmInfoEvent.getTimestamp());
                data[i][2] = jvmInfoEvent.getJvmProperty();
                data[i][3] = jvmInfoEvent.getJvmPropertyValue();
                data[i][4] = jvmInfoEvent.getJvmArguments();
                data[i][5] = jvmInfoEvent.getInstrumentationName();
                data[i][6] = jvmInfoEvent.getHostName();
                data[i][7] = jvmInfoEvent.getCollectorName();
                data[i][8] = jvmInfoEvent.getInstrumentationProvider();
            }
        }

        @Override
        public int getRowCount() {
            return jvmInfoEventList.size();
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