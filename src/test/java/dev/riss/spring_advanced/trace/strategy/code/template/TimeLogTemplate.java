package dev.riss.spring_advanced.trace.strategy.code.template;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {

  public void execute(Callback callback) {
    long startTime = System.currentTimeMillis();
    // execute business logic
    callback.call();    // 위임
    // end business logic
    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("cost time 1: {} ms", resultTime);
  }
}
