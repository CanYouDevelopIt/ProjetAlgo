package algo.linkedlist.nodes;

import algo.linkedlist.nodes.interfaces.ILinkSimple;

public class LinkSimple<T> implements ILinkSimple<T> {

	T value;
	ILinkSimple<T> next;

	public LinkSimple() {
	}

	public LinkSimple(T t) {
		this.value = t;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public ILinkSimple<T> getNext() {
		return next;
	}

	public void setNext(ILinkSimple<T> next) {
		this.next = next;
	}

}
