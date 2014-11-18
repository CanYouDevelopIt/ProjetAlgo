package algo.graph;

import java.util.ArrayList;
import java.util.List;

public class Node{
	
	private String id;
	private String idOrigine;
	private int x;
	private int y;
	private List<Edge> mesEdges;
	
	public Node(String unId, int x, int y){
		id = unId;
		idOrigine = unId;
		this.x = x;
		this.y = y;
		mesEdges = new ArrayList<Edge>();
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public List<Edge> getEdges() {
		return mesEdges;
	}

	public String getId() {
		return id;
	}	
	
	public boolean equals(Node n){
		return this.x == n.getX() && this.y == n.getY();
	}
	
	public void setId(String unId){
		this.id = unId;
	}
	
	public String getIdOrigine() {
		return idOrigine;
	}

	public void setIdOrigine(String idOrigine) {
		this.idOrigine = idOrigine;
	}

	
}
