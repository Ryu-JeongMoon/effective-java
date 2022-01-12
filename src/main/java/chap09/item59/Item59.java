package chap09.item59;

import java.util.Random;
import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 라이브러리를 익히고 사용하라
 */
public class Item59 {

  public static void main(String[] args) {
    Random random = new Random();
    random.nextInt(50);

    ThreadLocalRandom current = ThreadLocalRandom.current();
    current.nextInt(50);

    SplittableRandom splittableRandom = new SplittableRandom();
    splittableRandom.nextInt(50);
  }
}

/*
Random -> ThreadLocalRandom 으로 대체하면 성능 향상과 고품질의 난수 생성
SplittableRandom -> Fork-Join pool / Parallel Stream 에서 사용한다면

low-level 의 코드를 알아두면 좋지만 목적이 무엇인지에 따라 효율이 달라진다
평범한 웹 백엔드를 다룰 때 SplittableRandom 의 사용법 외에 사용 원리까지 빠삭하게 알아야 할 필요가 있을까?
core-module 관련 개발을 하는데 동작 방식을 싸그리 무시하고 사용법만 안다고 개발이 될까?

java.lang, java.util, java.io, java.util.concurrent 와 같이 핵심 라이브러리는 알아두면 도움이 된다
java 의 기초가 되는 lang
성능 좋고 사용하기 쉽게 만들어진 util
결국은 알아둬야 할 low-level 의 io
앞으로 더 중요해질 동시성 관련 concurrent

특정 기능이 필요하다고 무작정 구현하기 보다 표준 라이브러리에 그러한 기능이 있는지 먼저 살펴보도록 하자
표준이란 의미에 맞게 극한의 최적화를 이루어 뒀을 것이고 수많은 이들의 사용 경험으로 검증이 되었을 것이다
이래도 없다?! 그럼 만들자 or third-party library 이용하자

동작 원리는 너무나 중요하다
그러나 단계별로 알아야 하는, 알 수 있는 지식들은 차이가 있다
자바 개허접인데 동시성 프로그래밍의 원리부터 구현까지 싸그리 알 수는 없는 법
큰 그림을 먼저 파악한 후 정말 필요할 때 세부사항까지 공부해나가면 된다
 */