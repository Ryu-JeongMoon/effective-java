package chap09.item62;

// Java 가 제공하는 ThreadLocal 과 흡사한 모양
// 문자열 기반 API 문제를 해결하며 우아하당
public final class ThreadLocal<T> {

    public ThreadLocal() {
    }

    public void set(T value) {
    }

    public Object get() {
        return null;
    }
}

abstract class StringThreadLocal {

    private StringThreadLocal() {
    }

    // 스레드의 값을 키로 구분해 저장한다
    abstract void set(String key, Object value);

    // 스레드의 값을 반환한다
    abstract Object get(String key);
}

abstract class Key {

    private Key() {
    }

    // 문자열 기반의 API 의 문제점을 해결해준다
    // 전역으로 공유될 필요가 없는 녀석이 공유되기 때문에 1. 중복될 수 있다는 점, 2. 보안이 취약하다는 점
    abstract Key getKey();

    abstract void set(Key key, Object value);

    abstract Object get(Key key);
}