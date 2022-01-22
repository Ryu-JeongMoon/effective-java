package chap03.item10;

/**
 * equals 는 일반 규역을 지켜 재정의하라
 */
public class Item10 {

  public static void main(String[] args) {
    String yaho1 = "YAHO";
    String yaho2 = "Yaho";

    System.out.println(yaho1.equals(yaho2));
    System.out.println(yaho1.equalsIgnoreCase(yaho2));
  }
}

/*
아래에 해당하는 경우에는 equals 재정의 하지마라
1. 각각의 인스턴스가 본질적으로 고유할 때
2. 인스턴스의 logical equality 검사할 필요가 없을 때
3. 상위 클래스에서 재정의한 메서드를 굳이 오버라이드할 필요가 없을 때
ex) AbstractSet -> Set, AbstractList -> List, AbstractMap -> Map
4. 클래스가 private / package-private 이고 equals 호출할 일이 없을 때

그럼 언제 쓰나용?
물리적 동치성 (인스턴스 주소가 같은지) 말고 논리적 동치성 (인스턴스의 값이 같은지) 을 비교할 필요가 있는 경우
주로 값 클래스들이 해당 ex) Integer, String

equals 규약은 무엇인고?
모든 조건에서 널이 아닌 경우
1. 반사성 - 자기 자신은 같아야 함 x.equals(x) => true
2. 대칭성 - x.equals(y) => true 인 경우, y.equals(x) => true
3. 추이성 - x.equals(y), y.equals(z) => true 인 경우, x.equals(z) => true
4. 널아님 - if(obj != null) 이거 쓰지말고 적절히 형변환을 거쳐서 비교해야하니 if (!obj instanceof CustomObject) 요걸 쓰도록 하라

구체 클래스를 확장해 equals 비교에 영향을 주는 정보를 추가하면 대칭성과 추이성이 박살난다
그럼 어떻게 해결해야 할까?
Liskov substitution principle 만족하면서 값을 추가한 클래스 equals 해결하는 법은 없다
따라서 상위 클래스를 구체화할 수 없게 만들거나 (abstract, interface)
클래스를 명확히 분리해서 사용하는 수 밖에..
 */