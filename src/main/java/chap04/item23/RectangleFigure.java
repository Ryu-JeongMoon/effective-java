package chap04.item23;

record RectangleFigure(double length, double width) implements Figure {

    @Override
    public double area() {
        return length * width;
    }
}

/*
레코드 이 녀석 신기하구만
 */