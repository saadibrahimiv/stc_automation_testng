package com.testcrew.stc.utils;

import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.Map;

public class DataProviderUtils {

    @DataProvider(name = "subscriptionData")
    public static Object[][] subscriptionData() {
        List<Map<String, Object>> data = JsonUtils.readJsonFile("testdata.json");
        Object[][] dataProvider = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            dataProvider[i][0] = data.get(i);
        }
        return dataProvider;
    }
}
