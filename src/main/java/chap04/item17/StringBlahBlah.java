package chap04.item17;

public class StringBlahBlah {

  public static void main(String[] args) {
    int N = 7777_7777;
    long t;

    // 요 문법은 뭐징?!
    {
      StringBuffer sb = new StringBuffer();
      t = System.currentTimeMillis();
      for (int i = N; i-- > 0; ) {
        sb.append(" ");
      }
      System.out.println(System.currentTimeMillis() - t);
    }

    {
      StringBuilder sb = new StringBuilder();
      t = System.currentTimeMillis();
      sb.append(" ".repeat(N));
      System.out.println(System.currentTimeMillis() - t);
    }

    {
      String sb = "";
      t = System.currentTimeMillis();
      for (int i = N; i-- > 0; ) {
        sb += "!";
      }
      System.out.println("sb.substring(0, 1032) = " + sb.substring(0, 1032));
      System.out.println(System.currentTimeMillis() - t);
    }
  }
}

/*
int N = 77_7777;
StringBuffer -> 25ns
StringBuilder -> 4ns
String -> 27675ns

int N = 7777_7777;
StringBuffer -> 1567ns
StringBuilder -> 34ns
String -> 얜 넘 느려서 안함

compile time 에 String -> StringBuilder 로 바뀐다구 한다는데 아닌가벼
 */