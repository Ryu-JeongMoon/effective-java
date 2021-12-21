package chap07.item45;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * 스트림은 주의해서 사용하라
 */
public class Item45 {

    public static void main(String[] args) throws FileNotFoundException {
        File dictionary = new File("src/main/java/chap07/item45/panda");
        int minGroupSize = 1;

        Map<String, Set<String>> groups = new HashMap<>();
        try (Scanner s = new Scanner(dictionary)) {
            while (s.hasNext()) {
                String word = s.next();
                groups.computeIfAbsent(alphabetize(word), (unused) -> new TreeSet<>()).add(word);
            }
        }

        for (Set<String> group : groups.values()) {
            if (group.size() >= minGroupSize) {
                System.out.println(group.size() + " : " + group);
            }
        }
    }

    private static String alphabetize(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}

/*
다량의 데이터 처리를 위해 스트림이 제공된다
스트림은 여러 중간 연산과 하나의 종단 연산으로 구성된다
함수형 프로그래밍의 장점, 지연 평가가 이루어진다

*/