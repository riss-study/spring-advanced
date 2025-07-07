package dev.riss.spring_advanced.app.v5;

import dev.riss.spring_advanced.trace.callback.TraceCallback;
import dev.riss.spring_advanced.trace.callback.TraceTemplate;
import dev.riss.spring_advanced.trace.logtrace.LogTrace;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV5 {

  private final OrderServiceV5 orderService;
  private final TraceTemplate template;

  public OrderControllerV5(OrderServiceV5 orderService, LogTrace trace) {
    this.orderService = orderService;
    this.template = new TraceTemplate(trace);
  }

  @GetMapping("/v5/request")
  public String request (String itemId) {
    return template.execute("OrderController.request()", () -> {
      orderService.orderItem(itemId);
      return "ok";
    });
  }

}
