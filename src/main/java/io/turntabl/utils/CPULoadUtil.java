package io.turntabl.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.turntabl.ui.model.CpuLoad;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CPULoadUtil {
    private final JsonUtility jsonUtils;
    private ObjectMapper mapper = new ObjectMapper();

    public CPULoadUtil(JsonUtility jsonUtils) {
        this.jsonUtils = jsonUtils;
    }

    public List<CpuLoad> getCPULoad(JSONArray metricsArray) {
        Stream<JSONObject> cpuLoadMetric = jsonUtils.getStream(metricsArray);
        return cpuLoadMetric
                .filter(m -> m.get("name").toString().toLowerCase().startsWith("jfr.cpuload"))
                .map(c -> {
                    CpuLoad cpuLoad = null;
                    try {
                        cpuLoad = mapper.readValue(c.toJSONString(), CpuLoad.class);

                        String name = cpuLoad.getName().toLowerCase();
                        double value = Double.parseDouble(c.get("value").toString());

                        if (name.endsWith("jvmuser")) {
                            cpuLoad.setJvmUserValue(value);
                        } else if (name.endsWith("jvmsystem")) {
                            cpuLoad.setJvmSystemValue(value);
                        } else {
                            cpuLoad.setMachineTotalValue(value);
                        }
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    return cpuLoad;
                }).collect(Collectors.toList());

    }

    public Map<Long, List<CpuLoad>> groupCpuLoadByTimestamp(List<CpuLoad> cpuLoadList) {
        return cpuLoadList
                .stream()
                .collect(Collectors.groupingBy(CpuLoad::getStartTime));
    }

    public List<CpuLoad> getCPULoadConsolidated(List<CpuLoad> list) {
        Map<Long, List<CpuLoad>> cpuLoadMap = groupCpuLoadByTimestamp(list);

        return cpuLoadMap.entrySet().stream()
                .map(s -> {
                    CpuLoad cpu = s.getValue().get(0);
                    CpuLoad cpu2 = s.getValue().get(1);
                    CpuLoad cpu3 = s.getValue().get(2);

                    if (cpu2.getName().toLowerCase().endsWith("jvmuser")) {
                        cpu.setJvmUserValue(cpu2.getJvmUserValue());
                    } else if (cpu2.getName().toLowerCase().endsWith("jvmsystem")) {
                        cpu.setJvmSystemValue(cpu2.getJvmSystemValue());
                    } else {
                        cpu.setMachineTotalValue(cpu2.getMachineTotalValue());
                    }

                    if (cpu3.getName().toLowerCase().endsWith("jvmuser")) {
                        cpu.setJvmUserValue(cpu3.getJvmUserValue());
                    } else if (cpu3.getName().toLowerCase().endsWith("jvmsystem")) {
                        cpu.setJvmSystemValue(cpu3.getJvmSystemValue());
                    } else {
                        cpu.setMachineTotalValue(cpu3.getMachineTotalValue());
                    }

                    return cpu;
                }).collect(Collectors.toList());
    }

    public String formatDate(Long timestamp) {
        Date date = new Date(timestamp);
        DateFormat formatter = new SimpleDateFormat("ss");
        return formatter.format(date);
    }

    public XYDataset createDataSet(List<CpuLoad> cpuLoadList) {
        XYSeries jvmUserSeries = new XYSeries("JVM User");
        XYSeries jvmSystemSeries = new XYSeries("JVM System");
        XYSeries machineTotalSeries = new XYSeries("Machine Total");

        for (int i = 0; i < cpuLoadList.size(); i++) {
            int startTime = Integer.parseInt(formatDate(cpuLoadList.get(i).getStartTime()));

            if (cpuLoadList.get(i).getName().toLowerCase().endsWith("jvmuser")) {
                jvmUserSeries.add(cpuLoadList.get(i).getJvmUserValue(), startTime);
            } else if (cpuLoadList.get(i).getName().toLowerCase().endsWith("jvmsystem")) {
                jvmSystemSeries.add(cpuLoadList.get(i).getJvmSystemValue(), startTime);
            } else {
                machineTotalSeries.add(cpuLoadList.get(i).getMachineTotalValue(), startTime);
            }

        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(jvmUserSeries);
        dataset.addSeries(jvmSystemSeries);
        dataset.addSeries(machineTotalSeries);

        return dataset;
    }
}
