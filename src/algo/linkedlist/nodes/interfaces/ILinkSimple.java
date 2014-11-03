package algo.linkedlist.nodes.interfaces;

public interface ILinkSimple<V> {
	public V getValue();

	public void setValue(V value);

	public ILinkSimple<V> getNext();

	public void setNext(ILinkSimple<V> next);
}