package io.turntabl.ui.flight_recorder;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
import io.turntabl.ui.model.FlightRecording;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;


public class FlightRecordingPanel {
    JPanel panel;
    JTable table;

    TableModel myData;
    DefaultTableColumnModel columnModel;

    public FlightRecordingPanel (TableModel tableModel) {
        panel = new JPanel(new BorderLayout());
        table = new JBTable(tableModel);
        table.setRowSelectionAllowed(true);
        table.setRowSelectionInterval(0, 0);

        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
        table.getColumnModel().getColumn(5).setPreferredWidth(200);
        table.getColumnModel().getColumn(6).setPreferredWidth(200);
        table.getColumnModel().getColumn(7).setPreferredWidth(200);
        table.getColumnModel().getColumn(8).setPreferredWidth(200);
        table.getColumnModel().getColumn(9).setPreferredWidth(200);

        panel.add(new JBScrollPane(table, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER),  BorderLayout.CENTER);
    }

    public JPanel getFlightRecordingComponent (){
        return panel;
    }

    public static class FlightRecordingTableModel extends AbstractTableModel {
        String[] columnNames = {"startTime", "duration", "endTime","eventThread", "id", "name","destination", "max_age","max_size", "recording_duration"};
        String[][] data;
        private List<FlightRecording> FlightRecordingList;

        public FlightRecordingTableModel(List<FlightRecording> FlightRecordingList) {
            this.FlightRecordingList = FlightRecordingList;
            data = new String[FlightRecordingList.size()][columnNames.length];
            for (int i = 0; i< FlightRecordingList.size(); i++){
                FlightRecording FlightRecording = FlightRecordingList.get(i);
                data[i][0] = FlightRecording.getStartTime();
                data[i][1] = FlightRecording.getDuration();
                data[i][2] = FlightRecording.getEndTime();
                data[i][3] = FlightRecording.getEventThread();
                data[i][4] = String.valueOf(FlightRecording.getId());
                data[i][5] = FlightRecording.getName();
                data[i][6] = FlightRecording.getDestination();
                data[i][7] = FlightRecording.getMax_age();
                data[i][8] = FlightRecording.getMax_size();
                data[i][9]  = FlightRecording.getRecording_duration();
            }

        };

        @Override
        public int getRowCount() {
            return FlightRecordingList.size();
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
