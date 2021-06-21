package io.turntabl.utils.flame_graph_util;


import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.HttpContentResponse;
import org.eclipse.jetty.client.util.StringContentProvider;

import java.io.IOException;
import java.util.List;

public class Convert {
    public static void convert(List<String> threadStack, List<String> nonThreadStack) throws IOException {

        Profile profile = Folded.parseFolded(threadStack);
        Profile profile1 = Folded.parseFolded(nonThreadStack);

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
