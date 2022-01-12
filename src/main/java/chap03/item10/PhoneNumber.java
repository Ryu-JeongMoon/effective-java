package chap03.item10;

import java.util.Comparator;

public final class PhoneNumber implements Cloneable, Comparable<PhoneNumber> {

  // comparingInt 에서 타입 명시 안 해주면 에러난당 자바 바보자식
  private static final Comparator<PhoneNumber> COMPARATOR =
    Comparator.comparingInt((PhoneNumber pn) -> pn.areaCode)
      .thenComparingInt(pn -> pn.prefix)
      .thenComparingInt(pn -> pn.lineNum);

  private final int areaCode, prefix, lineNum;
  private int hashCode;

  public PhoneNumber(int areaCode, int prefix, int lineNum) {
    this.areaCode = rangeCheck(areaCode, 999, "지역 코드");
    this.prefix = rangeCheck(prefix, 999, "prefix");
    this.lineNum = rangeCheck(lineNum, 9999, "가입자 번호");
  }

  private static short rangeCheck(int val, int max, String arg) {
    if (val < 0 || val > max) {
      throw new IllegalArgumentException(arg + ": " + val);
    }
    return (short) val;
  }

  // 올바른 equals 메서드의 예
  // 자기자신과 비교는 최적화를 위해서임, instanceof 연산자를 이용해 타입이 다른 경우 false 반환할 수 있도록 함
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    } else if (!(obj instanceof PhoneNumber)) {
      return false;
    }

    PhoneNumber pn = (PhoneNumber) obj;
    return pn.lineNum == lineNum && pn.prefix == prefix && pn.areaCode == areaCode;
  }

  // hashCode lazy initialization
  @Override
  public int hashCode() {
    int result = hashCode;
    if (result == 0) {
      result = Integer.hashCode(areaCode);
      result = 31 * result + Integer.hashCode(prefix);
      result = 31 * result + Integer.hashCode(lineNum);
      hashCode = result;
    }
    return hashCode;
  }

  // 가독성과 디버깅을 위한 toString override
  @Override
  public String toString() {
    return String.format("%03d-%03d-%04d", areaCode, prefix, lineNum);
  }

  // clone 의 반환형은 Object 인데 자바가 공변 반환 (covariant return typing) 을 지원하므로 PhoneNumber 로 반환 가능
  @Override
  public PhoneNumber clone() {
    try {
      return (PhoneNumber) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }

  // 어중간하게 삼항 연산자 쓸 바엔 가독성을 위해 if 문 쓰자
  // COMPARATOR 를 이용해서 깔쌈하게 작성 가능한데 성능 저하됨 (약 10%)
  @Override
  public int compareTo(PhoneNumber o) {
//        int result = Integer.compare(areaCode, o.areaCode);
//        if (result == 0) {
//            result = Integer.compare(prefix, o.prefix);
//            if (result == 0) {
//                result = Integer.compare(lineNum, o.lineNum);
//            }
//        }
//        return result;

    return COMPARATOR.compare(this, o);
  }
}
/*
equals 오버라이드할 때엔 hashCode 도 오버라이드 해줘야 한다
equals 오버라이드할 때 입력값으로 Object 외에 커스텀 객체를 넣지마라
이건 재정의가 아니라 오버로드하는 것임둥

equals 재정의는 매우 번거롭고 지루한 과정이다
AutoValue framework 나 lombok @EqualsAndHashCode 쓰자
 */