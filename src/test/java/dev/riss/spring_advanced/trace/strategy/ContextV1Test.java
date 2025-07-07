package dev.riss.spring_advanced.trace.strategy;

import dev.riss.spring_advanced.trace.strategy.code.strategy.ContextV1;
import dev.riss.spring_advanced.trace.strategy.code.strategy.Strategy;
import dev.riss.spring_advanced.trace.strategy.code.strategy.StrategyLogic1;
import dev.riss.spring_advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 *  선 조립, 후 실행 -> Context 에 Strategy 를 두고(조립) 사용하는 방식
 *  Context 와 Strategy 를 한번 조립하면, 이후에는 Context 를 실행하기만 하면 됨
 *  -> 스프링 애플리케이션 로딩 시점에 의존관계 주입을 통해 필요한 의존관계를 모두 맺어두고(조립) 난 다음에,
 *    실제 요청을 처리(사용)하는 것과 같은 원리
 *
 *    단점: 한번 조립한 이후에는 전략을 변경하기 번거로움
 *      Context 에 Setter 를 제공해서 변경해도 되지만, Context 를 싱글톤으로 사용 시, 동시성 이슈 등 고려점이 많음
 *      Context1, Context2 처럼(아래의 테스트 코드) Context 를 하나 더 생성하여 다른 Strategy 주입하는 것이 나은 선택일 수 있음
 */
@Slf4j
public class ContextV1Test {

  @Test
  void strategyV0 () {
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
   * 전략 패턴 사용
   */
  @Test
  void strategyV1() {
    StrategyLogic1 strategyLogic1 = new StrategyLogic1();
    ContextV1 context1 = new ContextV1(strategyLogic1);
    context1.execute();

    StrategyLogic2 strategyLogic2 = new StrategyLogic2();
    ContextV1 context2 = new ContextV1(strategyLogic1);
    context2.execute();
  }

  /**
   * 전략 패턴 - 익명 내부 클래스 사용 (변수로 담아두기)
   */
  @Test
  void strategyV2() {
    Strategy strategyLogic1 = new Strategy() {
      @Override
      public void call() {
        log.info("execute business logic1");
      }
    };
    ContextV1 context1 = new ContextV1(strategyLogic1);
    log.info("strategyLogic1: {}", strategyLogic1.getClass());
    context1.execute();

    Strategy strategyLogic2 = new Strategy() {

      @Override
      public void call() {
        log.info("execute business logic2");
      }
    };

    ContextV1 context2 = new ContextV1(strategyLogic2);
    log.info("strategyLogic2: {}", strategyLogic2.getClass());
    context2.execute();
  }

  /**
   * 전략 패턴 - 익명 내부 클래스 사용 (생성하면서 바로 전달)
   */
  @Test
  void strategyV3() {

    ContextV1 context1 = new ContextV1(new Strategy() {
      @Override
      public void call() {
        log.info("execute business logic1");
      }
    });
    context1.execute();

    ContextV1 context2 = new ContextV1(new Strategy() {

      @Override
      public void call() {
        log.info("execute business logic2");
      }
    });
    context2.execute();
  }

  /**
   * 전략 패턴 - 익명 내부 클래스 사용 (lambda로 변경(익명 클래스 내부에 메서드 1개만 있을 때 허용))
   */
  @Test
  void strategyV4() {

    ContextV1 context1 = new ContextV1(() -> log.info("execute business logic1"));
    context1.execute();

    // 조립
    // () -> log.info("execute business logic2") 이 부분을 조립
    ContextV1 context2 = new ContextV1(() -> log.info("execute business logic2"));

    // 조립 이후로는 execute 만 실행하면 됨 (사용)
    context2.execute();
  }
}
