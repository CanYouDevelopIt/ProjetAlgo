package algo.graph;

import java.util.ArrayList;
import java.util.List;

public class Node{
	
	private String id;
	private List<Edge> mesEdges;
	
	public Node(String unId){
		id = unId;
		mesEdges = new ArrayList<Edge>();
	}
	
	public List<Edge> getEdges() {
		return mesEdges;
	}

	public String getId() {
		return id;
	}	

}
