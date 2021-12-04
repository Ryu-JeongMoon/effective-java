package chap05.item28;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GenericChooser<T> {

    private final List<? super T> choiceArray;

    // 생산자 매개변수에 와일드카드 타입 적용
    public GenericChooser(Collection<? extends T> choiceArray) {
        this.choiceArray = new ArrayList<>(choiceArray);
    }

    public Object choose() {
        Random rnd = ThreadLocalRandom.current();
        return choiceArray.get(rnd.nextInt(choiceArray.size()));
    }
}

/*
배열은 런타임에는 type safe, 컴파일 타임에는 no no
Generics 는 그 반대

생성자 매개변수에 와일드카드 타입 적용 왜 하는데??
Chooser<Number> 생성자에 List<Integer> 를 넘기고 싶을 때
와일드카드 타입 적용 안 하면 Parameterized Type 이니까 에러 터지겠쥬?
와일드카드 적용하면 Integer extends Number 니까 되겠쥬?

생성자는 producer 니까 super 아니고 extends
 */