package io.turntabl.utils;

import io.turntabl.ui.model.CpuLoad;

import java.util.Collections;
import java.util.List;

public class CPULoadUtil {
    private final JsonUtility jsonUtils;

    public CPULoadUtil(JsonUtility jsonUtils) {
        this.jsonUtils = jsonUtils;
    }


    public List<CpuLoad> getCPUMetric() {
//        JSONArray cpuInfoArray = jsonUtils.readJsonFile("CPU");

        return Collections.emptyList();
    }
}
