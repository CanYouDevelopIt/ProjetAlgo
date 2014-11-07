package algo.graph;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import algo.graph.LinkSimple;
import algo.graph.Node;

public class LinkedPriorityQueue {

	private LinkSimple linkSimple;

	private int listCount = 0;

	private Comparator<Node> comparator;

	public LinkedPriorityQueue() {
		this.setComparator(null);
	}

	public LinkedPriorityQueue(Comparator<Node> comp) {
		this.setComparator(comp);
	}

	public LinkedPriorityQueue(Comparator<Node> comp, LinkSimple ls) {
		this.setComparator(comp);
		this.linkSimple = ls;
	}

	public void add(List<Node> n) {
		// Valeur à ajouter
		LinkSimple newValue = new LinkSimple();
		newValue.setListNodes(n);
		newValue.setNext(null);
		// LinkSimmple == NULL
		  if (linkSimple == null) {
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
	
	/**
	 * Remove throws NoSuchElementException
	 * */
	public List<Node> remove() {
		if (linkSimple.getNext() == null || linkSimple == null) {
			linkSimple = null;
			throw new NoSuchElementException(
					"L'element a enlever est inexistant.");
		} else {
			linkSimple = (LinkSimple) linkSimple.getNext();
			listCount--;
			return linkSimple.getListNodes();
		}
	}

	public List<Node> peek() {
		return linkSimple.getListNodes();
	}

	public int size() {
		return getListCount();
	}

	public List<Node> poll() {
		 if (linkSimple.getNext() == null || linkSimple == null) {
			   return null;
			  } else {
			   List<Node> oldHeadValue = linkSimple.getListNodes();
			   linkSimple = (LinkSimple) linkSimple.getNext();
			   listCount--;
			   return oldHeadValue;
		}
	}

	/**
	 * Element throws NoSuchElementException
	 * */
	public List<Node> Element() {
		if (linkSimple.getListNodes() == null)
			throw new NoSuchElementException("Aucun element trouver.");
		return linkSimple.getListNodes();
	}

	/**
	 * Iterator
	 * */
	public boolean hasNext() {
		return (linkSimple.getNext() != null);
	}

	public List<Node> next() {
		if (this.hasNext() != false)
			return linkSimple.getNext().getListNodes();
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

}
