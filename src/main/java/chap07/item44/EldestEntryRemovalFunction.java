package chap07.item44;

import java.util.Map;

@FunctionalInterface
public interface EldestEntryRemovalFunction<K, V> {

  boolean remove(Map<K, V> map, Map.Entry<K, V> eldest);
}

/*
필요한 용도에 맞는게 있다면 표준 함수형 인터페이스를 사용하자
API 이해하기도 쉽고 있는 걸 굳이 만들 필요 없잖아 ?!

@FunctionalInterface 는 @Override 를 사용하는 이유와 같은 이유로 사용하도록 한다
보는 이로 하여금 용도를 파악하게 해주고 하나 이상의 메서드가 오지 못 하게 하는 용도이다
 */