package algo.linkedlist.nodes;

import algo.linkedlist.nodes.interfaces.ILinkDouble;
import algo.linkedlist.nodes.interfaces.ILinkSimple;

public class LinkDouble<V> implements ILinkDouble<V> {

	V value;
	ILinkDouble<V> next;
	ILinkDouble<V> previous;

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public void setValue(V value) {
		this.value = value;
	}

	@Override
	public ILinkDouble<V> getPrevious() {
		return previous;
	}

	@Override
	public void setPrevious(ILinkDouble<V> pRevious) {
		this.previous = pRevious;
	}

	@Override
	public ILinkDouble<V> getNext() {
		return next;
	}

	@Override
	public void setNext(ILinkDouble<V> nExt) {
		this.next = nExt;
	}

	@Override
	public void setNext(ILinkSimple<V> next) {
		this.next = (ILinkDouble<V>) next;
	}
}
