package com.testcrew.stc.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public class SubscriptionPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(SubscriptionPage.class);

    private By languageSpecificElement = By.id("translation-btn");
    private By subscriptionPackages = By.className("plan-title");
    private By priceElements = By.className("price");
    public SubscriptionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void navigateToSubscriptionPage(String url) {
        try {
            driver.get(url);
            logger.info("Navigated to Subscription Page: {}", url);
            wait.until(ExpectedConditions.presenceOfElementLocated(languageSpecificElement));
        } catch (Exception e) {
            logger.error("Failed to navigate to Subscription Page: {}", url, e);
            throw e;
        }
    }

    public void changeCountryAndLanguage(String country, String language) {
        try {
            String currentUrl = driver.getCurrentUrl();
            String newUrl = constructUrlWithCountryAndLanguage(currentUrl, country, language);
            driver.get(newUrl);
            logger.info("Changed URL to: {}", newUrl);
            wait.until(ExpectedConditions.urlContains(country + "-" + language));
            wait.until(ExpectedConditions.presenceOfElementLocated(languageSpecificElement));
            logger.info("Country and language changed successfully to: {}-{}", country, language);
        } catch (Exception e) {
            logger.error("Failed to change country and language to: {}-{}", country, language, e);
            throw e;
        }
    }

    private String constructUrlWithCountryAndLanguage(String currentUrl, String country, String language) {
        String baseUrl = "https://subscribe.stctv.com";
        return baseUrl + "/" + country + "-" + language;
    }

    public List<WebElement> getSubscriptionPackages() {
        try {
            return driver.findElements(subscriptionPackages);
        } catch (Exception e) {
            logger.error("Failed to get subscription packages", e);
            throw e;
        }
    }

    public String getPackageTitle(WebElement packageElement) {
        try {
            return packageElement.getText().trim();
        } catch (Exception e) {
            logger.error("Failed to get package title", e);
            throw e;
        }
    }

    public List<WebElement> getPriceElements() {
        try {
            return driver.findElements(priceElements);
        } catch (Exception e) {
            logger.error("Failed to get price elements", e);
            throw e;
        }
    }

    public String getPackagePrice(WebElement priceElement) {
        try {
            return priceElement.findElement(By.tagName("b")).getText();
        } catch (Exception e) {
            logger.error("Failed to get package price", e);
            throw e;
        }
    }

    public String getPackageCurrency(WebElement priceElement) {
        try {
            return priceElement.findElement(By.tagName("i")).getText().split("/")[0];
        } catch (Exception e) {
            logger.error("Failed to get package currency", e);
            throw e;
        }
    }

}
