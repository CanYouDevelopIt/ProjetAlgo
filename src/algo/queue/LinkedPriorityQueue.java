package algo.queue;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

import algo.linkedlist.nodes.LinkSimple;
import algo.queue.interfaces.IPriorityQueue;

public class LinkedPriorityQueue<T> implements IPriorityQueue<T> {

	private LinkSimple<T> linkSimple = new LinkSimple<T>();

	private int listCount = 0;

	private Comparator<T> comparator;

	public LinkedPriorityQueue() {
		this.setComparator(null);
	}

	public LinkedPriorityQueue(Collection<? extends LinkSimple<T>> c) {
		this.setComparator(null);
		for (LinkSimple<T> ls : c) {
			add(ls.getValue());
			listCount++;
		}
	}

	public LinkedPriorityQueue(Comparator<T> comp) {
		this.setComparator(comp);
	}

	public LinkedPriorityQueue(Comparator<T> comp, LinkSimple<T> ls) {
		this.setComparator(comp);
		this.linkSimple = ls;
	}

	public void add(T e) {
		LinkSimple<T> newValue = new LinkSimple<T>();
		newValue.setValue(e);
		  if (linkSimple.getValue() == null) {
			   linkSimple = newValue;
		  } else {
			  LinkSimple<T> actual = linkSimple;
			  while (actual.getNext() != null) {
				  actual = (LinkSimple<T>) actual.getNext();
			  }
			  	  actual.setNext(newValue);
		  }
		  listCount++;
	}

	/**
	 * Remove throws NoSuchElementException
	 * */
	public T remove() {
		if (linkSimple.getNext() == null || linkSimple == null) {
			linkSimple = null;
			throw new NoSuchElementException(
					"L'element a enlever est inexistant.");
		} else {
			linkSimple = (LinkSimple<T>) linkSimple.getNext();
			listCount--;
			return linkSimple.getValue();
		}
	}

	public T peek() {
		return linkSimple.getValue();
	}

	public int size() {
		return getListCount();
	}

	public T poll() {
		 if (linkSimple.getNext() == null || linkSimple == null) {
			   return null;
			  } else {
			   T oldHeadValue = linkSimple.getValue();
			   linkSimple = (LinkSimple<T>) linkSimple.getNext();
			   listCount--;
			   return oldHeadValue;
		}
	}

	/**
	 * Element throws NoSuchElementException
	 * */
	public T Element() {
		if (linkSimple.getValue() == null)
			throw new NoSuchElementException("Aucun element trouver.");
		return linkSimple.getValue();
	}

	/**
	 * Iterator
	 * */
	public boolean hasNext() {
		return (linkSimple.getNext() != null);
	}

	public T next() {
		if (this.hasNext() != false)
			return linkSimple.getNext().getValue();
		return null;
	}

	/**
	 * To Array function
	 * */
	public T[] toArrary() {
		int size = this.size();
		T[] table = (T[]) new Object[size];
		LinkSimple<T> newValue = linkSimple;
		int i = 0;
		while (newValue != null && i < size) {
			table[i] = newValue.getValue();
			newValue = (LinkSimple<T>) newValue.getNext();
			i++;
		}
		return table;
	}

	/**
	 * Getters & Setters
	 * 
	 * */
	public LinkSimple<T> getLinkSimple() {
		return linkSimple;
	}

	public void setLinkSimple(LinkSimple<T> linkSimple) {
		this.linkSimple = linkSimple;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public void print() {
		LinkSimple<T> ls = linkSimple;
		int i = 0;
		if (ls == null) {
			System.out.println("Link Simple is empty.");
			return;
		}
		while (ls != null) {
			System.out.println((++i) + " = " + ls.getValue());
			ls = (LinkSimple<T>) ls.getNext();
		}
	}

}
