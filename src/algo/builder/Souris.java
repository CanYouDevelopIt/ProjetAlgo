package algo.builder;

import java.util.ArrayList;
import java.util.List;

import algo.graph.Node;

public class Souris {
	
	private int id;
	private List<Node> nodesDejaPasses;
	private Node nodeSouris;
	private Node nodeFromage;
	
	public Souris(int id_, Node n, Node f){
		id = id_;
		nodeSouris = n;
		nodeFromage = f;
		nodesDejaPasses = new ArrayList<Node>();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Node> getNodesDejaPasses() {
		return nodesDejaPasses;
	}
	public void setNodesDejaPasses(List<Node> nodesDejaPasses) {
		this.nodesDejaPasses = nodesDejaPasses;
	}
	
	public void ajouterNodeDejaPasse(Node n){
		nodesDejaPasses.add(n);
	}
	
	public Node getNodeSouris() {
		return nodeSouris;
	}

	public void setNodeSouris(Node nodeSouris) {
		this.nodeSouris = nodeSouris;
	}
	
	public Node getNodeFromage() {
		return nodeFromage;
	}

	public void setNodeFromage(Node nodeFromage) {
		this.nodeFromage = nodeFromage;
	}
	
}
