package com.toshi313.jsp;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.toshi313.common.Util;



public class IndexTest {


    @Test
    public void index_jspが表示されること() throws Exception {

        System.setProperty("webdriver.chrome.driver", "C:/Users/Toshiyuki/maven_prj/mvn-web-app/src/test/resources/chromedriver_win32/chromedriver.exe");

        WebDriver wd = new ChromeDriver();
        wd.get("http://localhost:8080/mvn-web-app/");

        File image_temp = ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(image_temp, new File("C:/Users/Toshiyuki/maven_prj/mvn-web-app/src/test/evidence/" + Util.getMethodName() + ".png"));
        wd.quit();
    }
}
