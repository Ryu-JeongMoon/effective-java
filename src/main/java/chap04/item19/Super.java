package chap04.item19;

public class Super {

    public Super() {
        // 여기서 부르는 놈은 무조건 Super 의 overrideMe() 가 아니다
        // context 에 따라 달라지며 하위 클래스에서 호출된 것이라면 Sub 의 overrideMe() 가 호출된다
        overrideMe();
    }

    public void overrideMe() {
        System.out.println("YAHOO");
    }
}
