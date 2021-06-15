package io.turntabl.utils.flame_graph_util;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Convert {
    public static void convert() throws IOException {
        File file = new File("C:/flamegraph/stackTraces.txt");
        File file1 = new File("C:/flamegraph/stackTracesNoThreadName.txt");

        Profile profile = Folded.parseFolded(file);
        Profile profile1 = Folded.parseFolded(file1);

        String jsonString = profile.getRootNode().MarshalIndentJSON();
        String jsonString1 = profile1.getRootNode().MarshalIndentJSON();

        File jsonFile = new File("C:/flamegraph/stackTraces.json");
        if (!jsonFile.exists()) {
            jsonFile.createNewFile();
        }

        File jsonFile1 = new File("C:/flamegraph/stackTracesNoThreadName.json");
        if (!jsonFile1.exists()) {
            jsonFile1.createNewFile();
        }

        try{
            FileWriter fileWriter = new FileWriter("C:/flamegraph/stackTraces.json");
            fileWriter.write(jsonString);
            fileWriter.close();

            FileWriter fileWriter1 = new FileWriter("C:/flamegraph/stackTracesNoThreadName.json");
            fileWriter1.write(jsonString1);
            fileWriter1.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
