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
	
	public void cheminPlusCourt(Node nodeDepart, Node nodeArrive){
		
		 HashMap<Node, Boolean> nodesEffectues = new HashMap<Node, Boolean>();
		 HashMap<Node, Node> antecedants = new HashMap<Node, Node>();
		 HashMap<Node, Integer> distance = new  HashMap<Node, Integer>();
		 
		 ArrayList<Node> nodesFils = new ArrayList<Node>();
		 
		 Node nodePere = null;
		 Node nodeFils = null;
		 boolean nodeArriveTrouve = false;
		 
		 for(Node n: mesNodes){
			 if(nodeDepart.equals(n)){
				 distance.put(nodeDepart, 0);
			 }else{
				 distance.put(nodeDepart, -1);
			 }
			 nodesEffectues.put(n, false);
			 antecedants.put(n, null);
		 }
		 
		 nodePere = nodeDepart;
		 
		 while (!nodeArriveTrouve){
			 System.out.print(nodePere.getId() + " - ");
			 for(Edge e: nodePere.getEdges()){
				 nodeFils = e.getOther(nodePere);
				 
				 if(nodesEffectues.get(nodeFils).equals(false)){
					 if(nodeFils.equals(nodeArrive)){
						 System.out.print(nodeFils.getId());
						 System.out.print("\n Trouv�");
						 nodeArriveTrouve = true;
					 }
					 distance.put(nodeFils, e.getDistance());
					 antecedants.put(nodeFils, nodePere);
					 nodesFils.add(nodeFils);
				 }

			 }
			 nodesEffectues.put(nodePere, true);
			 
			 if(nodesFils.size() == 0){
				 System.out.println("Failed.");
				 nodeArriveTrouve = true;
			 }else{
				 for(Node n: nodesFils){
					System.out.print(distance.get(n)); 
				 }
				 nodePere = nodesFils.get(0);
			 }
			 
			 nodesFils.clear();
		 }
			
	}
	
}
