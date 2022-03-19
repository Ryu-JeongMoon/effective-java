package chap06.item36;

public class BitField {

  public static final int PUBLIC = 0x00000001;
  public static final int PRIVATE = 0x00000002;
  public static final int PROTECTED = 0x00000004;
  public static final int STATIC = 0x00000008;
  public static final int FINAL = 0x00000010;
  public static final int SYNCHRONIZED = 0x00000020;
  public static final int VOLATILE = 0x00000040;
  public static final int TRANSIENT = 0x00000080;
  public static final int NATIVE = 0x00000100;
  public static final int INTERFACE = 0x00000200;
  public static final int ABSTRACT = 0x00000400;
  public static final int STRICT = 0x00000800;

  public static void main(String[] args) {
    System.out.println("PUBLIC = " + PUBLIC);
    System.out.println("PRIVATE = " + PRIVATE);
    System.out.println("PROTECTED = " + PROTECTED);
    System.out.println("STATIC = " + STATIC);
    System.out.println("FINAL = " + FINAL);
    System.out.println("SYNCHRONIZED = " + SYNCHRONIZED);
    System.out.println("VOLATILE = " + VOLATILE);
    System.out.println("TRANSIENT = " + TRANSIENT);
    System.out.println("NATIVE = " + NATIVE);
    System.out.println("INTERFACE = " + INTERFACE);
    System.out.println("ABSTRACT = " + ABSTRACT);
    System.out.println("STRICT = " + STRICT);
  }
}
