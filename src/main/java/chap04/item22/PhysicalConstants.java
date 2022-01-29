package chap04.item22;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PhysicalConstants {

  public static final double AVOGADROS_NUMBER = 6.022_140_857e23;

  public static final double BOLTZMANN_CONST = 1.380_648_52e-23;

  public static final double ELECTRON_MASS = 9.109_383_56e-31;
}

/*
상수들을 모아놓는 클래스로 이용하려면 private 기본 생성자 만들어주자
혹시 모를 인스턴스화를 방지해야한다

Constant, Util, Enum 의 static import 는 언제 사용하는가
절대 원칙은 없으나 개인 / 팀의 협의를 거쳐 사용하면 될 것으로 보인다
장점으로는 boiler plate 가 줄고 가독성이 향상된다
그 장점이 유효한 경우는 Name Space 가 오염되지 않는 경우에 한해서다

예를 들어 Integer.MAX_VALUE, Double.MAX_VALUE 처럼 겹치는 변수명이 존재하는데
이러한 경우에 정적 임포트 해버리면 어느 클래스에서 사용되는 변수인지 한 눈에 파악하기 쉽지 않다
일반적으로 값을 변수로 대치했기 때문에 어찌됐든 값을 보려면 클래스 타고 들어가야 하지만
값을 보지 않고 전체 흐름 따라갈 때는 분명 문제의 여지가 있다

아래와 같이 원칙을 정해서 사용하자
1. 정적 임포트한 클래스에 겹치는 변수명이 없을 때
2. 1-2개의 상수 클래스, 이넘만 사용할 때
3. 사용횟수가 많을 때, (하나만 사용하는 경우엔 굳이 정적 임포트할 필요가 없다!)
 */