package com.erat.RestAssuredAPI.examplesForEducation.ExtentReportAppenderExample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import reporting.Listeners.ExtentListeners;

public class ExtentReportAppender {

  private static final Logger logger = LoggerFactory.getLogger(ExtentListeners.class);

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
