package io.turntabl.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;

public class JsonUtility {

    @SuppressWarnings("unchecked")
    public void readJsonFile() {
        JSONParser jsonParser = new JSONParser();
        try {
            String filepath = "src/main/resources/metrics.json";
            JSONArray parsedObject = (JSONArray) jsonParser.parse(new FileReader(filepath));


            JSONObject jsonObject = (JSONObject) parsedObject.get(0);
            JSONArray companyList = (JSONArray) jsonObject.get("metrics");

            Iterator<JSONObject> iterator = companyList.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next().get("attributes"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        JsonUtility util = new JsonUtility();
//        util.readJsonFile();
//    }
}
