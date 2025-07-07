package dev.riss.spring_advanced.trace.strategy;

import dev.riss.spring_advanced.trace.strategy.code.strategy.ContextV2;
import dev.riss.spring_advanced.trace.strategy.code.strategy.Strategy;
import dev.riss.spring_advanced.trace.strategy.code.strategy.StrategyLogic1;
import dev.riss.spring_advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

  /**
   * 전략 패턴 적용 - 컨텍스트 실행 시점마다 전략을 파라미터로 전달
   *   필드 전달보다 유연하게 실행 가능
   */
  @Test
  void strategyV1() {
    ContextV2 contextV2 = new ContextV2();
    contextV2.execute(new StrategyLogic1());
    contextV2.execute(new StrategyLogic2());
  }

  /**
   * 전략 패턴 - 익명 내부 클래스
   */
  @Test
  void strategyV2() {
    ContextV2 contextV2 = new ContextV2();
    contextV2.execute(new Strategy() {
      @Override
      public void call() {
        log.info("execute business logic1");
      }
    });
    contextV2.execute(new Strategy() {
      @Override
      public void call() {
        log.info("execute business logic2");
      }
    });
  }

  /**
   * 전략 패턴 - 익명 내부 클래스2 lambda
   */
  @Test
  void strategyV3() {
    ContextV2 contextV2 = new ContextV2();
    contextV2.execute(() -> log.info("execute business logic1"));
    contextV2.execute(() -> log.info("execute business logic2"));
  }
}
