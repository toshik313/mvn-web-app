package com.toshi313.integration;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.toshi313.common.Util;

public class IndexIntegration {

    private WebDriver phantom_wd;
//    private WebDriver chrome_wd;
    private String snapshop_save_path;
    private String url_root;

    @Before
    public void setUp() {

        // maven-failsafe-pluginに記載している値を取得

        this.snapshop_save_path = System.getProperty("snapshop_save_path");
        this.url_root = System.getProperty("url");

//        String chrome_driver_path = System.getProperty("chrome_driver_path");
//        System.setProperty("webdriver.chrome.driver", chrome_driver_path);
//        this.chrome_wd = new ChromeDriver();

        String phantomjs_binary_path = System.getProperty("phantomjs_binary_path");
        System.setProperty("phantomjs.binary.path", phantomjs_binary_path);
        this.phantom_wd = new PhantomJSDriver();
    }


    @After
    public void tearDown() {
        this.phantom_wd.quit();
//        this.chrome_wd.quit();
    }


    @Test
    public void it01初期表示として目次画面が表示されることを確認する() throws Exception {

        this.phantom_wd.get(this.url_root + "/index.jsp");

        File image_temp = ((TakesScreenshot)this.phantom_wd).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(image_temp, new File(this.snapshop_save_path + "/" + Util.getClassName() + Util.CM_SEP + Util.getMethodName() + "目次画面.png"));

        assertThat(this.phantom_wd.getTitle(), is("目次画面"));
    }


    @Test
    public void it02目次画面の表示ボタンクリックで都道府県一覧画面が表示されることを確認する() throws Exception {

        this.phantom_wd.get(this.url_root + "/index.jsp");


        this.phantom_wd.findElement(By.id("btn_view")).click();


        File image_temp = ((TakesScreenshot)this.phantom_wd).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(image_temp, new File(this.snapshop_save_path + "/" + Util.getClassName() + Util.CM_SEP + Util.getMethodName() + "都道府県一覧画面.png"));

        assertThat(this.phantom_wd.getTitle(), is("都道府県一覧画面"));

    }



//    @Test
//    public void index_jspが表示されることbyChrome() throws Exception {
//
//        this.chrome_wd.get(this.url_root + "index.jsp");
//
//        File image_temp = ((TakesScreenshot)this.chrome_wd).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(image_temp, new File(this.snapshop_save_path + "/" + Util.getClassName() + Util.CM_SEP + Util.getMethodName() + ".png"));
//
//        assertThat(this.chrome_wd.getTitle(), is("目次画面"));
//    }
}
