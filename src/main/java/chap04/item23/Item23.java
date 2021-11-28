package chap04.item23;

/**
 * 태그 달린 클래스보다는 클래스 계층구조를 활용하라
 */
public class Item23 {

    public static void main(String[] args) {
        OldFigure oldFigure = new OldFigure(2.3);
        System.out.println(oldFigure.area());

        CircleFigure circleFigure = new CircleFigure(5.5);
        System.out.println(circleFigure.radius());
        System.out.println(circleFigure.area());

        RectangleFigure rectangleFigure = new RectangleFigure(3.2, 2.3);
        System.out.println(rectangleFigure.length());
        System.out.println(rectangleFigure.width());
        System.out.println(rectangleFigure.area());
    }
}

/*
boolean & enum 과 같은 flag 를 이용하여 클래스 안에서 로직을 나누는 것은 자바스럽지 못한 방식
책임을 나누라고 클래스 만들어줬는데 안 쓰면 뭐여
하나의 타입이라는 것을 나타내고 싶을 때는 인터페이스를 이용해 두 클래스를 묶어주도록 하자

공통적으로 사용할 필드 변수, 메서드는 인터페이스에 정의해두고, 클래스 각각의 특수한 변수, 기능은
구현 클래스에서 직접 생성, 이용한다
 */