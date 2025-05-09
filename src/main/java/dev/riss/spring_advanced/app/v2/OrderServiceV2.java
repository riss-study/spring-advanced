package dev.riss.spring_advanced.app.v2;

import dev.riss.spring_advanced.trace.TraceId;
import dev.riss.spring_advanced.trace.TraceStatus;
import dev.riss.spring_advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

  private final OrderRepositoryV2 orderRepository;
  private final HelloTraceV2 trace;

  public void orderItem (TraceId traceId, String itemId) {

    TraceStatus status = null;
    try {
      status = trace.beginSync(traceId, "OrderService.orderItem()");

      orderRepository.save(status.getTraceId(), itemId);

      trace.end(status);
    } catch (Exception e) {
      trace.exception(status, e);
      throw e;
    }
  }
}

