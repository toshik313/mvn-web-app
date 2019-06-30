package com.toshi313.jsp;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class IndexTest {

    @Test
    public void index_jspが表示されること() {

        System.setProperty("webdriver.chrome.driver", "C:/Users/Toshiyuki/maven_prj/mvn-web-app/src/test/resources/chromedriver_win32/chromedriver.exe");

        WebDriver wd = new ChromeDriver();
        wd.get("http://localhost:8080/mvn-web-app/");
        wd.quit();
    }
}
