package io.turntabl.utils.flame_graph_util;


import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Convert {
    public static void convert() throws IOException {
//        File file = new File("C:/flamegraph/stackTraces.txt");
        File file = new File("/home/zaneta-work/flamegraph/stackTraces.txt");
//        File file1 = new File("C:/flamegraph/stackTracesNoThreadName.txt");
        File file1 = new File("/home/zaneta-work/flamegraph/stackTracesNoThreadName.txt");

        Profile profile = Folded.parseFolded(file);
        Profile profile1 = Folded.parseFolded(file1);

        String jsonString = profile.getRootNode().MarshalIndentJSON();
        String jsonString1 = profile1.getRootNode().MarshalIndentJSON();

        try {
            HttpClient client = new HttpClient();
            client.start();

            HttpContentResponse response = (HttpContentResponse) client
                    .POST("http://localhost:8787/fg/with-thread-names")
                    .content(new StringContentProvider(jsonString))
                    .send();

            HttpContentResponse response1 = (HttpContentResponse) client
                    .POST("http://localhost:8787/fg/without-thread-names")
                    .content(new StringContentProvider(jsonString1))
                    .send();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
