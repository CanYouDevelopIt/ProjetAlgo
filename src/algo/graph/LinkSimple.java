package algo.graph;


public class LinkSimple {

	Node value;
	LinkSimple next;

	public LinkSimple() {
	}

	public LinkSimple(Node t) {
		this.value = t;
	}

	public Node getValue() {
		return value;
	}

	public void setValue(Node value) {
		this.value = value;
	}

	public LinkSimple getNext() {
		return next;
	}

	public void setNext(LinkSimple next) {
		this.next = next;
	}

}
