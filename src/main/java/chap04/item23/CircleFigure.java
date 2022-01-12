package chap04.item23;

record CircleFigure(double radius) implements Figure {

  @Override
  public double area() {
    return Math.PI * (radius * radius);
  }
}
