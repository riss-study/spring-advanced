package dev.riss.spring_advanced.app.v3;

import dev.riss.spring_advanced.trace.TraceStatus;
import dev.riss.spring_advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

  private final LogTrace trace;

  public void save (String itemId) {

    TraceStatus status = null;
    try {
      status = trace.begin("OrderRepository.save()");

      if (itemId.equals("ex")) {
        throw new IllegalStateException("throw Exception!");
      }
      sleep(1000);

      trace.end(status);
    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }

  private void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
