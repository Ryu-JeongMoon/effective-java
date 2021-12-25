package chap07.item47;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PowerSet {

    public static final <E> Collection<Set<E>> of(Set<E> s) {
        ArrayList<E> src = new ArrayList<>(s);
        if (src.size() > 30) {
            throw new IllegalArgumentException("집합에 원소 투머치");
        }

        return new AbstractList<>() {
            // 옴매 이게 뭐시여
            @Override
            public Set<E> get(int index) {
                Set<E> result = new HashSet<>();
                for (int i = 0; index != 0; i++, index >>= 1) {
                    if ((index & 1) == 1) {
                        result.add(src.get(i));
                    }
                }
                return result;
            }

            @Override
            public int size() {
                // 멱집합의 크기는 2를 원래 집합의 원소 수만큼 거듭제곱 한 것과 같다?!
                return 1 << src.size();
            }
        };
    }
}
