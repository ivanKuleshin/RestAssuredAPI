package com.erat.RestAssuredAPI.examplesForEducation.ExtentReportAppenderExample;

import com.erat.RestAssuredAPI.setUp.BaseTest;
import org.testng.annotations.Test;
import reporting.Listeners.ExtentListeners;

public class ExtentReportAppender extends BaseTest {

  @Test
  public void check() {
    logger.info("Example log from {}", ExtentReportAppender.class.getSimpleName());
    logger.info("Example {}", ExtentListeners.class.getSimpleName());
  }

  @Test
  public void printLoggerWrapper() {

    String loggerWrapper = logger.getClass().getName();
    logger.info("Logger wrapper being used: {}", loggerWrapper);
  }
}
