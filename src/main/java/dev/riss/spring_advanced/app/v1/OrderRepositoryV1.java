package dev.riss.spring_advanced.app.v1;

import dev.riss.spring_advanced.trace.TraceStatus;
import dev.riss.spring_advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

  private final HelloTraceV1 trace;

  public void save (String itemId) {

    TraceStatus status = null;
    try {
      status = trace.begin("OrderRepository.save()");

      // save logic
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
