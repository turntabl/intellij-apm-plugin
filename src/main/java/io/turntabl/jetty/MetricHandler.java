package io.turntabl.jetty;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.turntabl.model.metrics.*;

import io.turntabl.ui.NewRelicJavaProfilerToolWindow;
import io.turntabl.ui.flight_recorder.*;
import io.turntabl.ui.flight_recorder.JfrSocketReadBytesReadPanel;
import io.turntabl.ui.flight_recorder.JfrSocketReadDurationPanel;
import io.turntabl.ui.flight_recorder.SummaryMetaspacePanel;
import io.turntabl.ui.java_application.ObjectAllocationInNewTLabPanel;
import io.turntabl.ui.java_application.ObjectAllocationOutsideTLabPanel;
import io.turntabl.ui.java_application.statistics.ThreadAllocationStatisticsPanel;
import io.turntabl.ui.java_virtual_machine.GcHeapSummaryPanel;
import io.turntabl.ui.java_virtual_machine.garbage_collection.*;
import io.turntabl.ui.operating_system.CpuLoadPanel;
import io.turntabl.ui.operating_system.ThreadCpuLoadPanel;
import io.turntabl.utils.*;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final GcHeapSummaryUtil gcHeapSummaryUtil = new GcHeapSummaryUtil(jsonUtil);
    private final GarbageCollectionUtil gcUtil = new GarbageCollectionUtil(jsonUtil);
    private final ObjectAllocationInNewTLabUtil objectAllocationInNewTLabUtil = new ObjectAllocationInNewTLabUtil(jsonUtil);
    private final ObjectAllocationOutsideTLabUtil objectAllocationOutsideTLabUtil = new ObjectAllocationOutsideTLabUtil(jsonUtil);
    private  final ThreadCpuLoadUtil threadCpuLoadUtil = new ThreadCpuLoadUtil(jsonUtil);
    private final JfrSocketReadUtil jfrSocketReadUtil = new JfrSocketReadUtil(jsonUtil);
    private final SummaryMetaspaceUtil summaryMetaspaceUtil = new SummaryMetaspaceUtil(jsonUtil);
    private final ThreadAllocatedStatisticsUtil threadAllocatedStatisticsUtil = new ThreadAllocatedStatisticsUtil(jsonUtil);

    private List<ObjectAllocationInNewTLab> cumulativeObjectAllocationList = new ArrayList<>();
    private List<ObjectAllocationOutsideTLab> cumulativeObjectAllocationOutsideList = new ArrayList<>();
    private List<CpuLoad> cumulativeCpuLoadList = new ArrayList<>();
    private List<GcHeapSummary> cumulativeGcHeapSummaryList = new ArrayList<>();
    private List<GcHeapSummary> cumulativeHeapSummaryBeforeGCList = new ArrayList<>();
    private List<GcHeapSummary> cumulativeHeapSummaryAfterGCList = new ArrayList<>();
    private List<GCMinorDuration> cumulativeGcMinorDurationList = new ArrayList<>();
    private List<GCMajorDuration> cumulativeGcMajorDurationList = new ArrayList<>();
    private List<G1GarbageCollectionDuration> cumulativeG1GCDurationList = new ArrayList<>();
    private List<GCDuration> cumulativeGCDurationList = new ArrayList<>();
    private List<GCLongestPause> cumulativeGCLongestPauseList = new ArrayList<>();
    private List<ThreadCpuLoad> cumulativeThreadCpuLoadList = new ArrayList<>();
    private List<JfrSocketReadBytesRead> cumulativeBytesReadList = new ArrayList<>();
    private List<JfrSocketReadDuration> cumulativeDurationList = new ArrayList<>();
    private List<ThreadAllocationStatistics> cumulativeThreadAllocatedStatisticsList = new ArrayList<>();
    private List<SummaryMetaspace> cumulativeSummaryMetaspaceList = new ArrayList<>();

    private final ThreadContextSwitchRateUtil threadContextSwitchRateUtil = new ThreadContextSwitchRateUtil(jsonUtil);
    private List<ThreadContextSwitchRate> cumulativeThreadContextSwitchRateList = new ArrayList<>();
    public MetricHandler() {
        toolWindowComponent = null;
    }

    public MetricHandler(NewRelicJavaProfilerToolWindow toolWindowComponent) {
        this.toolWindowComponent = toolWindowComponent;
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String decompressedString = servletUtils.decompress(req);

        updateCpuLoadPanel(decompressedString);
        updateSummaryMetaspacePanel(decompressedString);
        updateObjectAllocationInNewTLabPanel(decompressedString);
        updateObjectAllocationOutsideTLabPanel(decompressedString);
        updateGcHeapSummaryPanel(decompressedString);
        updateGarbageCollectionPanel(decompressedString);
        updateThreadLoadPanel(decompressedString);
        updateThreadAllocatedStatisticsPanel(decompressedString);
        updateJfrSocketReadPanels(decompressedString);
        updateThreadContextSwitchRatePanel(decompressedString);

        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("{ \"status\": \"ok\"}");
    }


    public void updateCpuLoadPanel(String jsonString) throws JsonProcessingException {
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);

        if (jsonArray.isPresent()) {
            List<CpuLoad> cpuLoadList = cpuLoadUtil.getCPULoad(jsonArray.get());
            List<CpuLoad> consolidatedList = cpuLoadUtil.getCPULoadConsolidated(cpuLoadList);

            cumulativeCpuLoadList.addAll(consolidatedList);

            String cumulativeJsonString = jsonUtil.convertToJSONString(cumulativeCpuLoadList);
            postObject("cpu-load", cumulativeJsonString);

            toolWindowComponent.getMetricsTree().updateComponentMap("CPU Load", (new CpuLoadPanel(new CpuLoadPanel.CpuLoadTableModel(cumulativeCpuLoadList))).getCpuLoadComponent());
        }
    }

    public void updateThreadLoadPanel(String jsonString) {
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);
        if (jsonArray.isPresent()) {
            List<ThreadCpuLoad> threadCpuLoadList = threadCpuLoadUtil.getThreadCpuLoad(jsonArray.get());
            List<ThreadCpuLoad> consolidatedList = threadCpuLoadUtil.getThreadCpuLoadConsolidated(threadCpuLoadList);

            cumulativeThreadCpuLoadList.addAll(consolidatedList);
            toolWindowComponent.getMetricsTree().updateComponentMap("Thread CPU Load",(new ThreadCpuLoadPanel(new ThreadCpuLoadPanel.ThreadCpuLoadTableModel(cumulativeThreadCpuLoadList))).getThreadCpuLoadComponent());
        }
    }

    public void updateThreadContextSwitchRatePanel(String jsonString)  throws JsonProcessingException  {
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);
        if (jsonArray.isPresent()) {
            List<ThreadContextSwitchRate> threadContextSwitchRateList = threadContextSwitchRateUtil.getThreadContextSwitchRate(jsonArray.get());

            cumulativeThreadContextSwitchRateList.addAll(threadContextSwitchRateList);

            String cumulativeJsonString = jsonUtil.convertToJSONString(cumulativeThreadContextSwitchRateList);
            postObject("thread-contextswitch-rate", cumulativeJsonString);

            toolWindowComponent.getMetricsTree().updateComponentMap("Thread Context Switch Rate",(new ThreadContextSwitchRatePanel(new ThreadContextSwitchRatePanel.ThreadContextSwitchRateTableModel(cumulativeThreadContextSwitchRateList))).getThreadContextSwitchRateComponent());
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

            toolWindowComponent.getMetricsTree().updateComponentMap("Bytes Read", new JfrSocketReadBytesReadPanel(bytesReadTableModel).getJfrSocketReadBytesReadComponent());
            toolWindowComponent.getMetricsTree().updateComponentMap("Duration", new JfrSocketReadDurationPanel(durationTableModel).getJfrSocketReadDurationComponent());
        }
    }

    private void updateThreadAllocatedStatisticsPanel(String jsonString){
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);
        List<ThreadAllocationStatistics> threadAllocationStatisticsList = threadAllocatedStatisticsUtil.getThreadAllocatedStatistics(jsonArray.get());
        cumulativeThreadAllocatedStatisticsList.addAll(threadAllocationStatisticsList);
        toolWindowComponent.getMetricsTree().updateComponentMap("Thread Allocated Statistics",(new ThreadAllocationStatisticsPanel(new ThreadAllocationStatisticsPanel.ThreadAllocationStatisticsTableModel(cumulativeThreadAllocatedStatisticsList))).getThreadAllocationStatisticsComponent());

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

            toolWindowComponent.getMetricsTree().updateComponentMap("GC Minor Duration", (new GCMinorDurationPanel(gcMinorTableModel)).getGCMinorDurationComponent());
            toolWindowComponent.getMetricsTree().updateComponentMap("GC Major Duration", (new GCMajorDurationPanel(gcMajorTableModel)).getGCMajorDurationComponent());
            toolWindowComponent.getMetricsTree().updateComponentMap("G1 GC Duration", (new G1GarbageCollectionDurationPanel(g1GCTableModel)).getG1GarbageCollectionDurationComponent());
            toolWindowComponent.getMetricsTree().updateComponentMap("GC Duration", (new GCDurationPanel(gcDurationTableModel)).getGCDurationComponent());
            toolWindowComponent.getMetricsTree().updateComponentMap("GC Longest Pause", (new GCLongestPausePanel(gcLongestPauseTableModel)).getGCLongestPauseComponent());

        }
    }

    public void updateGcHeapSummaryPanel(String jsonString) throws JsonProcessingException {
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);

        if (jsonArray.isPresent()){
            List<GcHeapSummary> gcHeapSummaryList = gcHeapSummaryUtil.getGcHeapSummary(jsonArray.get());
            List<GcHeapSummary> consolidatedList = gcHeapSummaryUtil.getGcHeapSummaryConsolidated(gcHeapSummaryList);
            cumulativeGcHeapSummaryList.addAll(consolidatedList);

            List<GcHeapSummary> beforeGCList = gcHeapSummaryUtil.getHeapBeforeGC(consolidatedList);
            List<GcHeapSummary> afterGCList = gcHeapSummaryUtil.getHeapAfterGC(consolidatedList);
            cumulativeHeapSummaryBeforeGCList.addAll(beforeGCList);
            cumulativeHeapSummaryAfterGCList.addAll(afterGCList);

            String heapSummaryBeforeGC = jsonUtil.convertToJSONString(cumulativeHeapSummaryBeforeGCList);
            postObject("heap-summary-before-gc", heapSummaryBeforeGC);

            String heapSummaryAfterGC = jsonUtil.convertToJSONString(cumulativeHeapSummaryAfterGCList);
            postObject("heap-summary-after-gc", heapSummaryAfterGC);

            TableModel tableModel = new GcHeapSummaryPanel.GcHeapSummaryTableModel(cumulativeGcHeapSummaryList);

            toolWindowComponent.getMetricsTree().updateComponentMap("GC Heap Summary", (new GcHeapSummaryPanel(tableModel)).getGcHeapSummaryComponent());
        }
    }

    public void updateSummaryMetaspacePanel(String jsonString) {
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);

        if (jsonArray.isPresent()) {
            List<SummaryMetaspace> summaryMetaspaceList = summaryMetaspaceUtil.getSummaryMetaspaceMetric(jsonArray.get());
            List<SummaryMetaspace> consolidatedList = summaryMetaspaceUtil.getSummaryMetaspaceConsolidated(summaryMetaspaceList);

            cumulativeSummaryMetaspaceList.addAll(consolidatedList);

            toolWindowComponent.getMetricsTree().updateComponentMap("Summary Metaspace", (new SummaryMetaspacePanel(new SummaryMetaspacePanel.SummaryMetaspaceTableModel(cumulativeSummaryMetaspaceList))).getSummaryMetaspaceComponent());
        }
    }
  
    public void updateObjectAllocationInNewTLabPanel(String jsonString) {
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);

        if (jsonArray.isPresent()) {
            List<ObjectAllocationInNewTLab> objectAllocationInNewTLabsList = objectAllocationInNewTLabUtil.getObjectAllocationInNewTLab(jsonArray.get());
            cumulativeObjectAllocationList.addAll(objectAllocationInNewTLabsList);

            toolWindowComponent.getMetricsTree().updateComponentMap("Object Allocation in new TLAB", (new ObjectAllocationInNewTLabPanel(new ObjectAllocationInNewTLabPanel.ObjectAllocationInNewTLabTableModel(cumulativeObjectAllocationList))).getObjectAllocationInNewTLabComponent());
        }
    }

    public void updateObjectAllocationOutsideTLabPanel(String jsonString) {
        Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);

        if (jsonArray.isPresent()) {
            List<ObjectAllocationOutsideTLab> objectAllocationOutsideTLabsList = objectAllocationOutsideTLabUtil.getObjectAllocationOutsideTLab(jsonArray.get());
            cumulativeObjectAllocationOutsideList.addAll(objectAllocationOutsideTLabsList);

            toolWindowComponent.getMetricsTree().updateComponentMap("Object Allocation outside TLAB", (new ObjectAllocationOutsideTLabPanel(new ObjectAllocationOutsideTLabPanel.ObjectAllocationOutsideTLabTableModel(cumulativeObjectAllocationOutsideList))).getObjectAllocationOutsideTLabComponent());
        }
    }

    private void postObject(String urlEnd, String jsonString) {
        try {
            HttpClient client = new HttpClient();
            client.start();

            HttpContentResponse response = (HttpContentResponse) client
                    .POST("http://localhost:8787/mg/" + urlEnd)
                    .content(new StringContentProvider(jsonString))
                    .send();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
