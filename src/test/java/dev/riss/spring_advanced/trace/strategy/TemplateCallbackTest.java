package dev.riss.spring_advanced.trace.strategy;

import dev.riss.spring_advanced.trace.strategy.code.template.Callback;
import dev.riss.spring_advanced.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTest {

  /**
   * 템플릿 콜백 패턴 - 익명 내부 클래스
   */
  @Test
  void callbackV1() {
    TimeLogTemplate template = new TimeLogTemplate();
    template.execute(new Callback() {
      @Override
      public void call() {
        log.info("execute business logic1");
      }
    });

    template.execute(new Callback() {
      @Override
      public void call() {
        log.info("execute business logic2");
      }
    });
  }

  /**
   * 템플릿 콜백 패턴 - 익명 Lambda
   */
  @Test
  void callbackV2() {
    TimeLogTemplate template = new TimeLogTemplate();
    template.execute(() -> log.info("execute business logic1"));
    template.execute(() -> log.info("execute business logic2"));
  }


}
