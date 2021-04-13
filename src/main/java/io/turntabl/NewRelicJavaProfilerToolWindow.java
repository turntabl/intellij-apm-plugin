package io.turntabl;

import com.intellij.openapi.vcs.changes.PreviewDiffSplitterComponent;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.JBSplitter;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTabbedPane;
import com.intellij.ui.ScrollPaneFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.table.JBTable;
import com.intellij.ui.treeStructure.SimpleTreeBuilder;
import com.intellij.ui.treeStructure.Tree;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

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
        eventsPanel = new JPanel(new MigLayout());
        p1 = new JPanel();
        p2 = new JPanel();
//        p1.setSize(200, 300);
//        p2.setSize(200,300);

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
//        sl = new JSplitPane(SwingConstants.VERTICAL, p1, p2);
//        sl.setOrientation(SwingConstants.VERTICAL);
//        sl.setOneTouchExpandable(true);

        JBSplitter splitter = new JBSplitter(0.13f);
        splitter.setFirstComponent(p1);
        splitter.setSecondComponent(p2);

//        eventsPanel.add(sl);
        eventsPanel.add(splitter, new CC().push().grow());

        // Create a root tree item as department
        DefaultMutableTreeNode department = new DefaultMutableTreeNode("Events");

        //create other tree items as department names
        DefaultMutableTreeNode salesDepartment = new DefaultMutableTreeNode("Flight Recorder");
        DefaultMutableTreeNode marketingDepartment = new DefaultMutableTreeNode("Object Allocations");
        DefaultMutableTreeNode manufacturingDepartment = new DefaultMutableTreeNode("Memory Consumption");

        //create other tree items as subEvents
        DefaultMutableTreeNode subEvent1 = new DefaultMutableTreeNode("VM operations");
        DefaultMutableTreeNode subEvent2 = new DefaultMutableTreeNode("CPU usage and peaks");
        DefaultMutableTreeNode subEvent3 = new DefaultMutableTreeNode("CPU usage and peaks");

        //add subEvents to sales department
        salesDepartment.add(subEvent1);
        salesDepartment.add(subEvent2);
        salesDepartment.add(subEvent3);

        //create other tree items as subEvents
        DefaultMutableTreeNode subEvent4 = new DefaultMutableTreeNode("CPU usage and peaks");
        DefaultMutableTreeNode subEvent5 = new DefaultMutableTreeNode("CPU usage and peaks");


        //add subEvents to marketing department
        marketingDepartment.add(subEvent4);
        marketingDepartment.add(subEvent5);

        //create other tree items as subEvents
        DefaultMutableTreeNode subEvent6 = new DefaultMutableTreeNode("CPU usage and peaks");
        DefaultMutableTreeNode subEvent7 = new DefaultMutableTreeNode("CPU usage and peaks");

        //add subEvents to sales department
        manufacturingDepartment.add(subEvent6);
        manufacturingDepartment.add(subEvent7);

        //add departments to department item
        department.add(salesDepartment);
        department.add(marketingDepartment);
        department.add(manufacturingDepartment);

        //create the tree with department as root node
        JTree tree = new Tree(department);
        JScrollPane treeView = new JBScrollPane(tree);
        p1.add(treeView);


        tabbedPane = new JBTabbedPane();

        tabbedPane.add("Flame Graph", flameGraphPanel);
        tabbedPane.add("Call Tree", callTreePanel);
        tabbedPane.add("Method List", methodListPanel);
        tabbedPane.add("Events", splitter);


        mainPanel.add(tabbedPane);

    }

    private void sayHello() {
        label.setText("Hello New Relic");
    }

    public JComponent getContent() {
        return mainPanel;
    }
}
