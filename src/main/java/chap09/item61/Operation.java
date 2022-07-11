package chap09.item61;

public class Operation {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		terriblySlow();
		System.out.println(System.currentTimeMillis() - start);

		start = System.currentTimeMillis();
		faster();
		System.out.println(System.currentTimeMillis() - start);
	}

	private static void terriblySlow() {
		Long sum = 0L;
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			sum += i;
		}
		System.out.println("sum = " + sum);
	}

	private static void faster() {
		long sum = 0L;
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			sum += i;
		}
		System.out.println("sum = " + sum);
	}
}
