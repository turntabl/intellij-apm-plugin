package io.turntabl.ui.flight_recorder;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.ui.model.DataLoss;
import io.turntabl.ui.model.JvmInformation;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

public class JvmInformationPanel {
    JBPanel panel;
    JTable table;

    TableModel myData;
    DefaultTableColumnModel columnModel;

    public JvmInformationPanel(TableModel tableModel) {
        panel = new JBPanel(new BorderLayout());
        table = new JBTable(tableModel);

        table.setRowSelectionAllowed(true);
        table.setRowSelectionInterval(0, 0);

        table.getColumnModel().getColumn(0).setPreferredWidth(350);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(700);
//        table.getColumnModel().getColumn(3).setPreferredWidth(350);
//        table.getColumnModel().getColumn(5).setPreferredWidth(200);
//        table.getColumnModel().getColumn(6).setPreferredWidth(700);

        panel.add(new JBScrollPane(table, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER),  BorderLayout.CENTER);
    }

    public JBPanel getJvmInformationComponent() {
        return panel;
    }

    public static class JvmInformationTableModel extends AbstractTableModel {
        String[] columnNames = {"timeStamp", "jvmProperty", "jvmPropertyValue", "instrumentationName",  "hostName", "collectorName", "instrumentationProvider"};
        String[][] data;
        private List<JvmInformation> jvmInformationPanelList;

        public JvmInformationTableModel(List<JvmInformation> jvmInformationPanelList) {
            this.jvmInformationPanelList = jvmInformationPanelList;
            data = new String[jvmInformationPanelList.size()][columnNames.length];
            for (int i = 0; i < jvmInformationPanelList.size(); i++) {
                JvmInformation jvmInformation = jvmInformationPanelList.get(i);
                data[i][0] = jvmInformation.getTimeStamp().toString();
                data[i][1] = jvmInformation.getJvmProperty();
                data[i][2] = jvmInformation.getJvmPropertyValue();
//                data[i][3] = jvmInformation.getInstrumentationName();
//                data[i][4] = jvmInformation.getHostName();
//                data[i][5] = jvmInformation.getCollectorName();
//                data[i][6] = jvmInformation.getInstrumentationProvider();
            }
        }

        @Override
        public int getRowCount() {
            return jvmInformationPanelList.size();
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
