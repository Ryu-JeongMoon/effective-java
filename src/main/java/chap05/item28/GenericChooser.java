package chap05.item28;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GenericChooser<T> {

    private final List<T> choiceArray;

    public GenericChooser(Collection<T> choiceArray) {
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
 */