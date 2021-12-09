package chap06.item35;

/**
 * ordinal 메서드 대신 인스턴스 필드를 사용하라
 */
public class Item35 {

    public static void main(String[] args) {
        System.out.println(Ensemble.SOLO.numberOfMusicians());
        System.out.println(Ensemble.DUET.numberOfMusicians());
    }
}

/*
ordinal 은 하드 코딩과 같다
코드를 작성하는 순간 값이 정해져 버려 유지보수하기에 극악이다
JPA 를 사용할 때도 주의해야 하고 꼭 EnumType.STRING 으로 줘야한다
EnumType.ORDINAL 의 경우 변경이 필요할 때 요소들의 순서가 바뀌면
DB 에 저장된 데이터들 모두를 업뎃시켜야 할 수도 있다
 */