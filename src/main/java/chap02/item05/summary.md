# 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라

~~~
private static final Lexicon dictionary = new LexiconDictionary();
~~~

위와 같이 코드에서 직접 new 연산자를 사용해 할당하면 유연하지 못한 구현이 되고, 테스트 하기도 어렵다<br>
하나의 인스턴스만 사용하는게 고정된 상황이더라도 변경 가능성까지 고려하여 외부에서 의존성을 주입하는 것이 좋다<br>
<hr>

~~~
Mosaic create(Supplier<? extends Tile> tileFactory) {...}
~~~

이것의 변형으로 정적 팩터리 메서드 자체를 넘기는 방법이 존재한다<br>
Tile 을 상속한 하위 클래스들을 제네릭으로 받는 Supplier 인 tileFactory 를 인수로 넘긴다
<hr>

습관적으로 의존성 주입 받을 것을 final 필드로 만들고 @RequiredArgsConstructor 를 사용해 의존성을 주입해줬다<br>
인스턴스의 동적 할당을 위한 구조를 편하게 사용하고 있다는 걸 잊지 말자<br>
핵심은 final 을 선언해 인스턴스 생성 시 재할당되지 않도록 만들어주는 것이고<br>
생성자를 통해 클라이언트 측에서 인스턴스를 지정해 주입해주는 것이다