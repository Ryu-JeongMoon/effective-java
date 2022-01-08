package chap11.item79;

import chap04.item18.ForwardingSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

public class ObservableSet<E> extends ForwardingSet<E> {

  private final List<SetObserver<E>> observers = new ArrayList<>();
  private final List<SetObserver<E>> copyOnObservers = new CopyOnWriteArrayList<>();

  public ObservableSet(Set<E> set) {
    super(set);
  }

  public void addObserver(SetObserver<E> observer) {
    synchronized (observers) {
      observers.add(observer);
    }
  }

  public void addObserverInCopyOnObservers(SetObserver<E> observer) {
    copyOnObservers.add(observer);
  }

  public boolean removeObserver(SetObserver<E> observer) {
    synchronized (observers) {
      return observers.remove(observer);
    }
  }

  public boolean removeObserverInCopyOnObservers(SetObserver<E> observer) {
    return copyOnObservers.remove(observer);
  }

  // alien method 동기화 블록 바깥에서 호출한다
  private void notifyElementAdded(E element) {
    List<SetObserver<E>> snapshot;
    synchronized (observers) {
      snapshot = new ArrayList<>(observers);
    }

    for (SetObserver<E> observer : snapshot) {
      observer.added(this, element);
    }
  }

  private void notifyElementAddedInCopyOnObservers(E element) {
    for (SetObserver<E> observer : copyOnObservers) {
      observer.added(this, element);
    }
  }

  @Override
  public boolean add(E element) {
    boolean added = super.add(element);
    if (added) {
      notifyElementAdded(element);
    }
    return added;
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    boolean result = false;
    for (E element : c) {
      // result = result || add(element); 동일한 코드
      result |= add(element);
    }
    return result;
  }
}
