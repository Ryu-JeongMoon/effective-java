package chap10.item71;

import java.util.function.Consumer;
import java.util.function.Function;

public class FunctionalWrapperUtils {

  public static <T, R, E extends Exception> Function<T, R> wrapper(FunctionWithException<T, R, E> fe) {
    return arg -> {
      try {
        return fe.apply(arg);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    };
  }

  public static <T, E extends Exception> Consumer<T> consumeWrapper(ConsumerWithException<T, E> ce) {
    return arg -> {
      try {
        ce.accept(arg);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    };
  }
}

/*
오우쉿 쩐당
 */