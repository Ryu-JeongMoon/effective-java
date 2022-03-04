package chap12.item89;

/**
 * 인스턴스 수를 통제해야 한다면 readResolve 보다는 열거 타입을 사용하라
 */
public class Item89 {

  public static void main(String[] args) {
    Elvis instance = Elvis.INSTANCE;
    Elvis elvis = (Elvis) instance.readResolve();

    System.out.println("(instance == elvis) => " + (instance == elvis));
  }
}

/*
싱글턴으로 만들기 위해 생성자를 막아두고 미리 만들어둔 인스턴스를 반환할 수 있는데
implements Serializable 때리는 순간 싱글턴이 아니게 된다
명시적인 readObject 를 제공하더라도 마찬가지다
readResolve 기능을 이용하면 readObject 가 만들어낸 인스턴스를 대체할 수 있게 된다
따라서 readResolve 로 새로 생성된 것을 버리고 기존 인스턴스로 대체해 반환해야 하는 것
readObject 때문에 필요도 없는 인스턴스가 만들어지고 얘는 참조하는 대상이 없으므로 바로 GC 대상이 된다

readResolve 를 인스턴스 통제 목적으로 사용한다면 reference type instance field 는 모두 transient 로 선언해야 한다
그렇지 않으면 readResolve 수행하기 전 transient 가 아닌 reference type field 는 이미 역직렬화되고 얘에 대한 참조를 훔쳐올 수 있다?!

readResolve 를 통해 방어 코드를 짜는 것 보다 애초에 싱글턴을 enum 으로 구현해버리는 게 쉽고
자바가 싱글턴임을 보장하므로 싱글턴을 구현할 때 첫번째 옵션으로 enum 을 고려하자
 */