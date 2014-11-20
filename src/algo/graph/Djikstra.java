package algo.graph;

import java.util.ArrayList;
import java.util.List;
import algo.graph.Node;

public class Djikstra{

	private Graph graph;
	private LinkedPriorityQueue listePassage;

	public Djikstra(Graph g) {
		this.graph = g;
		this.listePassage = new LinkedPriorityQueue();
	}

	public LinkedPriorityQueue cheminPlusCourt(Node nodeDepart, Node nodeArrive) {

		if (nodeDepart == null || nodeArrive == null) {
			return null;
		}
		LinkedPriorityQueue parcoursCorrecte = new LinkedPriorityQueue();

		Node nodePere = nodeDepart;
		Node nodeFils = null;
		List<Node> listNoeud = new ArrayList<Node>();
		List<Node> listNoeudActuel = null;
		int distance = 0;
		int distanceMinimum = 0;

		listNoeud.add(nodePere);
		listePassage.add(listNoeud, distance);

		while (listePassage.getLinkSimple() != null) {

			listNoeudActuel = listePassage.poll();

			nodePere = listNoeudActuel.get(listNoeudActuel.size() - 1);
			for (Edge e : nodePere.getEdges()) {
				nodeFils = e.getOther(nodePere);

				if (isNodeFilsInList(listNoeudActuel, nodeFils)) {
					continue;
				}
				listNoeud = new ArrayList<Node>();
				listNoeud.addAll(listNoeudActuel);
				listNoeud.add(nodeFils);
				printListNoeuds(listNoeud);

				distance = distanceParcours(listNoeud);
				
				if (parcoursCorrecte.size() > 0){
					if(distanceMinimum >= distance){
						if (nodeFils.equals(nodeArrive)) {
							distanceMinimum = distance;
							System.out.println("Here bro !!");
							printListNoeuds(listNoeud);
							parcoursCorrecte.add(listNoeud, distance);
						}else{
							listePassage.add(listNoeud, distance);
						}
					}
				}else{
					if (nodeFils.equals(nodeArrive)) {
						distanceMinimum = distance;
						System.out.println("Here bro !!");
						printListNoeuds(listNoeud);
						parcoursCorrecte.add(listNoeud, distance);
					}else{
						listePassage.add(listNoeud, distance);
					}
				}

			}
		}

		listePassage = parcoursCorrecte;
		this.printPaths(listePassage);
		return listePassage;
	}

	private int distanceParcours(List<Node> listNoeud) {
		int distance = 0;
		for (int i = 0; i < listNoeud.size(); i++) {
			int j = i + 1;
			if (j >= listNoeud.size()) {
				break;
			}
			Node nodeActuel = listNoeud.get(i);
			Node nodeSuivant = listNoeud.get(j);
			for (Edge e : listNoeud.get(i).getEdges()) {
				if (e.getOther(nodeActuel).equals(nodeSuivant)) {
					distance = distance + e.getDistance();
				}
			}
		}
		return distance;
	}

	private boolean isNodeFilsInList(List<Node> listNoeudActuel, Node nodeFils) {
		// String nodeToCheckFor = nodeFils.getId();
		for (Node aNode : listNoeudActuel) {
			if (aNode.equals(nodeFils)) {
				// il existe dans la liste donc pas besoin de l'ajouter
				return true;
			}
		}
		return false;
	}

	public void printPaths(LinkedPriorityQueue newListePassage) {
		System.out.println("Go ");
		LinkSimple ls = newListePassage.getLinkSimple();
		while (ls != null) {
			printListNoeuds(ls.getListNodes());
			System.out.println("Distance : " + ls.getDistance());
			ls = ls.getNext();
		}
	}

	public void printListNoeuds(List<Node> parcours) {
		for (int a = 0; a < parcours.size(); a++) {
			System.out.print("[" + parcours.get(a).getX() + ","
					+ parcours.get(a).getY() + "]");
		}
		System.out.println("");
	}
}
