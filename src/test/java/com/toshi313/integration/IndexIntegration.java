package com.toshi313.integration;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.toshi313.common.Util;

public class IndexIntegration {

    @Test
    public void index_jspが表示されること() throws Exception {

        String snapshop_save_path = System.getProperty("snapshop_save_path");
//        String chrome_driver_path = System.getProperty("chrome_driver_path");
        String phantomjs_binary_path = System.getProperty("phantomjs_binary_path");
        String url = System.getProperty("url");

//        System.setProperty("webdriver.chrome.driver", chrome_driver_path);
        System.setProperty("phantomjs.binary.path", phantomjs_binary_path);

//        WebDriver wd = new ChromeDriver();
        WebDriver wd = new PhantomJSDriver();

        wd.get(url);

        File image_temp = ((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(image_temp, new File(snapshop_save_path + "/" + Util.getClassName() + "-" + Util.getMethodName() + ".png"));

        assertThat(wd.getTitle(), is("index.jsp"));

        wd.quit();
    }
}
