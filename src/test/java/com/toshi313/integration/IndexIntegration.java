package com.toshi313.integration;

import static org.junit.Assert.*;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.toshi313.common.Util;

public class IndexIntegration {

    @Test
    public void index_jspが表示されること() throws Exception {

        String chrome_driver_path = System.getProperty("chrome_driver_path");
        String url = System.getProperty("url");

        System.setProperty("webdriver.chrome.driver", chrome_driver_path);

        WebDriver wd = new ChromeDriver();
//        wd.get("http://localhost:8080/mvn-web-app/");


        wd.get(url);
        File image_temp = ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(image_temp, new File("C:/Users/Toshiyuki/maven_prj/mvn-web-app/src/test/evidence/" + Util.getMethodName() + ".png"));
        wd.quit();

        fail("テスト失敗");
    }
}
