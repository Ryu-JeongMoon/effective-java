package chap05.item28;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class NormalChooser {

  private final Object[] choiceArray;

  public NormalChooser(Object[] choiceArray) {
    this.choiceArray = choiceArray;
  }

  public Object choose() {
    Random rnd = ThreadLocalRandom.current();
    return choiceArray[rnd.nextInt(choiceArray.length)];
  }
}

/*
으잉 왜 레코드로 변환하라 그러지
 */