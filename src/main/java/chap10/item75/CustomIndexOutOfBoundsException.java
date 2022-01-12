package chap10.item75;

public class CustomIndexOutOfBoundsException extends IndexOutOfBoundsException {

  private final int lowerBound;
  private final int upperBound;
  private final int index;

  public CustomIndexOutOfBoundsException(int lowerBound, int upperBound, int index) {
    super(String.format("최솟값 : %d, 최댓값 : %d, 인덱스 : %d", lowerBound, upperBound, index));

    this.lowerBound = lowerBound;
    this.upperBound = upperBound;
    this.index = index;
  }

  public int getLowerBound() {
    return lowerBound;
  }

  public int getUpperBound() {
    return upperBound;
  }

  public int getIndex() {
    return index;
  }
}
