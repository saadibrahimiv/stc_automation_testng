// package com.testcrew.stc.tests;

// import com.aventstack.extentreports.ExtentReports;
// import com.aventstack.extentreports.ExtentTest;
// import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
// import com.aventstack.extentreports.reporter.configuration.Theme;
// import com.testcrew.stc.base.BaseTest;
// import com.testcrew.stc.pages.SubscriptionPage;
// import com.testcrew.stc.utils.DataProviderUtils;
// import com.testcrew.stc.utils.WebDriverManager;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.WebElement;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.testng.Assert;
// import org.testng.annotations.AfterClass;
// import org.testng.annotations.BeforeClass;
// import org.testng.annotations.Optional;
// import org.testng.annotations.Parameters;
// import org.testng.annotations.Test;
// import java.text.SimpleDateFormat;
// import java.util.Date;
// import java.util.List;
// import java.util.Map;

// public class SubscriptionTest extends BaseTest {
//     private WebDriver driver;
//     private SubscriptionPage subscriptionPage;
//     private static final Logger logger = LoggerFactory.getLogger(SubscriptionTest.class);

//     private ExtentReports extentReports;
//     private ExtentHtmlReporter htmlReporter;
//     private ExtentTest test;

//     @Parameters({"browser", "url"})
//     @BeforeClass
//     public void setUp(@Optional("firefox") String browser, @Optional("https://subscribe.stctv.com/") String url) {
//         logger.info("Setting up test with browser: {} and URL: {}", browser, url);
//         super.setUp(browser, url);
        

//         driver = WebDriverManager.getDriver();
//         if (driver == null) {
//             logger.error("WebDriver is null. Exiting setup.");
//             throw new NullPointerException("WebDriver is null.");
//         }

//         subscriptionPage = new SubscriptionPage(driver);
//         if (subscriptionPage == null) {
//             logger.error("SubscriptionPage is null. Exiting setup.");
//             throw new NullPointerException("SubscriptionPage is null.");
//         }

//         subscriptionPage.navigateToSubscriptionPage(url);

//         String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//         String reportPath = "target/extent-report-" + timestamp + ".html";

//         htmlReporter = new ExtentHtmlReporter(reportPath);
//         htmlReporter.config().setTheme(Theme.STANDARD);
//         htmlReporter.config().setDocumentTitle("Subscription Test Report");
//         htmlReporter.config().setReportName("Subscription Test Report");

//         extentReports = new ExtentReports();
//         extentReports.attachReporter(htmlReporter);
//         extentReports.setSystemInfo("Environment", "Test");
//         extentReports.setSystemInfo("Tester", "Your Name");

//         logger.info("Setup completed. Browser: {}, URL: {}", browser, url);
//     }

//     @Test(dataProvider = "subscriptionData", dataProviderClass = DataProviderUtils.class)
//     public void validateSubscriptionPackages(Map<String, Object> countryData) throws Exception {
//         String country = (String) countryData.get("country");
//         List<Map<String, String>> expectedPackages = (List<Map<String, String>>) countryData.get("packages");
//         int numberOfPackages1 = expectedPackages.size();
//         System.out.println("Number of expected packages found: " + numberOfPackages1);
//         test = extentReports.createTest("Validate Subscription Packages for " + country);
//         logger.info("Started test: Validate Subscription Packages for {}", country);

//         try {
//             subscriptionPage.changeCountryAndLanguage(country, "en");
//             Thread.sleep(5000);

//             List<WebElement> actualPackages = subscriptionPage.getSubscriptionPackages();
//             List<WebElement> priceElements = subscriptionPage.getPriceElements();
//             Assert.assertEquals(actualPackages.size(), expectedPackages.size(), "Number of packages mismatch for " + country);
//             int numberOfPackages = actualPackages.size();
//             System.out.println("Number of actual packages found: " + numberOfPackages);
//             for (int j = 0; j < numberOfPackages; j++) {
//                 WebElement packageElement = actualPackages.get(j);
//                 System.out.println("Package " + j + ": " + packageElement.getText());}

//             for (int i = 0; i < actualPackages.size(); i++) {
//                 WebElement actualPackage = actualPackages.get(i);
//                 WebElement priceElement = priceElements.get(i);
//                 String actualType = subscriptionPage.getPackageTitle(actualPackage);
//                 String actualPrice = subscriptionPage.getPackagePrice(priceElement);
//                 String actualCurrency = subscriptionPage.getPackageCurrency(priceElement);
//                 System.out.println( actualType + actualCurrency + actualPrice); 

//                 // Map<String, String> expectedPackage = expectedPackages.stream()
//                 //         .filter(pkg -> pkg.get("type").equalsIgnoreCase(actualType))
//                 //         .findFirst()
//                 //         .orElseThrow(() -> new AssertionError("Package type not found in expected packages: " + actualType));
//                 Map<String, String> expectedPackage = expectedPackages.get(i);
//                 System.out.println( expectedPackage);
//                 String expectedType = expectedPackage.get("type");
//                 String expectedPrice = expectedPackage.get("price");
//                 String expectedCurrency = expectedPackage.get("currency");


