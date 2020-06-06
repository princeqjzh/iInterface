package com.hogwarts.testcase;

import com.hogwarts.tools.JSONParaser;
import com.hogwarts.common.CityWeather;
import com.hogwarts.tools.ZTestReport;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners({ZTestReport.class})
public class TestExec {
    public String httpResult = null, weatherinfo = null, city = null, exp_city = null;
    public static String cityCode = "";
    CityWeather weather = new CityWeather();

    @Test(groups = {"BaseCase"}, description = "验证深圳天气返回城市名称是否正确")
    public void getShenZhen_Succ() throws Exception {
        exp_city = "深圳";
        cityCode = "101280601";
        Reporter.log("【模拟跳过测试用例】:获取" + exp_city + "天气成功!");
        httpResult = weather.getHttpRespone(cityCode);
        Reporter.log("请求地址: " + weather.geturl());
        Reporter.log("返回结果: " + httpResult);
        weatherinfo = JSONParaser.getJsonValue(httpResult, "weatherinfo");
        city = JSONParaser.getJsonValue(weatherinfo, "city");
        Reporter.log("用例结果: resultCode=>expected: " + exp_city + " ,actual: " + city);
        Assert.assertEquals(city, exp_city);
        throw new SkipException("");
    }

    @Test(groups = {"BaseCase"}, description = "验证北京天气返回城市名称是否正确")
    public void getBeiJing_Succ() throws IOException {
        exp_city = "北京";
        cityCode = "101010100";
        Reporter.log("【正常用例】:获取" + exp_city + "天气成功!");
        httpResult = weather.getHttpRespone(cityCode);
        Reporter.log("请求地址: " + weather.geturl());
        Reporter.log("返回结果: " + httpResult);
        weatherinfo = JSONParaser.getJsonValue(httpResult, "weatherinfo");
        city = JSONParaser.getJsonValue(weatherinfo, "city");
        Reporter.log("用例结果: resultCode=>expected: " + exp_city + " ,actual: " + city);
        Assert.assertEquals(city, exp_city);
    }

    @Test(groups = {"BaseCase"}, description = "验证上海天气返回城市名称是否正确", dataProvider = "dataShangHai")
    public void getShangHai_Succ(String exp_city) throws IOException {
        cityCode = "101020100";
        Reporter.log("【正常用例】:获取" + exp_city + "天气成功!");
        httpResult = weather.getHttpRespone(cityCode);
        Reporter.log("请求地址: " + weather.geturl());
        Reporter.log("返回结果: " + httpResult);
        weatherinfo = JSONParaser.getJsonValue(httpResult, "weatherinfo");
        city = JSONParaser.getJsonValue(weatherinfo, "city");
        Reporter.log("用例结果: resultCode=>expected: " + exp_city + " ,actual: " + city);
        Assert.assertEquals(city, exp_city, "期望城市检测");
    }

    @DataProvider(name = "dataShangHai")
    public Object[][] dataProvider() {
        return new Object[][]{{"上海"}};
    }
}
