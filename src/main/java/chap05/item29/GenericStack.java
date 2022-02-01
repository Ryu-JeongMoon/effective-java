package chap05.item29;

import java.util.Arrays;
import java.util.Collection;
import java.util.EmptyStackException;

public class GenericStack<E> {

  private static final int DEFAULT_INITIAL_CAPACITY = 16;
  private E[] elements;
  private int size = 0;

  // 첫번째 해결 방법, Object 배열로 만든 후 타입을 확실히 아니까 @SuppressWarnings 써준다
  // 이놈으로 쓰면 코드가 짧아 가독성이 좋은데 단점으로는 컴파일 타입과 런타임 타입이 다르기 때문에 Heap Pollution 이 일어난다
  @SuppressWarnings("unchecked")
  public GenericStack() {
    // 요기서 에러 발생
    elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
  }

  public static void main(String[] args) {
    GenericStack<String> stack = new GenericStack<>();
    for (String arg : args) {
      stack.push(arg);
    }

    while (!stack.isEmpty()) {
      System.out.println("stack.pop().toUpperCase() = " + stack.pop().toUpperCase());
    }
  }

  public void push(E e) {
    ensureCapacity();
    elements[size++] = e;
  }

  // 한정적 와일드카드 적용, E 를 상속한 모든 타입이 들어올 수 있다
  // Parameterized Type 으로만 해두면 유연하지 못 하기 때문에 한정적 와일드카드 사용해야 한다
  public void pushAll(Iterable<? extends E> src) {
    for (E e : src) {
      push(e);
    }
  }

  private void ensureCapacity() {
    if (elements.length == size) {
      elements = Arrays.copyOf(elements, 2 * size + 1);
    }
  }

  // 뺀놈 메모리 해제해주기
  public E pop() {
    if (size == 0) {
      throw new EmptyStackException();
    }

    E result = elements[--size];
    elements[size] = null;
    return result;
  }

  public void popAll(Collection<? super E> dst) {
    while (!isEmpty()) {
      dst.add(pop());
    }
  }

  public boolean isEmpty() {
    return size == 0;
  }
}

/*
E 와 같이 실체화가 불가한 녀석으로는 배열을 만들 수 없당
컴파일 타임에만 체크되고 런타임에는 소거되기 때문이지 움허허

compile & run
> javac GenericStack.java
> java GenericStack.java hello yaho

28과는 상반되지만 자바에서 기본적으로 List 를 제공하지 않는데에는 다~~ 이유가 있는 법
배열을 쓰는게 성능 목적으로 낫고, List, Map 등의 기본적인 구현을 할 때는 Object[] 을 이용한다
일반 개발자에게는 컴파일 시점에 type safe 하게 만들 수 있기 때문에 Generics 추천되는 것이고
자바 도사님들은 타입을 알아서 지지고 볶아 먹기 때문에 Object[] 써도 되는 것

배열은 실체화되지만 E[] 로 캐스팅하는 경우 E[] 는 실체화되지 않으므로
런타임에 타입 안전성을 보장해줄 수 없어서 컴파일러가 경고 띄운다
단 모든 메서드에서 E 로 다루도록 작성했으므로 런타임 시 문제가 날 확률이 없으니
@SuppressWarnings 로 숨겨주어 다른 에러를 가리지 못 하도록 만들어준다

가능한 클라이언트가 형변환을 신경쓰지 않게 작성하자
복잡하고 불편한 연산을 클라이언트에게 맡기지 말고 내부적으로 해결한다

elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
위와 같이 선언해봤자 Generics 는 런타임 시 소거되기 때문에 elements 는 new Object[] 일 뿐이다
컴파일 타임에는 E[] 이고 런타임에는 Object[] 이기 때문에 타입이 다르다는 것
런타임 시 클래스 로딩 과정에 클래스와 인스턴스가 JVM Heap 에 저장되는데 요 두 타입이 달라 Heap Pollution 일어나는 것

compiler 는 타입 캐스팅을 검사하지 않는다, 참조하는 값을 변수에 할당할 수 있는지만 검사한다
모든 값들을 Object 에 담고 런타임 시 연산 수행하면 그때서야 예외 터진다
 */