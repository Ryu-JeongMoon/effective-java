package chap02.item03;

public class Main {

    public static void main(String[] args) {
        Singleton2 i1 = Singleton2.getInstance();
        Singleton2 i2 = Singleton2.getInstance();

        System.out.println("(i1 == i2) = " + (i1 == i2));

        Singleton1 i3 = Singleton1.getInstance();
        Singleton1 i4 = Singleton1.getInstance();

        System.out.println("(i3 == i4) = " + (i3 == i4));
    }
}
