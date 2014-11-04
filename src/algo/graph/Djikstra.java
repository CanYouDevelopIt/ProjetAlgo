package algo.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import algo.graph.Node;

public class Djikstra<T> {
	
	private Graph graph;
	private LinkedPriorityQueue listePassage;
	
	public Djikstra(Graph g){
		this.graph = g;
		this.listePassage = new LinkedPriorityQueue();
	}
	
	public void cheminPlusCourt(Node nodeDepart, Node nodeArrive){
		 
		if(nodeDepart == null || nodeArrive == null){
			return;
		}
		
		LinkedPriorityQueue newListePassage = new LinkedPriorityQueue();
		Node nodePere = null;
		Node nodeFils = null;
		
		listePassage.add(nodeDepart);
		
		for(int i=0; i < listePassage.size(); i++){
			
			nodePere = listePassage.peek();
			System.out.println("NodePere = " + nodePere.getId());
			
			for(Edge e: nodePere.getEdges()){
				
				nodeFils = e.getOther(nodePere);
				System.out.println(nodeFils.getId() + ":" + e.getDistance());
				
//				LinkSimple listNodes = new LinkSimple();
//				listNodes.add(nodePere);
//				listNodes.add(nodeFils);				
//				newListePassage.add(listNodes, e.getDistance());
				
			}
			
		}
		
	}
}
