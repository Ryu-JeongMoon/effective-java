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

  // alien method 동기화 블록 바깥에서 호출한다, 이를 open call, 열린 호출이라 한다
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

/*
copyOnWriteArrayList 를 왜 쓰는가?
내부를 변경하는 작업에 항상 복사본을 만들도록 구현되어있기 때문이다
순회하는 일은 많고 변경하는 일은 적은 관찰자 리스트를 만들 때 찰떡이다
다른 List 구현체 사용 시 add, remove 같은 메서드에 synchronized 발라줘야 하는데
copyOnWriteArrayList 에서는 필요 없어 동기화 영역 제거 오께이

열린 호출이 왜 필요한가?
동기화 영역 안에서 수행한다면 락이 걸려있어 다른 스레드의 접근이 불가능하다
실패 방지 효과 뿐만 아니라 동시성 효율을 올리기 위해 꼭 필요하다
열린 호출을 신경쓰지 않는다면 동시성 코드를 작성하고서 오히려 느려질 수 있다

가변 클래스를 작성한다면 두 선택지 중 하나를 따라야 한다
1. 동기화 놉, 클라이언트야 알아서 해라
2. 동기화 오께이, 내부에서 처리해줄게

두 선택지에 해당하는 예가 StringBuffer & StringBuilder
StringBuffer 는 거의 모든 상황에 단일 스레드에서 사용되었지만 내부적으로 동기화를 수행했기 때문에 성능 저하가 있었고
이를 해결하기 위해 StringBuilder 를 만들었고 클라이언트에게 동기화 책임을 전가했다
따라서 별 생각 없이 작성할 때는 StringBuffer 를 그대로 쓰되
성능도 중요하고 동기화도 중요할 때는 StringBuilder 를 이용하고 동기화 신경 써줘야한다

와따매 동기화 어렵네잉 ㅋ
 */