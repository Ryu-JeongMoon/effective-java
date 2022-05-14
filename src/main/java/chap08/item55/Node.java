package chap08.item55;

import java.util.Optional;

class Node<T> {
	private final T data;
	private Optional<Node<T>> next = Optional.empty();

	Node(T data) {

		this.data = data;
	}

	Optional<Node<T>> getNext() {
		return next;
	}

	void setNext(Node<T> next) { setNext(Optional.ofNullable(next)); }

	void setNext(Optional<Node<T>> next ) { this.next = next; }
}
