package dev.riss.spring_advanced.trace.threadlocal;

import dev.riss.spring_advanced.trace.threadlocal.code.ThreadLocalService;
import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {

  private final ThreadLocalService threadLocalService = new ThreadLocalService();

    @Test
  void fieldCountDownLatch() {
    log.info("main start");

    CountDownLatch latch = new CountDownLatch(2);
    Runnable userA = () -> {
      try {
        threadLocalService.logic("userA");
      } finally {
        latch.countDown();
      }
    };
    Runnable userB = () -> {
      try {
        threadLocalService.logic("userB");
      } finally {
        latch.countDown();
      }
    };

    Thread threadA = new Thread(userA);
    threadA.setName("thread-A");
    Thread threadB = new Thread(userB);
    threadB.setName("thread-B");

    threadA.start();
    sleep(100);
    threadB.start();

    try {
      latch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    log.info("main exit");
  }

  private void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
