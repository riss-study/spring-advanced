package dev.riss.spring_advanced.config;

import dev.riss.spring_advanced.trace.logtrace.FieldLogTrace;
import dev.riss.spring_advanced.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

  @Bean
  public LogTrace logTrace() {
    return new FieldLogTrace();
  }
}
