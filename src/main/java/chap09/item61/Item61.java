package chap09.item61;

/**
 * 박싱된 기본 타입보다는 기본 타입을 사용하라
 */
public class Item61 {

  public static void main(String[] args) {
    Integer five = new Integer(5);
    Integer secondFive = new Integer(5);

    System.out.println("(five == secondFive) = " + (five == secondFive));
    System.out.println("(five equals secondFive) = " + (five.equals(secondFive)));
  }
}

/*
참조 타입은 식별성을 갖는다 (hashCode)
값이 같더라도 서로 다르다고 식별될 수 있다

대소 비교 (>, <) 시에는 기본 값으로 변환되어 비교하고
동일성 비교 (=) 시에는 hashCode 로 비교해버린다

박싱된 타입이 이렇게 쓰레기 같은데 언제 쓰는가
Collection 에 담을 때 기본 타입을 넣을 수 없기 때문에 박싱된 타입 써야한다
일반화하여 말하면 parameterized type, method 사용 시 박싱된 타입 쓰도록 한다
 */