package io.turntabl.ui.flame_graph;

import io.turntabl.utils.flame_graph_util.Convert;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ConvertTest {

    @Test
    void stackTracesConvert() throws IOException {
        Convert.convert();

        File jsonFile = new File("C:/flamegraph/stackTraces.json");
        assertTrue(jsonFile.exists());
    }
}