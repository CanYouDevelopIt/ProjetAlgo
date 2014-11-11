package algo.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;


public class Graph{
	
	private Collection<Node> mesNodes;
	
	public Node getNode(String unId) {
		for(Node unNode : mesNodes){
			if(unNode.getId() == unId){
				return unNode;
			}
		}
		return null;
	}

	public void registerNode(Node unNode) {
		mesNodes.add(unNode);
	}

	public void unregisterNode(String unId) {
		mesNodes.remove(getNode(unId));
	}

	public Collection<Node> getNodes() {
		return mesNodes;
	}
	
	public Graph(){
		mesNodes = new ArrayList<Node>();
	}
	
}
