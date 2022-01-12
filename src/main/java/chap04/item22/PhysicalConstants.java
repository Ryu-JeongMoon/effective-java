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
 */