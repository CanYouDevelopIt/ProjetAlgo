package algo.builder;

import java.util.ArrayList;
import java.util.List;

import algo.graph.Node;

public class Souris {
	
	private int id;
	private List<Node> nodesDejaPasses;
	private Node nodeSouris;
	
	public Souris(int id_, Node n){
		id = id_;
		nodeSouris = n;
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

	
}
