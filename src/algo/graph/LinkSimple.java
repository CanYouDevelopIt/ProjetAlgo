package algo.graph;

import java.util.List;


public class LinkSimple {

	List<Node> listNodes;
	LinkSimple next;

	public LinkSimple() {
	}

	public LinkSimple(List<Node> n) {
		this.listNodes = n;
	}

	public List<Node> getListNodes() {
		return listNodes;
	}

	public void setListNodes(List<Node> n) {
		this.listNodes = n;
	}

	public LinkSimple getNext() {
		return next;
	}

	public void setNext(LinkSimple next) {
		this.next = next;
	}

}