//                 test.info("Package Type: " + expectedType);
//                 test.info("Expected Price: " + expectedPrice);
//                 test.info("Actual Price: " + actualPrice);
//                 test.info("Expected Currency: " + expectedCurrency);
//                 test.info("Actual Currency: " + actualCurrency);
//                 Assert.assertEquals(actualType, expectedPackage.get("type"), "Type mismatch for " + country + actualType);
//                 Assert.assertEquals(actualPrice, expectedPrice, "Price mismatch for " +country + expectedType);
//                 Assert.assertEquals(actualCurrency, expectedCurrency, "Currency mismatch for " +country+ expectedType);
//                 test.pass("Validated package details for " + expectedType);
//             }
//         } catch (AssertionError e) {
//             if (test != null) {
//                 test.fail("Assertion failed: " + e.getMessage());
//             }
//             logger.error("Assertion failed while validating subscription packages", e);
//             throw e;
//         } catch (Exception e) {
//             if (test != null) {
//                 test.fail("Test encountered an error: " + e.getMessage());
//             }
//             logger.error("Error while validating subscription packages", e);
//             throw e;
//         } finally {
//             extentReports.flush();
//         }
//     }

//     @AfterClass
//     public void tearDown() {
//         if (driver != null) {
//             driver.quit();
//         }
//     }
// }


package com.testcrew.stc.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.testcrew.stc.base.BaseTest;
import com.testcrew.stc.pages.SubscriptionPage;
import com.testcrew.stc.utils.DataProviderUtils;
import com.testcrew.stc.utils.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class SubscriptionTest extends BaseTest {
    private WebDriver driver;
    private SubscriptionPage subscriptionPage;
    private static final Logger logger = LoggerFactory.getLogger(SubscriptionTest.class);

    private ExtentReports extentReports;
    private ExtentHtmlReporter htmlReporter;
    private ExtentTest test;

    @Parameters({"browser", "url"})
    @BeforeClass
    public void setUp(@Optional("chrome") String browser, @Optional("https://subscribe.stctv.com/") String url) {
        logger.info("Setting up test with browser: {} and URL: {}", browser, url);
        super.setUp(browser, url);

        driver = WebDriverManager.getDriver();
        if (driver == null) {
            logger.error("WebDriver is null. Exiting setup.");
            throw new NullPointerException("WebDriver is null.");
        }

        subscriptionPage = new SubscriptionPage(driver);
        if (subscriptionPage == null) {
            logger.error("SubscriptionPage is null. Exiting setup.");
            throw new NullPointerException("SubscriptionPage is null.");
        }

        subscriptionPage.navigateToSubscriptionPage(url);

        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String reportPath = "target/extent-report-" + timestamp + ".html";

        htmlReporter = new ExtentHtmlReporter(reportPath);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Subscription Test Report");
        htmlReporter.config().setReportName("Subscription Test Report");

        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("Environment", "Test");
        extentReports.setSystemInfo("Tester", "Your Name");

        logger.info("Setup completed. Browser: {}, URL: {}", browser, url);
    }

    @Test(dataProvider = "subscriptionData", dataProviderClass = DataProviderUtils.class)
    public void validateSubscriptionPackages(Map<String, Object> countryData) throws Exception {
        String country = (String) countryData.get("country");
        List<Map<String, String>> expectedPackages = (List<Map<String, String>>) countryData.get("packages");

        test = extentReports.createTest("Validate Subscription Packages for " + country);
        logger.info("Started test: Validate Subscription Packages for {}", country);

        try {
            subscriptionPage.changeCountryAndLanguage(country, "en");
            Thread.sleep(5000);

            List<WebElement> actualPackages = subscriptionPage.getSubscriptionPackages();
            List<WebElement> priceElements = subscriptionPage.getPriceElements();
            Assert.assertEquals(actualPackages.size(), expectedPackages.size(), "Number of packages mismatch");

            for (int i = 0; i < actualPackages.size(); i++) {
                WebElement actualPackage = actualPackages.get(i);
                WebElement priceElement = priceElements.get(i);

                String actualType = subscriptionPage.getPackageTitle(actualPackage);
                String actualPrice = subscriptionPage.getPackagePrice(priceElement);
                String actualCurrency = subscriptionPage.getPackageCurrency(priceElement);

                Map<String, String> expectedPackage = expectedPackages.stream()
                        .filter(pkg -> pkg.get("type").equalsIgnoreCase(actualType))
                        .findFirst()
                        .orElseThrow(() -> new AssertionError("Package type not found in expected packages: " + actualType));

                String expectedPrice = expectedPackage.get("price");
                String expectedCurrency = expectedPackage.get("currency");

                test.info("Package Type: " + actualType);
                test.info("Expected Price: " + expectedPrice);
                test.info("Actual Price: " + actualPrice);
                test.info("Expected Currency: " + expectedCurrency);
                test.info("Actual Currency: " + actualCurrency);

                Assert.assertEquals(actualType, expectedPackage.get("type"), "Type mismatch for " + actualType);
                Assert.assertEquals(actualPrice, expectedPrice, "Price mismatch for " + actualType);
                Assert.assertEquals(actualCurrency, expectedCurrency, "Currency mismatch for " + actualType);
                test.pass("Validated package details for " + actualType);
            }
        } catch (AssertionError e) {
            if (test != null) {
                test.fail("Assertion failed: " + e.getMessage());
            }
            logger.error("Assertion failed while validating subscription packages", e);
            throw e;
        } catch (Exception e) {
            if (test != null) {
                test.fail("Test encountered an error: " + e.getMessage());
            }
            logger.error("Error while validating subscription packages", e);
            throw e;
        } finally {
            extentReports.flush();
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (extentReports != null) {
            extentReports.flush();
        }
    }
}
