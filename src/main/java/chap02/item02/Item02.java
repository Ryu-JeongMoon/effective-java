package chap02.item02;

import lombok.Builder;
import lombok.Data;

/**
 * 생성자에 매개변수가 많다면 빌더를 고려하라
 */
public class Item02 {

  public static void main(String[] args) {
    NutritionFacts nutritionFacts = NutritionFacts.builder()
      .calories(500)
      .build();

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
인스턴스 생성할 때 받아야 하는 매개변수가 무지막지 하다면 어떻게 해야할까?
1. telescoping constructor pattern
2. java beans pattern
3. builder pattern

점층적 생성자 패턴은 받아야 하는 생성자 오버로딩을 통해 매개변수 수를 늘려가는 것
매개변수 개수에 따라 계속 늘어나야 하므로 유지보수까지 생각한다면 이건 지옥

Java Beans Pattern 은 기본 생성자를 하나 두고 setter 로 필드 값을 지정해준다
하나의 instance 만드려면 setter 난무 해야하고 필수 값을 빠뜨리고 생성할 수도 있기 때문에 일관성이 보장되지 않는다
setter 를 열어뒀기 때문에 불변 객체로 만들 수 없다는 단점도 있다

모든 필드가 필수가 아닐 때, 생성자를 이용하면 혼란스러워짐 요럴 때 Builder 써라!
점층적 생성자 패턴도 사용 가능하지만 일일이 다 생성하기 넘 힘드렁 변경에 유연하게 대응하기 위해서는 Builder 써야 함
lombok 에서 제공해주니까 편하게 쓰장, 롬복이 대략 요런 놈을 만들어 주는 것

public static class Builder {
    private final int servingSize;
    private final int servings;
    private int calories;
    private int fat;
    private int sodium;
    private int carbohydrate;

    public Builder(int servingSize, int servings) {
      this.servingSize = servingSize;
      this.servings = servings;
    }

    final 아닌 것들의 만들어지는 형태
    public Builder calories(int calories) {
      this.calories = calories;
      return this;
    }
}

불변과 불변식
immutability 는 어떠한 변경도 허용하지 않는다
invariant 는 프로그램 실행 동안 변경은 허용되나 특정 조건 하에서만 가능하다
ex) List 의 size 는 0 미만일 수 없다, Period 의 start 는 end 보다 항상 앞서야 한다
 */