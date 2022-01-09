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

람다 대신 익명 클래스를 사용하는 이유가 무엇인가
람다는 자기 자신 (this) 을 넘길 수단이 없다, 따라서 this 를 넘겨서 수행해야 하는 작업이 있다면 익명 클래스를 사용해야 한다

현대 멀티 코어가 발전함에 따라 동시성의 병목은 lock 을 얻는 시간이 아닌,
모든 코어가 일관된 메모리를 보기 위한 시간이 되었다

따라서 메서드 내부 동기화는 추천되지 않고, thread-safety 하지 않음을 명시하고
이를 사용하는 쪽에서 제어하게 하는 것이 낫다
동시성을 월등히 개선할 수 있을 때 내부 동기화를 사용하도록 하자

내부에서 동기화하기로 결정했다면 아래 기법들을 사용해 동시성 지원을 높일 수 있다 (요놈들 찾아볼 것)
- lock splitting
- lock striping
- nonblocking concurrency control
 */