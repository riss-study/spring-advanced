package dev.riss.spring_advanced.trace.callback;

public interface TraceCallback<T> {
  T call();
}
