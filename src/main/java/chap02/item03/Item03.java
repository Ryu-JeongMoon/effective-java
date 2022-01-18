package chap02.item03;

/**
 * private 생성자나 열거 타입으로 싱글턴임을 보장하라
 */
public class Item03 {

  public static void main(String[] args) {
    System.out.println(EnumElvis.INSTANCE);
    System.out.println((EnumElvis.INSTANCE == EnumElvis.INSTANCE));

    System.out.println((Elvis.INSTANCE == Elvis.INSTANCE));

    Elvis i1 = Elvis.getInstance();
    Elvis i2 = Elvis.getInstance();

    System.out.println((i1 == i2));
  }

  enum EnumElvis {
    INSTANCE
  }

  static class Elvis {

    public static final Elvis INSTANCE = new Elvis();

    private Elvis() {
      if (INSTANCE != null) {
        throw new IllegalStateException("Cannot construct another instance");
      }
    }

    public static Elvis getInstance() {
      return INSTANCE;
    }
  }
}

/*
클래스를 싱글턴으로 만들면 mock 테스트가 힘들다

싱글턴을 만드는 방식은 둘 중 하나, 두 방식 모두 생성자 private 으로 제한
1. public 필드 방식, Elvis.INSTANCE 호출 시 단 한번만 초기화됨, 싱글턴임이 API 에 명백히 드러난다
2. static getInstance()로 INSTANCE 반환

두 방식 모두 Reflection 을 이용한 접근에 의해 수정될 수 있기 때문에 이를 방어하려면
private 생성자에서 두번째 인스턴스를 만드려고 할 때 예외가 터지게끔 만들어야한다

직렬화 - 역직렬화 과정에서도 단순히 클래스에서 Serializable 을 구현했다고 끝이 아니라
readResolve 메서드를 사용해 진짜 Elvis 를 반환하도록 만들어야한다 ?!

Enum 방식은 아주 효율적이나 Android 개발할 때, Context 라는 의존성이 끼어드는 문제가 있다고 한다
웹 개발에서는 문제 없남?

최대 궁금증은 스프링의 관리를 받아서 @Bean 으로 싱글턴 구현하는 게 제일 간편하지 않은감? 하는 생각이다
Bean 으로 관리하게 된다면 사용하는 측도 일반 class 여서는 안 되고 Bean 주입 받아야 되니께 스프링의 관리를 받아야 한다
어차피 스프링에 의존하기로 한거 빈으로 관리 받아서 써도 되긴 한데
애플리케이션 로드 시점에 빈이 생성 되버리니까 사용하기 전에 쓰잘데기 없이 생성된 것이다

웬만하면 LazyHolder 쓰자
단 이 방법에서 직렬화를 한다고 하면 모든 인스턴스 필드를 transient 선언하고 readResolve 메서드 제공해야 한다
이러한 과정이 번거롭다면 enum 방식으로 만들면 된다
간결하고 직렬화 상황 / 리플렉션 공격에도 다른 인스턴스가 생성되는 것을 막아준다
 */