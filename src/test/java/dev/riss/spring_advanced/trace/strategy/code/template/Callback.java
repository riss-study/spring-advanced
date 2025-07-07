package dev.riss.spring_advanced.trace.strategy.code.template;

/**
 * 템플릿 콜백 패턴
 *  -> GOF 디자인 패턴은 아니지만, 스프링에서 자주 나오는 패턴 (like JdbcTemplate, RedisTemplate, RestTemplate...)
 */
public interface Callback {
  void call();
}
