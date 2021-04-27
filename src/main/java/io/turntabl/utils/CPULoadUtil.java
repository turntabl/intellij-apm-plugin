package io.turntabl.utils;

import io.turntabl.ui.model.CPULoad;

import java.util.Collections;
import java.util.List;

public class CPULoadUtil {
    private final JsonUtility jsonUtils;

    public CPULoadUtil(JsonUtility jsonUtils) {
        this.jsonUtils = jsonUtils;
    }


    public List<CPULoad> getCPUMetric() {
//        JSONArray cpuInfoArray = jsonUtils.readJsonFile("CPU");


        return Collections.emptyList();
    }
}
