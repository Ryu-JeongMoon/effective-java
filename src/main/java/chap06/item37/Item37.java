package chap06.item37;

import chap06.item37.Plant.LifeCycle;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;

/**
 * ordinal 인덱싱 대신 EnumMap 을 사용하라
 */
public class Item37 {

    public static void main(String[] args) {
        EnumMap<LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(LifeCycle.class);

        for (LifeCycle lc : LifeCycle.values()) {
            plantsByLifeCycle.put(lc, new HashSet<>());
        }

    }
}

/*
Enum, ordinal 부분 복습해야겠다..
 */