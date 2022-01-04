package chap10.item71;

@FunctionalInterface
public interface FunctionWithException<T, R, E extends Exception> {

  R apply(T t) throws E;
}
