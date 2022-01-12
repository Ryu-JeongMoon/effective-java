package chap04.item17;

record Complex(double re, double im) implements Cloneable {

  public static Complex valueOf(double re, double im) {
    return new Complex(re, im);
  }

  public double realPart() {
    return re;
  }

  public double imaginaryPart() {
    return im;
  }

  public Complex plus(Complex c) {
    return new Complex(re + c.re, im + c.im);
  }

  public Complex minus(Complex c) {
    return new Complex(re - c.re, im - c.im);
  }

  public Complex times(Complex c) {
    return new Complex(re * c.re, im * c.im);
  }

  public Complex divideBy(Complex c) {
    double tmp = c.re * c.re + c.im * c.im;
    return new Complex((re * c.re + im * c.im) / tmp, (re * c.re - im * c.im) / tmp);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    // pattern variable ?!
    if (!(o instanceof Complex complex)) {
      return false;
    }

    return Double.compare(complex.re, re) == 0 && Double.compare(complex.im, im) == 0;
  }

  @Override
  public int hashCode() {
    return 31 * Double.hashCode(re) + Double.hashCode(im);
  }

  @Override
  public Complex clone() {
    try {
      return (Complex) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }
}
