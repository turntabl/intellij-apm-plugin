package io.turntabl.jetty;

import io.turntabl.ui.CpuGraph;
import io.turntabl.ui.NewRelicJavaProfilerToolWindow;
import io.turntabl.ui.flight_recorder.SummaryMetaspacePanel;
import io.turntabl.ui.model.CpuLoad;
import io.turntabl.ui.model.SummaryMetaspace;
import io.turntabl.ui.operating_system.CpuLoadPanel;
import io.turntabl.utils.CPULoadUtil;
import io.turntabl.utils.JsonUtility;
import io.turntabl.utils.SummaryMetaspaceUtil;
import io.turntabl.ui.java_virtual_machine.GcHeapSummaryPanel;
import io.turntabl.ui.java_virtual_machine.garbage_collection.*;
import io.turntabl.ui.model.*;
import io.turntabl.ui.flight_recorder.*;
import io.turntabl.ui.java_application.statistics.ThreadAllocationStatisticsPanel;
import io.turntabl.ui.operating_system.*;
import io.turntabl.utils.*;
import org.jfree.data.xy.XYDataset;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.table.TableModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MetricHandler extends HttpServlet {
    private final NewRelicJavaProfilerToolWindow toolWindowComponent;
    private final Logger logger = LoggerFactory.getLogger(MetricHandler.class);
    private final ServletUtils servletUtils = new ServletUtils();
    private final JsonUtility jsonUtil = new JsonUtility();
    private final CPULoadUtil cpuLoadUtil = new CPULoadUtil(jsonUtil);
    private List<CpuLoad> cumulativeCpuLoadList = new ArrayList<>();
    private final GcHeapSummaryUtil gcHeapSummaryUtil = new GcHeapSummaryUtil(jsonUtil);
    private List<GcHeapSummary> cumulativeGcHeapSummaryList = new ArrayList<>();
    private final GarbageCollectionUtil gcUtil = new GarbageCollectionUtil(jsonUtil);
    private List<GCMinorDuration> cumulativeGcMinorDurationList = new ArrayList<>();
    private List<GCMajorDuration> cumulativeGcMajorDurationList = new ArrayList<>();
    private List<G1GarbageCollectionDuration> cumulativeG1GCDurationList = new ArrayList<>();
    private List<GCDuration> cumulativeGCDurationList = new ArrayList<>();
    private List<GCLongestPause> cumulativeGCLongestPauseList = new ArrayList<>();
    private  final ThreadCpuLoadUtil threadCpuLoadUtil = new ThreadCpuLoadUtil(jsonUtil);
    private List<ThreadCpuLoad> cumulativeThreadCpuLoadList = new ArrayList<>();
    private final JfrSocketReadUtil jfrSocketReadUtil = new JfrSocketReadUtil(jsonUtil);
    private List<JfrSocketReadBytesRead> cumulativeBytesReadList = new ArrayList<>();
    private List<JfrSocketReadDuration> cumulativeDurationList = new ArrayList<>();
    private final ThreadAllocatedStatisticsUtil threadAllocatedStatisticsUtil = new ThreadAllocatedStatisticsUtil(jsonUtil);
    private List<ThreadAllocationStatistics> cumulativeThreadAllocatedStatisticsList = new ArrayList<>();

    private final SummaryMetaspaceUtil summaryMetaspaceUtil = new SummaryMetaspaceUtil(jsonUtil);
    private List<SummaryMetaspace> cumulativeSummaryMetaspaceList = new ArrayList<>();
    public MetricHandler() {
        toolWindowComponent = null;
    }

    public MetricHandler(NewRelicJavaProfilerToolWindow toolWindowComponent) {
        this.toolWindowComponent = toolWindowComponent;
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String decompressedString = servletUtils.decompress(req);

        updateCpuLoadPanel(decompressedString); //update the cpuload table

        updateSummaryMetaspacePanel(decompressedString);

//        updateGcHeapSummaryPanel(decompressedString);
//        updateGarbageCollectionPanel(decompressedString);
//        updateThreadLoadPanel(decompressedString); //update the threadCpuLoad table
//        updateThreadAllocatedStatisticsPanel(decompressedString); //Update threadAllocatedStatistics table
//        updateJfrSocketReadPanels(decompressedString);


        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("{ \"status\": \"ok\"}");
    }

    public void updateCpuLoadPanel(String jsonString) {
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);

        if (jsonArray.isPresent()) {
            List<CpuLoad> cpuLoadList = cpuLoadUtil.getCPULoad(jsonArray.get());
            List<CpuLoad> consolidatedList = cpuLoadUtil.getCPULoadConsolidated(cpuLoadList);

            cumulativeCpuLoadList.addAll(consolidatedList);

            toolWindowComponent.getMetricsTree().getCpuLoadTable().setModel(new CpuLoadPanel.CpuLoadTableModel(cumulativeCpuLoadList));
            toolWindowComponent.getMetricsTree().updateComponentMap("CPU Load", (new CpuLoadPanel(new CpuLoadPanel.CpuLoadTableModel(cumulativeCpuLoadList))).getCpuLoadComponent());

            XYDataset dataset = cpuLoadUtil.createDataSet(cumulativeCpuLoadList);
            toolWindowComponent.getMetricsTree().updateCpuLoadGraph(new CpuGraph(dataset, "CPU Load Metric", "Values", "Start Time"));
        }


    }

    public void updateThreadLoadPanel(String jsonString) {
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);
        if (jsonArray.isPresent()) {
            List<ThreadCpuLoad> threadCpuLoadList = threadCpuLoadUtil.getThreadCpuLoad(jsonArray.get());
            List<ThreadCpuLoad> consolidatedList = threadCpuLoadUtil.getThreadCpuLoadConsolidated(threadCpuLoadList);

            cumulativeThreadCpuLoadList.addAll(consolidatedList);
            toolWindowComponent.getMetricsTree().getThreadCpuTable().setModel(new ThreadCpuLoadPanel.ThreadCpuLoadTableModel(cumulativeThreadCpuLoadList));
            toolWindowComponent.getMetricsTree().updateComponentMap("Thread CPU Load",(new ThreadCpuLoadPanel(new ThreadCpuLoadPanel.ThreadCpuLoadTableModel(cumulativeThreadCpuLoadList))).getThreadCpuLoadComponent());

        }
    }

    private void updateJfrSocketReadPanels(String jsonString){
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);

        if (jsonArray.isPresent()){
            List<JfrSocketReadBytesRead> bytesReadList = jfrSocketReadUtil.getJfrSocketReadBytesRead(jsonArray.get());
            List<JfrSocketReadDuration> durationList = jfrSocketReadUtil.getJfrSocketReadDuration(jsonArray.get());

            cumulativeBytesReadList.addAll(bytesReadList);
            cumulativeDurationList.addAll(durationList);

            TableModel bytesReadTableModel = new JfrSocketReadBytesReadPanel.JfrSocketReadBytesReadTableModel(cumulativeBytesReadList);
            TableModel durationTableModel = new JfrSocketReadDurationPanel.JfrSocketReadDurationTableModel(cumulativeDurationList);

            toolWindowComponent.getMetricsTree().getJfrSocketReadBytesReadTable().setModel(bytesReadTableModel);
            toolWindowComponent.getMetricsTree().getJfrSocketReadDurationTable().setModel(durationTableModel);

            toolWindowComponent.getMetricsTree().updateComponentMap("Bytes Read", new JfrSocketReadBytesReadPanel(bytesReadTableModel).getJfrSocketReadBytesReadComponent());
            toolWindowComponent.getMetricsTree().updateComponentMap("Duration", new JfrSocketReadDurationPanel(durationTableModel).getJfrSocketReadDurationComponent());
        }
    }

    private void updateThreadAllocatedStatisticsPanel(String jsonString){
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);
        List<ThreadAllocationStatistics> threadAllocationStatisticsList = threadAllocatedStatisticsUtil.getThreadAllocatedStatistics(jsonArray.get());

        cumulativeThreadAllocatedStatisticsList.addAll(threadAllocationStatisticsList);
        toolWindowComponent.getMetricsTree().getThreadAllocatedStatisticsTable().setModel(new ThreadAllocationStatisticsPanel.ThreadAllocationStatisticsTableModel(cumulativeThreadAllocatedStatisticsList));
        toolWindowComponent.getMetricsTree().updateComponentMap("Thread Allocated Statistics",(new ThreadAllocationStatisticsPanel(new ThreadCpuLoadPanel.ThreadCpuLoadTableModel(cumulativeThreadCpuLoadList))).getThreadAllocationStatisticsComponent());

    }

    public void updateGarbageCollectionPanel(String jsonString) {
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);

        if (jsonArray.isPresent()){
            List<GCMinorDuration> gcMinorDurationList = gcUtil.getGCMinorDuration(jsonArray.get());
            List<GCMajorDuration> gcMajorDurationList = gcUtil.getGCMajorDuration(jsonArray.get());
            List<G1GarbageCollectionDuration> g1GCDurationList = gcUtil.getG1GarbageCollectionDuration(jsonArray.get());
            List<GCDuration> gcDurationList = gcUtil.getGCDuration(jsonArray.get());
            List<GCLongestPause> gcLongestPauseList = gcUtil.getGCLongestPause(jsonArray.get());

            cumulativeGcMinorDurationList.addAll(gcMinorDurationList);
            cumulativeGcMajorDurationList.addAll(gcMajorDurationList);
            cumulativeG1GCDurationList.addAll(g1GCDurationList);
            cumulativeGCDurationList.addAll(gcDurationList);
            cumulativeGCLongestPauseList.addAll(gcLongestPauseList);

            TableModel gcMinorTableModel = new GCMinorDurationPanel.GCMinorDurationTableModel(cumulativeGcMinorDurationList);
            TableModel gcMajorTableModel = new GCMajorDurationPanel.GCMajorDurationTableModel(cumulativeGcMajorDurationList);
            TableModel g1GCTableModel = new G1GarbageCollectionDurationPanel.G1GarbageCollectionDurationTableModel(cumulativeG1GCDurationList);
            TableModel gcDurationTableModel = new GCDurationPanel.GCDurationTableModel(cumulativeGCDurationList);
            TableModel gcLongestPauseTableModel = new GCLongestPausePanel.GCLongestPauseTableModel(cumulativeGCLongestPauseList);

            toolWindowComponent.getMetricsTree().getGCMinorDurationTable().setModel(gcMinorTableModel);
            toolWindowComponent.getMetricsTree().getGCMajorDurationTable().setModel(gcMajorTableModel);
            toolWindowComponent.getMetricsTree().getG1GCDurationTable().setModel(g1GCTableModel);
            toolWindowComponent.getMetricsTree().getGCDurationTable().setModel(gcDurationTableModel);
            toolWindowComponent.getMetricsTree().getGCLongestPauseTable().setModel(gcLongestPauseTableModel);

            toolWindowComponent.getMetricsTree().updateComponentMap("GC Minor Duration", (new GCMinorDurationPanel(gcMinorTableModel)).getGCMinorDurationComponent());
            toolWindowComponent.getMetricsTree().updateComponentMap("GC Major Duration", (new GCMajorDurationPanel(gcMajorTableModel)).getGCMajorDurationComponent());
            toolWindowComponent.getMetricsTree().updateComponentMap("G1 GC Duration", (new G1GarbageCollectionDurationPanel(g1GCTableModel)).getG1GarbageCollectionDurationComponent());
            toolWindowComponent.getMetricsTree().updateComponentMap("GC Duration", (new GCDurationPanel(gcDurationTableModel)).getGCDurationComponent());
            toolWindowComponent.getMetricsTree().updateComponentMap("GC Longest Pause", (new GCLongestPausePanel(gcLongestPauseTableModel)).getGCLongestPauseComponent());

        }
    }

    public void updateGcHeapSummaryPanel(String jsonString){
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);

        if (jsonArray.isPresent()){
            List<GcHeapSummary> gcHeapSummaryList = gcHeapSummaryUtil.getGcHeapSummary(jsonArray.get());
            List<GcHeapSummary> consolidatedList = gcHeapSummaryUtil.getGcHeapSummaryConsolidated(gcHeapSummaryList);

            cumulativeGcHeapSummaryList.addAll(consolidatedList);

            TableModel tableModel = new GcHeapSummaryPanel.GcHeapSummaryTableModel(cumulativeGcHeapSummaryList);

            toolWindowComponent.getMetricsTree().getGcHeapSummaryTable().setModel(tableModel);
            toolWindowComponent.getMetricsTree().updateComponentMap("GC Heap Summary", (new GcHeapSummaryPanel(tableModel)).getGcHeapSummaryComponent());
        }
    }

    public void updateSummaryMetaspacePanel(String jsonString) {
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);

        if (jsonArray.isPresent()) {
            List<SummaryMetaspace> summaryMetaspaceList = summaryMetaspaceUtil.getSummaryMetaspaceMetric(jsonArray.get());
            List<SummaryMetaspace> consolidatedList = summaryMetaspaceUtil.getSummaryMetaspaceConsolidated(summaryMetaspaceList);

            cumulativeSummaryMetaspaceList.addAll(consolidatedList);

            toolWindowComponent.getMetricsTree().getCpuLoadTable().setModel(new SummaryMetaspacePanel.SummaryMetaspaceTableModel(cumulativeSummaryMetaspaceList));
            toolWindowComponent.getMetricsTree().updateComponentMap("Summary Metaspace", (new SummaryMetaspacePanel(new SummaryMetaspacePanel.SummaryMetaspaceTableModel(cumulativeSummaryMetaspaceList))).getSummaryMetaspaceComponent());

            //XYDataset dataset = summaryMetaspaceUtil.createDataSet(cumulativeSummaryMetaspaceList);
            //toolWindowComponent.getMetricsTree().updateCpuLoadGraph(new CpuGraph(dataset, "CPU Load Metric", "Values", "Start Time"));
        }
    }
}
