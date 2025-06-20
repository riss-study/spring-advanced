package dev.riss.spring_advanced.app.v4;

import dev.riss.spring_advanced.trace.TraceStatus;
import dev.riss.spring_advanced.trace.logtrace.LogTrace;
import dev.riss.spring_advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

  private final LogTrace trace;

  public void save (String itemId) {

    AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
      @Override
      protected Void call() {
        if (itemId.equals("ex")) {
          throw new IllegalStateException("throw Exception!");
        }
        sleep(1000);
        return null;
      }
    };

    template.execute("OrderRepository.save()");
  }

  private void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
