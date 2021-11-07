package chap02;

import lombok.Builder;
import lombok.Data;

/**
 * 생성자에 매개변수가 많다면 빌더를 고려하라
 */
public class Item02 {

    public static void main(String[] args) {
        NutritionFacts nutritionFacts = NutritionFacts.builder()
            .calories(500).build();

        System.out.println(nutritionFacts);
    }

    @Data
    @Builder
    static class NutritionFacts {

        private final int servingSize;
        private final int servings;
        private final int calories;
        private final int fat;
        private final int sodium;
        private final int carbohydrate;
    }
}

/*
모든 필드가 필수가 아닐 때, 생성자를 이용하면 혼란스러워짐 요럴 때 Builder 써라!
점층적 생성자 패턴도 사용 가능하지만 일일이 다 생성하기 넘 힘드렁 변경에 유연하게 대응하기 위해서는 Builder 써야 함
lombok 에서 제공해주니까 편하게 쓰장

Java Beans Pattern 이라는 것도 존재하는데 기본 생성자를 하나 두고 setter 로 필드 값을 지정해준다
하나의 instance 만드려면 setter 난무 해야하고 필수 값을 빠뜨리고 생성할 수도 있기 때문에 일관성이 보장되지 않는다
setter 를 열어뒀기 때문에 불변 객체로 만들 수 없다는 단점도 있다
 */