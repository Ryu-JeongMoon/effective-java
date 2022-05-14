package chap08.item55;

class UnsafeNode<T> {
	private final T data;
	private UnsafeNode<T> next;

	UnsafeNode(T data) {
		this.data = data;
	}

	UnsafeNode<T> getNext() {
		return next;
	}

	void setNext(UnsafeNode<T> next) {
		this.next = next;
	}
}
