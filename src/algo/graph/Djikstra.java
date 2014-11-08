package algo.graph;

import java.util.ArrayList;
import java.util.List;

import algo.graph.Node;

public class Djikstra<T> {

	private Graph graph;
	private LinkedPriorityQueue listePassage;

	public Djikstra(Graph g) {
		this.graph = g;
		this.listePassage = new LinkedPriorityQueue();
	}

	public void cheminPlusCourt(Node nodeDepart, Node nodeArrive) {

		if (nodeDepart == null || nodeArrive == null) {
			return;
		}
		LinkedPriorityQueue parcoursCorrecte = new LinkedPriorityQueue();

		Node nodePere = nodeDepart;
		Node nodeFils = null;
		List<Node> listNoeud = new ArrayList<Node>();
		List<Node> listNoeudActuel = null;
		listNoeud.add(nodePere);
		listePassage.add(listNoeud);
		for (int i = 0; i < listePassage.size(); i++) {

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
				if (nodeFils.getId().equals(nodeArrive.getId())) {
					System.out.print(distanceParcours(listNoeud) + " --> ");
					printListNoeuds(listNoeud);
					parcoursCorrecte.add(listNoeud);
				}
				listePassage.add(listNoeud);
			}
		}
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
				if (e.getOther(nodeActuel).getId().equals(nodeSuivant.getId())) {
					distance = distance + e.getDistance();
				}
			}
		}
		return distance;
	}

	private boolean isNodeFilsInList(List<Node> listNoeudActuel, Node nodeFils) {
		String nodeToCheckFor = nodeFils.getId();
		for (Node aNode : listNoeudActuel) {
			if (aNode.getId().equals(nodeToCheckFor)) {
				// il existe dans la liste donc pas besoin de l'ajouter
				return true;
			}
		}
		return false;
	}

	public void printPaths(LinkedPriorityQueue newListePassage) {

		LinkSimple ls = newListePassage.getLinkSimple();
		while (ls != null) {
			printListNoeuds(ls.getListNodes());
			ls = ls.getNext();
		}
	}

	public void printListNoeuds(List<Node> parcours) {
		for (int a = 0; a < parcours.size(); a++) {
			System.out.print(parcours.get(a).getId());
		}
		System.out.println("");
	}
}
