package chap08.item54;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public record Cheese(List<Cheese> cheeseInStock) {

    private static final Cheese[] EMPTY_CHEESE_ARRAY = new Cheese[0];

    public List<Cheese> getCheeses() {
        return cheeseInStock.isEmpty() ? null : new ArrayList<>(cheeseInStock);
    }

    // ArrayList 새로 생성해서 반환
    // 가능성이 크진 않지만 성능 저하 일어날 수 있다
    public List<Cheese> getEmptyCheeses() {
        return new ArrayList<>(cheeseInStock);
    }

    // 빈 리스트를 불변 객체로 반환
    // 이는 최적화에 해당하고, 최적화는 필요한 경우에만 수행한다
    // 최적화한 후에는 반드시 성능 측정을 하여 성능 향상이 이루어진 경우에만 사용하도록 한다
    public List<Cheese> getFixedEmptyCheeses() {
        return cheeseInStock.isEmpty() ? Collections.emptyList() : new ArrayList<>(cheeseInStock);
    }

    // 배열 반환할 때도 마찬가지로 null 반환하지 말고 길이가 0인 배열 반환
    public Cheese[] getEmptyArrayCheeses() {
        return cheeseInStock.toArray(new Cheese[0]);
    }

    // 성능이 걱정될 때는 미리 길이 0인 배열 만들어놓고 반환
    // 근디 요 방식이 성능이 떨어진다는 연구 결과도 있다네?! 뭐여 왜 떨어져
    public Cheese[] getFixedEmptyArrayCheeses() {
        return cheeseInStock.toArray(EMPTY_CHEESE_ARRAY);
    }
}
