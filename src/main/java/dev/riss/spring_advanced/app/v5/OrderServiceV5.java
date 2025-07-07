package dev.riss.spring_advanced.app.v5;

import dev.riss.spring_advanced.trace.callback.TraceCallback;
import dev.riss.spring_advanced.trace.callback.TraceTemplate;
import dev.riss.spring_advanced.trace.logtrace.LogTrace;
import dev.riss.spring_advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

  private final OrderRepositoryV5 orderRepository;
  private final TraceTemplate template;

  public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace trace) {
    this.orderRepository = orderRepository;
    this.template = new TraceTemplate(trace);
  }

  public void orderItem (String itemId) {
    template.execute("OrderService.orderItem()", () -> {
      orderRepository.save(itemId);
      return null;
    });
  }
}

