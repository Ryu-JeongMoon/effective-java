package chap02.item01;

import static chap02.item01.Item01.Card.JACK;
import static chap02.item01.Item01.Card.KING;
import static chap02.item01.Item01.Card.QUEEN;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

/**
 * 생성자 대신 정적 팩터리 메서드를 고려하라
 */
public class Item01 {

  public static void main(String[] args) throws IOException, SQLException {
    Instant instant = Instant.now();
    Date date = Date.from(instant);
    System.out.println("date = " + date);

    EnumSet<Card> jack = EnumSet.of(JACK, QUEEN, KING);
    System.out.println("jack = " + jack);

    BigInteger bigInteger = BigInteger.valueOf(Integer.MAX_VALUE);
    System.out.println("bigInteger = " + bigInteger);

    List<Object> of = List.of("YAHO");
    System.out.println(of.get(0));
    System.out.println("of.getClass() = " + of.getClass().getName());

    StackWalker instance = StackWalker.getInstance();
    System.out.println("instance = " + instance);

    // getType -> getInstance 와 같은데 Files 생성이 아니라 FileStore 생성인 것처럼 다른 클래스 생성할 때 사용
    FileStore blahBlah = Files.getFileStore(Paths.get("어쩌구저쩌구"));
    long blockSize = blahBlah.getBlockSize();
    System.out.println("blockSize = " + blockSize);

    // newType -> newInstance 와 같음, 다른 클래스 생성할 때 사용
    BufferedReader br = Files.newBufferedReader(Paths.get("어쩌구저쩌구"));
    System.out.println("br.readLine() = " + br.readLine());

    // type -> getType, newType 간략 버전
    // List<Complaint> litany = Collections.list(legacyLitany);
  }

  enum Card {
    JACK, QUEEN, KING
  }
}

/*
Java8 전에는 인터페이스에 static method 있을 수 없었다
Collection (Interface) -> Collections (Util Class) 처럼 인터페이스에 s 를 붙여 유틸 클래스를 만드는 것이 관례였다
유틸 클래스에 static factory method 를 두어 여기서 Collections.emptyList() 같은 메서드 만들어두었다
Java8 이후로는 인터페이스를 이용해 반환하면 되므로 의미가 많이 사라졌으나,
Java9부터 인터페이스에 private static field / member class 를 둘 수 있어서 자바8을 사용하는 경우 유틸 클래스 사용할 필요가 있다

기본적으로 클라이언트는 클래스의 인스턴스를 얻기 위해 new ClassName() 방식을 사용한다
생성자와는 별도로 static factory method 를 이용하는 방법도 있다
그에 따른 장/단점은 아래와 같다

장점
1. instance 생성 메서드의 이름을 가질 수 있다
일반 생성자의 경우 객체의 특성을 제대로 설명하지 못 한다
또한 같은 시그니처로는 생성자를 하나만 만들 수 있다, 이를 생성자 오버로딩을 통해 극복할 수도 있지만
생성자가 여러개 존재할 때, 어떤 것이 필요할지 어떻게 알 수 있을까?
API 작성자는 의도를 드러내어 만들었다고 생각하겠지만 사용하는 입장에서는 주석을 참고하거나 사용되는 Context 알고 클래스를 까봐야 알 수 있다
정적 팩토리 메서드는 getInstance() 등과 같이 일반적인 네이밍도 가능하고 구체적으로도 가능
효과적인 네이밍을 통해 클래스를 까보지 않아도 어떤 상황에 쓰일 수 있는지 드러낼 수 있다

2. 호출될 때마다 인스턴스를 생성할 필요가 없다
singleton

3. 반환 타입의 하위 타입 객체를 반환할 수 있다
List.of() 로 생성 시 java.util.ImmutableCollections$ListN 형태의 리스트 생성됨, 요놈은 읽기 전용

4. 입력 매개변수에 따라 다른 클래스의 객체를 반환할 수 있다
EnumSet 의 경우 매개변수가 가진 요소의 개수에 따라 64개를 기준으로 이하일 때 RegularEnumSet, 초과일 때 JumboEnumSet 반환
다음 릴리즈때 구현체를 바꾸더라도 클라이언트는 상관이 없다?!
이전 버전에 맞게 특화된 코드들이 있을 수 있으므로 상관이 없는 건 아닐듯, 단지 구현체를 바꾸더라도 터지지 않고 잘 돌아간다 정도로 해석

5. 정적 팩터리 메서드를 작성하는 시점에는 반환될 객체의 클래스가 존재하지 않아도 된다
독립적인 개발을 진행할 때 Interface 만들어두고 해당 인터페이스 구현하는 임시 객체를 사용하는 것처럼 활용 가능
지금 당장 없어도 애플리케이션 통합할 때 실제 클래스 존재하면 됨

단점
1. 상속을 위해서는 public or protected 생성자 필요, 정적 팩터리 메서드만 제공할 때는 상속 불가
이 제약은 상속보다 합성을 사용하도록 유도하기 때문에 오히려 좋아

2. 정적 팩터리 메서드는 프로그래머가 찾기 어렵다??
생성자는 한 눈에 바로 들어와서 아 여기서 생성하는고나 하고 파악이 쉬운데,
팩터리 메서드 자체가 어디서 사용되는지도 찾아야되고 어떤 인스턴스 반환하는지 클래스 또 까봐야 안다
API 문서를 잘 작성하고, 관례에 따라 네이밍하도록 한다

각각의 쓰임새가 있으니 필요에 따라 선택하도록 하거라
 */