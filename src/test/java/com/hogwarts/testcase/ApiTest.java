package com.hogwarts.testcase;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.config.SSLConfig;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Properties;

//import org.apache.log4j.Logger;

public class ApiTest {
    //    private Logger logger = Logger.getLogger(ApiTest.class);
    private String baseUrl = "";
    private String expectCityName = "";

    /**
     * Load config file from the environment path user.home.
     *
     * @param propFileName: Properties file name (String)
     * @return Properties object of the config file (Properties)
     */
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
            System.err.println("配置文件加载失败，请检查 " + path + File.separator + propFileName + "文件是否存在！");
        }

        return prop;
    }

    /**
     * Get the city name from the response body.
     *
     * @param cityCode: city code (string)
     * @return: City name (String)
     */
    @Step
    private String getCityName(String cityCode) {
        String fullUrl = baseUrl + cityCode + ".html";
        Response resp = RestAssured
                .given()
                .config((RestAssured.config().sslConfig(new SSLConfig().relaxedHTTPSValidation())))
                .get(fullUrl);
        resp.print();
        return resp.jsonPath().get("weatherinfo.city");
    }

    @BeforeEach
    public void begin() {
        String propFileName = "iInterface.properties";
        Properties prop = loadFromEnvProperties(propFileName);
        String host = prop.getProperty("server_addr", "www.weather.com.cn");
        baseUrl = "http://" + host + "/data/cityinfo/";
    }

    @AfterEach
    public void tearDown() {
        System.out.println(expectCityName + " Test Finished!");

    }

    @Test
    @Feature("Test ShenZhen")
    public void testShenZhen() {
        expectCityName = "深圳";
        String actualCityName = getCityName("101280601");
        Assertions.assertEquals(expectCityName, actualCityName);
    }

    @Test
    @Feature("Test ShangHai")
    public void testShangHai() {
        expectCityName = "上海";
        String actualCityName = getCityName("101020100");
        Assertions.assertEquals(expectCityName, actualCityName);
    }

    @Test
    @Feature("Test Beijing")
    public void testBeijing() {
        expectCityName = "北京";
        String actualCityName = getCityName("101010100");
        Assertions.assertEquals(expectCityName, actualCityName);
    }

}
