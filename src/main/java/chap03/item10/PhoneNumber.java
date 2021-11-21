package chap03.item10;

public final class PhoneNumber {

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
}
/*
equals 오버라이드할 때엔 hashCode 도 오버라이드 해줘야 한다
equals 오버라이드할 때 입력값으로 Object 외에 커스텀 객체를 넣지마라
이건 재정의가 아니라 오버로드하는 것임둥

equals 재정의는 매우 번거롭고 지루한 과정이다
AutoValue framework 나 lombok @EqualsAndHashCode 쓰자
 */