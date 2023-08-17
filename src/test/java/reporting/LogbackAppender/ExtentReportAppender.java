package reporting.LogbackAppender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.aventstack.extentreports.ExtentTest;
import reporting.Listeners.ExtentListeners;

import java.util.Optional;

public class ExtentReportAppender extends AppenderBase<ILoggingEvent> {

  @Override
  protected void append(ILoggingEvent event) {
    Optional<ILoggingEvent> eventOptional = Optional.ofNullable(event);
    Optional<ExtentTest> extentTestOptional = Optional.ofNullable(ExtentListeners.testReport.get());

    eventOptional.flatMap(ILoggingEvent -> extentTestOptional)
            .ifPresent(extentTest -> extentTest.info(event.getFormattedMessage()));
  }
}
