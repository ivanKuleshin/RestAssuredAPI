package reporting.LogbackAppender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.aventstack.extentreports.ExtentTest;
import reporting.Listeners.ExtentListeners;

public class ExtentReportAppender extends AppenderBase<ILoggingEvent> {

  @Override
  protected void append(ILoggingEvent event) {
    if (event != null) {
      String logMessage = event.getFormattedMessage();
      ExtentTest extentTest = ExtentListeners.testReport.get();
      if (extentTest != null) {
        extentTest.info(logMessage);
      }
    }
  }
}
