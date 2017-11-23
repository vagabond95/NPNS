package com.naver.npns_demo.util;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonHelper {

    private Map<String, String> mParams;

    public JsonHelper() {
        mParams = new HashMap<String, String>();
    }

    public void putDataToJson(String key, String value) {
        mParams.put(key, value);
    }

    public String getJson() {
        JSONObject parameter = new JSONObject(mParams);
        return parameter.toString();
    }
}
