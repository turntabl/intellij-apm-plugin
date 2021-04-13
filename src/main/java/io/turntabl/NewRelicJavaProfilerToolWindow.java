package io.turntabl;

import com.intellij.openapi.vcs.changes.PreviewDiffSplitterComponent;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTabbedPane;
import com.intellij.ui.ScrollPaneFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.table.JBTable;
import com.intellij.ui.treeStructure.SimpleNode;
import com.intellij.ui.treeStructure.SimpleTree;
import com.intellij.ui.treeStructure.SimpleTreeBuilder;
import com.intellij.ui.treeStructure.Tree;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class NewRelicJavaProfilerToolWindow {

    private JLabel label, flameGraphLabel, callTreeLabel, methodListLabel, eventsLabel1, eventsLabel2;
    private JPanel mainPanel, flameGraphPanel, callTreePanel, methodListPanel, eventsPanel, p1, p2;
    private PreviewDiffSplitterComponent splitterComponent;
    private JTabbedPane tabbedPane;
    private  JSplitPane sl;

    public NewRelicJavaProfilerToolWindow(ToolWindow toolWindow) {
        label = new JLabel("NewRelic Profiler");

        mainPanel = new JPanel(new GridLayout(1, 1));

        flameGraphPanel = new JPanel();
        callTreePanel = new JPanel();
        methodListPanel = new JPanel();
        eventsPanel = new JPanel();
        p1 = new JPanel();
        p2 = new JPanel();
        p1.setSize(200, 300);
        p2.setSize(200,300);

        flameGraphLabel = new JLabel("Flame Graph is displayed here!!!!!!!!!!!");
        callTreeLabel = new JLabel("Call Tree is displayed here!!!!!!!!!!!");
        methodListLabel = new JLabel("Method List is displayed here!!!!!!!!!!!");
        eventsLabel1 = new JLabel("Events 1 are displayed here!!!!!!!!!!!");
        eventsLabel2 = new JLabel("Events 2 are displayed here!!!!!!!!!!!");

        flameGraphPanel.add(flameGraphLabel);
        callTreePanel.add(callTreeLabel);
        methodListPanel.add(methodListLabel);
//        p1.add(eventsLabel1);
        p2.add(eventsLabel2);
        sl = new JSplitPane(SwingConstants.VERTICAL, p1, p2);
        sl.setOrientation(SwingConstants.VERTICAL);
//        sl.setOneTouchExpandable(true);
        eventsPanel.add(sl);

        // Create a root tree item as event
        DefaultMutableTreeNode event = new DefaultMutableTreeNode("Events");

        //create other tree items as event names
        DefaultMutableTreeNode flightRecorder = new DefaultMutableTreeNode("Flight Recorder");
        DefaultMutableTreeNode objectAllocation = new DefaultMutableTreeNode("Object Allocations");
        DefaultMutableTreeNode memoryConsumption = new DefaultMutableTreeNode("Memory Consumption");

        //create other tree items as employees
        DefaultMutableTreeNode employee1 = new DefaultMutableTreeNode("VM operations");
        DefaultMutableTreeNode employee2 = new DefaultMutableTreeNode("CPU usage and peaks");
        DefaultMutableTreeNode employee3 = new DefaultMutableTreeNode("CPU usage and peaks");

        //add employees to sales event
        flightRecorder.add(employee1);
        flightRecorder.add(employee2);
        flightRecorder.add(employee3);

        //create other tree items as employees
        DefaultMutableTreeNode employee4 = new DefaultMutableTreeNode("CPU usage and peaks");
        DefaultMutableTreeNode employee5 = new DefaultMutableTreeNode("CPU usage and peaks");


        //add employees to marketing event
        objectAllocation.add(employee4);
        objectAllocation.add(employee5);

        //create other tree items as employees
        DefaultMutableTreeNode employee6 = new DefaultMutableTreeNode("CPU usage and peaks");
        DefaultMutableTreeNode employee7 = new DefaultMutableTreeNode("CPU usage and peaks");

        //add employees to sales event
        memoryConsumption.add(employee6);
        memoryConsumption.add(employee7);

        //add events to event item
        event.add(flightRecorder);
        event.add(objectAllocation);
        event.add(memoryConsumption);

        //create the tree with event as root node
        JTree tree = new Tree(event);
        JScrollPane treeView = new JBScrollPane(tree);
        p1.add(treeView);


        tabbedPane = new JBTabbedPane();

        tabbedPane.add("Flame Graph", flameGraphPanel);
        tabbedPane.add("Call Tree", callTreePanel);
        tabbedPane.add("Method List", methodListPanel);
        tabbedPane.add("Events", sl);


        mainPanel.add(tabbedPane);

    }

    private void sayHello() {
        label.setText("Hello New Relic");
    }

    public JComponent getContent() {
        return mainPanel;
    }
}
