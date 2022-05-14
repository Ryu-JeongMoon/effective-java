package chap08.item55;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.IntStream;

class DeleteMiddle {
	private static <T> T getLinkedList(int size, Function<Integer, T> supplier, BiConsumer<T, T> reducer) {
		T head = supplier.apply(1);
		IntStream.rangeClosed(2, size).mapToObj(supplier::apply).reduce(head, (a, b) -> {
			reducer.accept(a, b);
			return b;
		});
		return head;
	}

	private static void deleteMiddle(Node<Integer> head) {
		Optional<Node<Integer>> oneStep = Optional.of(head);
		Optional<Node<Integer>> doubleStep = oneStep;
		Optional<Node<Integer>> prevStep = Optional.empty();

		while (doubleStep.isPresent() && doubleStep.get().getNext().isPresent()) {
			doubleStep = doubleStep.get().getNext().get().getNext();
			prevStep = oneStep;
			oneStep = oneStep.get().getNext();
		}

		final Optional<Node<Integer>> toDelete = oneStep;
		prevStep.ifPresent(s -> s.setNext(toDelete.flatMap(Node::getNext)));
	}

	private static void deleteMiddleUnsafe(UnsafeNode<Integer> head) {
		UnsafeNode<Integer> oneStep = head;
		UnsafeNode<Integer> doubleStep = oneStep;
		UnsafeNode<Integer> prevStep = null;

		while (doubleStep != null && doubleStep.getNext() != null) {
			doubleStep = doubleStep.getNext().getNext();
			prevStep = oneStep;
			oneStep = oneStep.getNext();
		}
		if (prevStep != null) {
			prevStep.setNext(oneStep.getNext());
		}
	}

	public static void main(String[] args) {
		int size = 10000000;
		Node<Integer> head = getLinkedList(size, Node::new, Node::setNext);
		long before = System.currentTimeMillis();
		deleteMiddle(head);
		System.out.println("Safe: " + (System.currentTimeMillis() - before));

		UnsafeNode<Integer> headUnsafe = getLinkedList(size, UnsafeNode::new, UnsafeNode::setNext);
		before = System.currentTimeMillis();
		deleteMiddleUnsafe(headUnsafe);
		System.out.println("Unsafe: " + (System.currentTimeMillis() - before));
	}
}
