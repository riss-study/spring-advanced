package dev.riss.spring_advanced.trace.threadlocal;

import dev.riss.spring_advanced.trace.threadlocal.code.FieldService;
import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

  private FieldService fieldService = new FieldService();

  @Test
  void field() {
    log.info("main start");

//    Runnable user = new Runnable() {
//      @Override
//      public void run() {
//        fieldService.logic("user");
//      }
//    };
    // 위의 코드를 아래의 람다 식으로 줄일 수 있음
    Runnable userA = () -> {
      fieldService.logic("userA");
    };

    Runnable userB = () -> {
      fieldService.logic("userB");
    };

    Thread threadA = new Thread(userA);
    threadA.setName("thread-A");
    Thread threadB = new Thread(userB);
    threadB.setName("thread-B");

    threadA.start();
    sleep(100);
    threadB.start();
    // 여기까지 해놓고 바로 실행하면 의도하는 대로 두번째 저장 로그 안나옴
    // threadB는 도는 데, main thread가 threadB 시작 시켜놓고 자기는 끝내 버림. 그래서 B의 조회 로그는 안 보이는 것임
    // 원래는 CountDownLatch 쓰면 됨
    sleep(3000);
    log.info("main exit");
  }

  @Test
  void fieldCountDownLatch() {
    log.info("main start");

    CountDownLatch latch = new CountDownLatch(2);
    Runnable userA = () -> {
      try {
        fieldService.logic("userA");
      } finally {
        latch.countDown();
      }
    };
    Runnable userB = () -> {
      try {
        fieldService.logic("userB");
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
