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
