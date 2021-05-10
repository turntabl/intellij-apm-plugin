package io.turntabl.ui.java_application;

import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.table.JBTable;
<<<<<<< HEAD
import io.turntabl.model.events.JavaMonitorWait;
=======
import io.turntabl.model.JavaMonitorWait;
>>>>>>> main

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.List;

public class JavaMonitorWaitPanel {
    JBPanel panel;
    JTable table;

    TableModel myData;
    DefaultTableColumnModel columnModel;

    public JavaMonitorWaitPanel (TableModel tableModel) {
        panel = new JBPanel(new BorderLayout());
        table = new JBTable(tableModel);
        table.setRowSelectionAllowed(true);
<<<<<<< HEAD
=======

>>>>>>> main

        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
        table.getColumnModel().getColumn(5).setPreferredWidth(200);
        table.getColumnModel().getColumn(6).setPreferredWidth(200);
        table.getColumnModel().getColumn(7).setPreferredWidth(200);
        table.getColumnModel().getColumn(8).setPreferredWidth(200);

        panel.add(new JBScrollPane(table, JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER),  BorderLayout.CENTER);
    }

    public JBPanel getJavaMonitorWaitComponent (){
        return panel;
    }

    public static class JavaMonitorWaitTableModel extends AbstractTableModel {
        String[] columnNames = {"Start Time", "Duration", "End Time","Event Thread","Monitor Class","Notifier Thread","Timeout","Time Out","Monitor Address"};
        String[][] data;
        private List<JavaMonitorWait> javaMonitorWaitList;

        public JavaMonitorWaitTableModel(List<JavaMonitorWait> javaMonitorWaitList) {
            this.javaMonitorWaitList = javaMonitorWaitList;
            data = new String[javaMonitorWaitList.size()][columnNames.length];
            for (int i = 0; i< javaMonitorWaitList.size(); i++){
                JavaMonitorWait javaMonitorWait = javaMonitorWaitList.get(i);
                data[i][0] = javaMonitorWait.getStartTime();
                data[i][1] = javaMonitorWait.getDuration();
                data[i][2] = javaMonitorWait.getEndTime();
                data[i][3] = javaMonitorWait.getEventThread();
                data[i][4] = javaMonitorWait.getMonitorClass();
                data[i][5] = javaMonitorWait.getNotifierThread();
                data[i][6] = javaMonitorWait.getTimeOut();
                data[i][7] = javaMonitorWait.getTimedOut();
                data[i][8]  = javaMonitorWait.getMonitorAddress();
            }

        };

        @Override
        public int getRowCount() {
            return javaMonitorWaitList.size();
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