package dev.riss.spring_advanced.trace.logtrace;

import dev.riss.spring_advanced.trace.TraceStatus;

public interface LogTrace {

  TraceStatus begin (String message);
  void end (TraceStatus status);
  void exception (TraceStatus status, Exception e);
}
