package chap02.item01;

import static chap02.item01.Item01.Card.JACK;
import static chap02.item01.Item01.Card.KING;
import static chap02.item01.Item01.Card.QUEEN;

import java.math.BigInteger;
import java.sql.Date;
import java.time.Instant;
import java.util.EnumSet;

/**
 * 생성자 대신 정적 팩터리 메서드를 고려하라
 */
public class Item01 {

    public static void main(String[] args) {
        Instant instant = Instant.now();
        Date.from(instant);

        EnumSet.of(JACK, QUEEN, KING);

        BigInteger.valueOf(Integer.MAX_VALUE);

        StackWalker.getInstance();

        // getType -> getInstance 와 같은데 Files 생성이 아니라 FileStore 생성인 것처럼 다른 클래스 생성할 때 사용
        // Files.getFileStore(Path.of("어쩌구 저쩌구"));

        // newType -> newInstance 와 같음, 다른 클래스 생성할 때 사용
        // BufferedReader br = Files.newBufferedReader(path);

        // type -> getType, newType 간략 버전
        // List<Complaint> litany = Collections.list(legacyLitany);
    }

    enum Card {
        JACK, QUEEN, KING
    }
}

/*
장점
1. instance 생성 메서드의 이름을 가질 수 있다
getInstance() 등과 같이 일반적인 네이밍도 가능하고 구체적으로도 가능
2. 호출될 때마다 인스턴스를 생성할 필요가 없다
singleton ??
3. 반환 타입의 하위 타입 객체를 반환할 수 있다
List.of() 같은 메서드를 이용할 때 ArrayList 로 반환해버린다는 뜻?!
4. 입력 매개변수에 따라 다른 클래스의 객체를 반환할 수 있다
EnumSet 의 경우 매개변수가 가진 요소의 개수에 따라 64개를 기준으로 이하일 때 RegularEnumSet, 초과일 때 JumboEnumSet 반환
5. 정적 팩터리 메서드를 작성하는 시점에는 반환될 객체의 클래스가 존재하지 않아도 된다
오잉 이게 뭔 소리임

단점
1. 상속을 위해서는 public or protected 생성자 필요, 정적 팩터리 메서드만 제공하기 위해서는 상속 불가
이 제약은 상속보다 합성을 사용하도록 유도하기 때문에 오히려 좋아
2. 정적 팩터리 메서드는 프로그래머가 찾기 어렵다??
생성자는 한 눈에 바로 들어와서 파악이 쉬운데, 팩터리 메서드는 안 그러니까? API 문서를 잘 작성하고, 관례에 따라 네이밍하도록 한다

각각의 쓰임새가 있으니 필요에 따라 선택하도록 하거라
 */