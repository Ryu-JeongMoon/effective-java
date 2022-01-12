package chap04.item18;

import java.util.Set;

public class CompositedInstrumentedHashSet<E> extends ForwardingSet<E> {

  public CompositedInstrumentedHashSet(Set<E> set) {
    super(set);
  }
}
