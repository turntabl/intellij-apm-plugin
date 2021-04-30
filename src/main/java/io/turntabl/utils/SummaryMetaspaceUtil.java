package io.turntabl.utils;

import io.turntabl.ui.model.SummaryMetaspace;

import java.util.Collections;
import java.util.List;

public class SummaryMetaspaceUtil {

    private final JsonUtility jsonUtils;

    public SummaryMetaspaceUtil(JsonUtility jsonUtils) {
        this.jsonUtils = jsonUtils;
    }


    public List<SummaryMetaspace> getSummaryMetaspaceMetric() {
//        JSONArray summaryInfoArray = jsonUtils.readJsonFile("Summary");

        return Collections.emptyList();
    }
}
