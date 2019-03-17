package com.hogwarts.Interface;

import java.io.IOException;

public class CityWeather {
    private String url = "";

    public String geturl() {
        return url;
    }

    public String getHttpRespone(String cityCode) throws IOException {
        String line = "";
        String httpResults = "";
        url = ("http://www.weather.com.cn/data/cityinfo/" + cityCode + ".html");
        httpResults = HttpClient.sendGet(url);

        return httpResults;
    }
}
