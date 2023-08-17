package com.erat.RestAssuredAPI.examplesForEducation.ExtentReportAppenderExample;

import com.erat.RestAssuredAPI.setUp.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import reporting.Listeners.ExtentListeners;

@Slf4j
public class ExtentReportAppenderTest extends BaseTest {

  @Test
  public void check() {
    log.info("Example log from {}", ExtentReportAppenderTest.class.getSimpleName());
    log.info("Example {}", ExtentListeners.class.getSimpleName());
  }

  @Test
  public void printLoggerWrapper() {
    String loggerWrapper = log.getClass().getName();
    log.info("Logger wrapper being used: {}", loggerWrapper);
  }
}
