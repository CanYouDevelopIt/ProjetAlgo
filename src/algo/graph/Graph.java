package algo.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


public class Graph{
	
	private Collection<Node> mesNodes;
	
	public Node getNode(int x, int y) {
		for(Node unNode : mesNodes){
			if(unNode.getX() == x && unNode.getY() == y){
				return unNode;
			}
		}
		return null;
	}

	public void registerNode(Node unNode) {
		mesNodes.add(unNode);
	}

	public void unregisterNode(String unId, int x, int y) {
		mesNodes.remove(getNode(x, y));
	}

	public Collection<Node> getNodes() {
		return mesNodes;
	}
	
	public Graph(){
		mesNodes = new ArrayList<Node>();
	}
	
}
