package dev.riss.spring_advanced.trace.template;

import dev.riss.spring_advanced.trace.template.code.AbstractTemplate;
import dev.riss.spring_advanced.trace.template.code.SubClassLogic1;
import dev.riss.spring_advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

  @Test
  void templateMethodV0 () {
    logic1();
    logic2();
  }

  private void logic1 () {
    long startTime = System.currentTimeMillis();
    // execute business logic
    log.info("execute business logic1");
    // end business logic
    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("cost time 1: {} ms", resultTime);
  }


  private void logic2 () {
    long startTime = System.currentTimeMillis();
    // execute business logic
    log.info("execute business logic2");
    // end business logic
    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("cost time 2: {} ms", resultTime);
  }

  /**
   * 템플릿 메서드 패턴 적용
   */
  @Test
  void templateMethodV1 () {
    AbstractTemplate template1 = new SubClassLogic1();
    template1.execute();

    AbstractTemplate template2 = new SubClassLogic2();
    template2.execute();
  }

  @Test
  void templateMethodV2 () {
    AbstractTemplate template1 = new AbstractTemplate() {
      @Override
      protected void call() {
        log.info("execute business logic1");
      }
    };
    log.info("class name1: {}", template1.getClass());
    template1.execute();

    AbstractTemplate template2 = new AbstractTemplate() {
      @Override
      protected void call() {
        log.info("execute business logic2");
      }
    };
    log.info("class name2: {}", template2.getClass());
    template2.execute();
  }
}
