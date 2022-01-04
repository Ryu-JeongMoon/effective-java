package chap10.item71;

@FunctionalInterface
public interface ConsumerWithException<T, E extends Exception> {

  void accept(T t) throws E;
}
