package com.hogwarts.common;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class CityWeather {
    private String url = "";
    private Logger logger = Logger.getLogger(CityWeather.class);
    private String propFileName = "iInterface.properties";
    private Properties prop = loadFromEnvProperties(propFileName);

    public String geturl() {
        return url;
    }

    public String getHttpRespone(String cityCode) throws IOException {
        String httpResults = "";
        String server_address = prop.getProperty("server_addr", "www.weather.com.cn");
        url = "http://" + server_address + "/data/cityinfo/" + cityCode + ".html";
        logger.debug("请求地址：" + url);

        httpResults = HttpClient.sendGet(url);

        return httpResults;
    }

    //加载配置文件
    private Properties loadFromEnvProperties(String propFileName) {
        Properties prop = null;

        String path = System.getProperty("user.home");

        //读入envProperties属性文件
        try {
            prop = new Properties();
            InputStream in = new BufferedInputStream(
                    new FileInputStream(path + File.separator + propFileName));
            prop.load(in);
            in.close();
        } catch (IOException ioex) {
            logger.error("配置文件加载失败，请检查 " + path + File.separator + propFileName + "文件是否存在！");
        }

        return prop;
    }
}
