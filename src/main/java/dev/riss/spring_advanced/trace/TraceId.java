package dev.riss.spring_advanced.trace;

import java.util.UUID;
import lombok.Getter;

@Getter
public class TraceId {

  private String id;
  private int level;

  public TraceId() {
    this.id = createId();
    this.level = 0;
  }

  private TraceId(String id, int level) {
    this.id = id;
    this.level = level;
  }

  private String createId() {
    return UUID.randomUUID().toString().substring(0, 8);    // 앞 8자리만 사용
  }

  public TraceId createNextId() {
    return new TraceId(this.id, level + 1);
  }

  public TraceId createPreviousId() {
    return new TraceId(this.id, level - 1);
  }

  public boolean isFirstLevel() {
    return level == 0;
  }
}
