package dev.riss.spring_advanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 전략을 파라미터로 전달 받는 방식
 */
@Slf4j
public class ContextV2 {

  public void execute(Strategy strategy) {
    long startTime = System.currentTimeMillis();
    // execute business logic
    strategy.call();    // 위임
    // end business logic
    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("cost time 1: {} ms", resultTime);
  }
}
