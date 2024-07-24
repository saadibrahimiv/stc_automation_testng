package com.testcrew.stc.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReportManager {
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static final Logger logger = LoggerFactory.getLogger(ReportManager.class);

    public static void initReport() {
        if (extent == null) {
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("reports/extent.html");
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
            logger.info("Initialized ExtentReports");
        }
    }

    public static void startTest(String testName) {
        ExtentTest extentTest = extent.createTest(testName);
        test.set(extentTest);
        logger.info("Started test: {}", testName);
    }

    public static void logInfo(String message) {
        test.get().info(message);
        logger.info(message);
    }

    public static void logFail(String message) {
        test.get().fail(message);
        logger.error(message);
    }

    public static void endTest() {
        logger.info("Ended test");
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
            logger.info("Flushed report");
        }
    }
}
