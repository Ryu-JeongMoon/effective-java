package chap05.item33;

/**
 * 타입 안전 이종 컨테이너를 고려하라
 */
public class Item33 {

  public static void main(String[] args) {
    Favorites favorites = new Favorites();

    favorites.putFavorite(String.class, "Java");
    favorites.putFavorite(Integer.class, 0xcafebabe);
    favorites.putFavorite(Class.class, Favorites.class);

    String stringInFavorite = favorites.getFavorite(String.class);
    Integer integerInFavorite = favorites.getFavorite(Integer.class);
    Class classInFavorite = favorites.getFavorite(Class.class);

    System.out.printf("%s %x %s%n", stringInFavorite, integerInFavorite, classInFavorite);
  }
}

/*
일반적으로 사용하는 Map<String, String>, Set<Integer> 등은 컨테이너 자체가 매개변수화된다
그에 따라 하나의 컨테이너에서 매개변수화할 수 있는 타입의 수가 제한된다
Map <key, value> -> 2개
Set, List -> 1개

Type Safe Heterogeneous Container ?!
컨테이너 대신 키를 매개변수화하여 제공하면 타입 안전 이종 컨테이너 완성~!
일반적으로 사용하는 컨테이너와 다르게 컨테이너에 포함된 원소들이 다양한 타입을 가질 수 있다

class 리터럴의 타입은 Class 가 아닌 Class<T> 다.
String.class 의 타입이 Class<String> ?

%n -> platform 에 맞게 줄바꿈 문자로 치환
\n -> 대부분의 OS 에서 줄바꿈을 의미하지만 아닌 경우도 있음
 */