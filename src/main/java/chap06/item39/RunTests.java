package chap06.item39;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunTests {

    public static void main(String[] args) throws ClassNotFoundException {
        int tests = 0;
        int passed = 0;
        Class<?> testClass = Class.forName(args[0]);
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
        }
        System.out.printf("Success : %d, Failed : %d%n", passed, tests - passed);
    }
}

/*
오호.. JUnit 이 이런 방식으로 돌아가겠거니

 */