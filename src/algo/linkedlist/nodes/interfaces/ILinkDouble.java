package algo.linkedlist.nodes.interfaces;

public interface ILinkDouble<V> extends ILinkSimple<V>{
	public ILinkDouble<V> getPrevious();
	public void setPrevious(ILinkDouble<V> previous);
	public ILinkDouble<V> getNext();
	public void setNext(ILinkDouble<V> next);
}