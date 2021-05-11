package Java.utils;

import io.turntabl.model.events.JavaMonitorWait;
import junit.framework.TestCase;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class JavaMonitorWaitUtilTest extends TestCase {
    String jsonString = "[\n" +
            "    {\n" +
            "        \"common\": {\n" +
            "               \"eventType\": \"JfrJavaMonitorWait\",\n" +
            "                \"timestamp\": 1619441624895,\n" +
            "                \"instrumentation.name\": \"JFR\",\n" +
            "                \"host.hostname\": \"DESKTOP-8AO41P3/172.21.224.1\",\n" +
            "                \"collector.name\": \"JFR-Uploader\",\n" +
            "                \"instrumentation.provider\": \"JFR-Uploader\"\n" +
            "            }\n" +

            "        \"events\": [\n" +
            "            {\n" +
            "                \"duration\": 9266,\n" +
            "                \"stackTrace\":\"{\"type\":\"stacktrace\"," +
            "                 \"language\":\"java\",\"version\":1,\"truncated\"" +
            "                  :false,\"payload\":[{\"desc\":\"java.lang.Object.wait(J)V\",\"line\":\"-1\"," +
            "                  \"bytecodeIndex\":\"0\"},{\"desc\":\"java.lang.ref.ReferenceQueue.remove(J)Ljava/lang/ref/Reference;\"," +
            "                   \"line\":\"155\",\"bytecodeIndex\":\"59\"},{\"desc\":\"java.lang.ref.ReferenceQueue.remove()Ljava/lang/ref/Reference;\"," +
            "                   \"line\":\"176\",\"bytecodeIndex\":\"2\"},{\"desc\":\"java.lang.ref.Finalizer$FinalizerThread.run()V\"," +
            "                   \"line\":\"170\",\"bytecodeIndex\":\"37\"}]}\",\n" +
            "                 \"thread.name\":\"Finalizer\", \n" +
            "                  \"class\":\"java.lang.ref.ReferenceQueue$Lock\", \n" +
            "            },\n" +
            "            {\n" +
            "                \"duration\": 8893,\n" +
            "                \"stackTrace\":\"{\"type\":\"stacktrace\"," +
            "                 \"language\":\"java\",\"version\":1,\"truncated\"" +
            "                  :false,\"payload\":[{\"desc\":\"java.lang.Object.wait(J)V\",\"line\":\"-1\"," +
            "                  \"bytecodeIndex\":\"0\"},{\"desc\":\"java.lang.ref.ReferenceQueue.remove(J)Ljava/lang/ref/Reference;\"," +
            "                   \"line\":\"155\",\"bytecodeIndex\":\"59\"},{\"desc\":\"java.lang.ref.ReferenceQueue.remove()Ljava/lang/ref/Reference;\"," +
            "                   \"line\":\"176\",\"bytecodeIndex\":\"2\"},{\"desc\":\"java.lang.ref.Finalizer$FinalizerThread.run()V\"," +
            "                   \"line\":\"170\",\"bytecodeIndex\":\"37\"}]}\",\n" +
            "                 \"thread.name\":\"Common-Cleaner\", \n" +
            "                  \"class\":\"java.lang.ref.ReferenceQueue$Lock\", \n" +
            "            },\n" +
            "            },\n" +
            "]";


        JsonUtility jsonUtil = new JsonUtility();
        JavaMonitorWaitUtil javaMonitorWaitUtil = new JavaMonitorWaitUtil(jsonUtil);

        @Test
        void getJavaMonitorWait() {
            Optional<JSONArray> jsonArray = jsonUtil.readMetricsJson(jsonString);
            List<JavaMonitorWait> javaMonitorWaitList = javaMonitorWaitUtil.getJavaMonitorWaitList(jsonArray.get());

            System.out.println(javaMonitorWaitList);
            assertEquals(1,javaMonitorWaitList.size());
        }

    }
