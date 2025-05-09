package dev.riss.spring_advanced.app.v2;

import dev.riss.spring_advanced.trace.TraceId;
import dev.riss.spring_advanced.trace.TraceStatus;
import dev.riss.spring_advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

  private final HelloTraceV2 trace;

  public void save (TraceId traceId, String itemId) {

    TraceStatus status = null;
    try {
      status = trace.beginSync(traceId, "OrderRepository.save()");

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
