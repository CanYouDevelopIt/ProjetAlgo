package algo.queue.interfaces;

public interface IPriorityQueue<T> {
	/** Add the e element into the priority queue */
	public void add(T e);

	/** remove and return the element with the highest priority */
	public T remove();

	/** return the element with the highest priority */
	public T peek();
}
