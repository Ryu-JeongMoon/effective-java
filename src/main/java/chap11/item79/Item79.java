package chap11.item79;

import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 과도한 동기화는 피하라
 */
public class Item79 {

  public static void main(String[] args) {
    ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());

    // set.addObserver((s, e) -> System.out.println("e = " + e));

    // ConcurrentModificationException 발생 ?!
    /*
    set.addObserver(new SetObserver<>() {
      @Override
      public void added(ObservableSet<Integer> s, Integer e) {
        System.out.println("element = " + e);
        if (e == 23) {
          s.removeObserver(this);
        }
      }
    });*/

    // 예외는 안 터지고 deadlock 걸림
    set.addObserver(new SetObserver<>() {
      @Override
      public void added(ObservableSet<Integer> s, Integer e) {
        System.out.println("e = " + e);
        if (e == 23) {
          ExecutorService exec = Executors.newSingleThreadExecutor();
          try {
            exec.submit(() -> s.removeObserver(this)).get();
          } catch (ExecutionException | InterruptedException ex) {
            ex.printStackTrace();
          } finally {
            exec.shutdown();
          }
        }
      }
    });

    for (int i = 0; i < 100; i++) {
      set.add(i);
    }
  }
}

/*
리스트를 순회하는 도중에 리스트의 요소를 삭제하는 것은 허용되지 않는다!!
오마이갓 넘 어렵당..
 */