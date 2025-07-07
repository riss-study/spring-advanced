package dev.riss.spring_advanced.app.v5;

import dev.riss.spring_advanced.trace.callback.TraceTemplate;
import dev.riss.spring_advanced.trace.logtrace.LogTrace;
import dev.riss.spring_advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV5 {

  private final TraceTemplate template;

  public OrderRepositoryV5(LogTrace trace) {
    this.template = new TraceTemplate(trace);
  }

  public void save (String itemId) {
    template.execute("OrderRepository.save()", () -> {
      if (itemId.equals("ex")) {
        throw new IllegalStateException("throw Exception!");
      }
      sleep(1000);
      return null;
    });
  }

  private void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
