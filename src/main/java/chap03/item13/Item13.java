package chap03.item13;

import chap03.item11.PhoneNumber;

/**
 * clone 재정의는 주의해서 진행하라
 */
public class Item13 {

  public static void main(String[] args) {
    PhoneNumber p1 = new PhoneNumber(123, 456, 9999);
    PhoneNumber p2 = p1.clone();

    System.out.println("(p1==p2) = " + (p1 == p2));
    System.out.println("p1 = " + p1);
    System.out.println("p2 = " + p2);
  }
}

/*
Cloneable 은 복제 가능한 클래스임을 명시하는 marker interface 인데 의도한 목적을 이뤘다고는 할 수 없다?
clone 선언된 곳이 Object 이고, protected 접근 제한자를 가지고 있기 때문

일반적으로 인터페이스를 구현한다는 것은 해당 인터페이스가 제공하는 public API 를 이용하겠다는 의미를 암묵적으로 전달하는 것
그러나 Cloneable 은 구현을 통해 Object 가 가진 clone() 메서드의 동작 방식을 결정한다
인터페이스의 이례적인 예로 다른 인터페이스에서는 이런 방식으로 사용해서는 안 된다
관례를 뒤집어 까버린 것이기 때문에 다른 인터페이스에는 적용 노노!

그럼에도 불구하고 Cloneable 은 자주 쓰인다
실무에서는 Cloneable 을 구현한 클래스는 clone 을 public 으로 제공하며 기능이 제대로 동작됨을 보장해야 한다

Cloneable 은 왜 존재하는가?
단순히 인스턴스를 복제하는 경우엔 clone 을 구현할 필요가 없다, 그저 인스턴스 복사 메서드를 생성하고 사용하면 그만이다
클래스 계층을 따라 super.clone() 을 호출하는 경우에 필요한 것이다
단 이때 함정을 주의하자
super class 의 clone() 에서 super class type 으로 인스턴스 생성해서 반환한다면 어떻게 될까?
sub class 에서의 clone() 결과가 super class 로 나타난다
복사했는데 상위 클래스가 띠용?!

작성 시에 Object 반환하지 말고 type casting 을 통해 하위 타입으로 캐스팅해서 반환해주어야 한다
이는 자바가 covariant return typing 을 지원하기 때문에 가능한 것, 자바야 고맙다!
개념적으로 같은 타입을 반환 받는 것 뿐만 아니라 클라이언트가 캐스팅해서 써야하는 불편함을 줄여줄 수 있다
안전한 실행을 위해 예외 처리를 거르지 않는다고 할 때 예외 발생 가능하다면 try-catch 로 묶어서 CloneNotSupportedException 처리할 것
근데 Cloneable 구현하는 걸 아는데 굳이?! 이러면 똥 된다
CloneNotSupportedException 은 RuntimeException 이었어야 한다고 한다, 듣고보니 일리가 있구만

특별히 필요한 경우가 아니라면 clone 이용하는 것보다는
복사하려는 인스턴스의 값들을 parameter 로 받아 생성자나 팩터리 메서드를 이용해 반환해주는 것이 깔끔

여기까지는 단순 객체에 해당하는 내용이었고 clone 대상이 배열이라면?!
골치 아프지만 순회하면서 deep copy 때려주자, 이렇게 안 하면 참조 복사되어 클론한 놈으로 원본 객체를 조질 수가 있다

그렇다면 고수준 API 로 짧고 간결하게, 예를 들어 hashMap.put(key, value) 형식으로 우아한 코드를 짠다면 어떨까?
성능을 조지는 지름길이 된다, 때로는 저수준을 직접 다뤄야하는 경우가 있다
Cloneable 의 핵심 아이디어인 필드 단위 복사도 지키지 않기 때문에 영 좋지 않다

복사 한번 하려다가 어디까지 가는걸까
그냥 쉽게 쉽게 처리해보면 어떤가해서 나온 것이 conversion constructor / factory
static method 로 해당 클래스 또는 해당 클래스가 구현한 인터페이스 타입을 받아
필드 단위 복사 수행한 후 돌려주도록 한다
이렇게 하면 반환 타입을 개발자가 직접 선택할 수도 있고, final 조건도 충족시킬 수 있다
 */