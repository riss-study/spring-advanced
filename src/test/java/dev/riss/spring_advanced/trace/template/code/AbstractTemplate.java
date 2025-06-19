package dev.riss.spring_advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {

  public void execute () {
    long startTime = System.currentTimeMillis();
    // execute business logic
    call();
    // end business logic
    long endTime = System.currentTimeMillis();
    long resultTime = endTime - startTime;
    log.info("cost time: {} ms", resultTime);
  }

  protected abstract void call ();
}
