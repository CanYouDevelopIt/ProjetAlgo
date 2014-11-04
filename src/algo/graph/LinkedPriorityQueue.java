package algo.graph;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

import algo.graph.LinkSimple;
import algo.graph.Node;

public class LinkedPriorityQueue {

	private LinkSimple linkSimple = new LinkSimple();

	private int listCount = 0;

	private Comparator<Node> comparator;

	public LinkedPriorityQueue() {
		this.setComparator(null);
	}

//	public LinkedPriorityQueue(Collection<? extends LinkSimple> c) {
//		this.setComparator(null);
//		for (LinkSimple ls : c) {
//			add(ls.getValue());
//			listCount++;
//		}
//	}

	public LinkedPriorityQueue(Comparator<Node> comp) {
		this.setComparator(comp);
	}

	public LinkedPriorityQueue(Comparator<Node> comp, LinkSimple ls) {
		this.setComparator(comp);
		this.linkSimple = ls;
	}

	public void add(Node e) {
		LinkSimple newValue = new LinkSimple();
		newValue.setValue(e);
		  if (linkSimple.getValue() == null) {
			   linkSimple = newValue;
		  } else {
			  LinkSimple actual = linkSimple;
			  while (actual.getNext() != null) {
				  actual = (LinkSimple) actual.getNext();
			  }
			  	  actual.setNext(newValue);
		  }
		  listCount++;
	}
	public void add(Collection<? extends LinkSimple> c, int distance){

		for (LinkSimple ls : c) {
			add(ls.getValue());
			listCount++;
		}
	}

	/**
	 * Remove throws NoSuchElementException
	 * */
	public Node remove() {
		if (linkSimple.getNext() == null || linkSimple == null) {
			linkSimple = null;
			throw new NoSuchElementException(
					"L'element a enlever est inexistant.");
		} else {
			linkSimple = (LinkSimple) linkSimple.getNext();
			listCount--;
			return linkSimple.getValue();
		}
	}

	public Node peek() {
		return linkSimple.getValue();
	}

	public int size() {
		return getListCount();
	}

	public Node poll() {
		 if (linkSimple.getNext() == null || linkSimple == null) {
			   return null;
			  } else {
			   Node oldHeadValue = linkSimple.getValue();
			   linkSimple = (LinkSimple) linkSimple.getNext();
			   listCount--;
			   return oldHeadValue;
		}
	}

	/**
	 * Element throws NoSuchElementException
	 * */
	public Node Element() {
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

	public Node next() {
		if (this.hasNext() != false)
			return linkSimple.getNext().getValue();
		return null;
	}

	/**
	 * Getters & Setters
	 * 
	 * */
	public LinkSimple getLinkSimple() {
		return linkSimple;
	}

	public void setLinkSimple(LinkSimple linkSimple) {
		this.linkSimple = linkSimple;
	}

	public int getListCount() {
		return listCount;
	}

	public void setListCount(int listCount) {
		this.listCount = listCount;
	}

	public Comparator<Node> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<Node> comparator) {
		this.comparator = comparator;
	}

	public void print() {
		LinkSimple ls = linkSimple;
		int i = 0;
		if (ls == null) {
			System.out.println("Link Simple is empty.");
			return;
		}
		while (ls != null) {
			System.out.println((++i) + " = " + ls.getValue());
			ls = (LinkSimple) ls.getNext();
		}
	}

}
