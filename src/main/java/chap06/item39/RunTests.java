package chap06.item39;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class RunTests {

  public static void main(String[] args) throws ClassNotFoundException {
    int tests = 0;
    int passed = 0;
    Scanner scanner = new Scanner(System.in);

    Class<?> testClass = Class.forName(scanner.next());
    for (Method m : testClass.getDeclaredMethods()) {
      if (m.isAnnotationPresent(CustomTest.class)) {
        tests++;
        try {
          m.invoke(null);
          passed++;
        } catch (InvocationTargetException we) {
          Throwable cause = we.getCause();
          System.out.println(m + " FAILED : " + cause);
        } catch (Exception e) {
          System.out.println("WRONG USED : " + m);
        }
      }

      if (m.isAnnotationPresent(ExceptionTest.class)) {
        tests++;
        try {
          m.invoke(null);
          System.out.printf("Test Failed => %s%n", m);
        } catch (InvocationTargetException e) {
          Throwable throwable = e.getCause();
          Class<? extends Throwable>[] throwableTypes = m.getAnnotation(ExceptionTest.class).value();

          for (Class<? extends Throwable> throwableType : throwableTypes) {
            if (throwableType.isInstance(throwable)) {
              passed++;
            } else {
              System.out.printf("Test %s Failed\nexpected => %s\nactual => %s", m, throwableType.getName(), throwable);
            }
          }

        } catch (Exception e) {
          System.out.println("WRONG USED : " + m);
        }
      }
    }
    System.out.printf("Success : %d, Failed : %d%n", passed, tests - passed);
  }
}

/*
input -> chap06.item39.Sample
오호.. JUnit 이 이런 방식으로 돌아가겠거니
 */