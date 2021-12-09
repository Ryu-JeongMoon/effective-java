package chap05.item33;

import java.util.HashMap;
import java.util.Map;

public class Favorites {

    private final Map<Class<?>, Object> favorites = new HashMap<>();

    // 런타임 타입 안정성을 위해 put 과정에서도 캐스팅하여 넣기
    public <T> void putFavorite(Class<T> type, T instance) {
        favorites.put(type, type.cast(instance));
    }

    // type.cast()
    // 비검사 형변환하는 손실 없이 타입 안전하게 만드는 방법
    public <T> T getFavorite(Class<T> type) {
        return type.cast(favorites.get(type));
    }
}
