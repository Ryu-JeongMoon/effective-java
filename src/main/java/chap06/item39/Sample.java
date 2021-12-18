package chap06.item39;

public class Sample {

    @CustomTest
    public void m1() {}

    public void m2() {}

    @CustomTest
    public void m3() {
        throw new RuntimeException("x");
    }
}
