package chap04.item23;

class OldFigure {

  final Shape shape;

  double length;
  double width;
  double radius;

  // CIRCLE
  public OldFigure(double radius) {
    shape = Shape.CIRCLE;
    this.radius = radius;
  }

  // RECTANGLE
  public OldFigure(double length, double width) {
    shape = Shape.RECTANGLE;
    this.length = length;
    this.width = width;
  }

  // java 17, switch pattern matching
  double area() {
    return switch (shape) {
      case CIRCLE -> Math.PI * (radius * radius);
      case RECTANGLE -> (length * width);
    };
  }

  enum Shape {RECTANGLE, CIRCLE}
}

/*
관심사를 분리하지 못 했다
사용하지 않을 필드에 대해서도 생성자에서 초기화를 해주어야 한다
이렇게 작성했을 때 장점은 기껏해야 Figure.XX 로 묶을 수 있다는 것 밖에 없다
 */