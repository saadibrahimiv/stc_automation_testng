package com.testcrew.stc.base;

import com.testcrew.stc.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected WebDriver driver;

    @BeforeClass
    @Parameters({"browser", "url"})
    public void setUp(@Optional("firefox") String browser, @Optional("https://subscribe.stctv.com/") String url) {
        System.out.println("Browser parameter: " + browser);  
        System.out.println("URL parameter: " + url);  
        System.setProperty("browser", browser);  
        System.setProperty("url", url);  
        driver = WebDriverManager.getDriver();
    }

    @AfterClass
    public void tearDown() {
        WebDriverManager.quitDriver();
    }
}
