package algo.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dijkstra {
	
	private Graph graph;
	private LinkedPriorityQueueNode listePassage;
	private Node nodeDepart;
	private Node nodeArrive;
	
	public Dijkstra(Graph g, Node nd, Node na){
		
		graph = g;
		this.listePassage = new LinkedPriorityQueueNode();
		this.nodeDepart = nd;
		this.nodeArrive = na;
		
		listePassage.add(nodeDepart);
		
	}
	
	public List<Node> cheminPlusCourt(){
		
		List<Node> cheminPlusCourt = new ArrayList<Node>();
		
		nodeDepart.setMinDistance(0);
		Node nodeActuel = null;
		Node nodeFils = null;
		int distanceActuel;
		
		while(listePassage.size() > 0){
			
			nodeActuel = listePassage.poll();
			
			for(Edge e : nodeActuel.getEdges()){
				
				nodeFils = e.getOther(nodeActuel);
				distanceActuel = e.getDistance() + nodeActuel.getMinDistance();
				
				if(distanceActuel < nodeFils.getMinDistance()){
					
					nodeFils.setMinDistance(distanceActuel);
					nodeFils.setNodePrecedent(nodeActuel);
					listePassage.add(nodeFils);
					
				}
				
			}
			
		}
		
		for(Node n = nodeArrive; n != null; n = n.getNodePrecedent()){
			cheminPlusCourt.add(n);
		}
		Collections.reverse(cheminPlusCourt);
		
//		for(int i = 0; i < cheminPlusCourt.size(); i++){
//			System.out.print(cheminPlusCourt.get(i).getId() + "-");
//		}
		
		return cheminPlusCourt;
	}
	
}
