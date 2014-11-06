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
		
		List<Node> listNoeud = new ArrayList<Node>();
		LinkedPriorityQueue newListePassage = new LinkedPriorityQueue();
		
		Node nodePere = nodeDepart;
		Node nodeFils = null;
		List<Node> listNoeudActuel = null;
		
		listNoeud.add(nodePere);
		listePassage.add(listNoeud);
		
		for(int i=0; i < listePassage.size(); i++){
			
			listNoeudActuel = listePassage.peek();
			nodePere = listNoeudActuel.get(listNoeudActuel.size() - 1);
			System.out.println("NodePere = " + nodePere.getId());
				
			for(Edge e: nodePere.getEdges()){
					
				nodeFils = e.getOther(nodePere);
				System.out.println(nodeFils.getId() + ":" + e.getDistance());
				for(int k=0; k < listNoeudActuel.size(); k++){
					listNoeud.add(listNoeudActuel.get(k));
				}
				listNoeud.add(nodeFils);
					
				newListePassage.add(listNoeud);
					
			}				
		}
		listePassage = newListePassage;
	}
}
