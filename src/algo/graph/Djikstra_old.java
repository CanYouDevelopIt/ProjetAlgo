package algo.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import algo.graph.Node;

public class Djikstra_old<T> {

	private Graph graph;
	private LinkedPriorityQueue listePassage;

	public Djikstra_old(Graph g) {
		this.graph = g;
		this.listePassage = new LinkedPriorityQueue();
	}

	public void cheminPlusCourt(Node nodeDepart, Node nodeArrive) {

		if (nodeDepart == null || nodeArrive == null) {
			return;
		}
		// Liste qui contiendra nos List<Nodes> qui represente un parcours
		LinkedPriorityQueue newListePassage = new LinkedPriorityQueue();

		// Node
		Node nodePere = nodeDepart;
		Node nodeFils = null;

		// liste de noeud qui sera dans LinkPriorityQueue
		List<Node> listNoeud = new ArrayList<Node>();
		List<Node> listNoeudActuel = null;

		// on ajoute le point de départ qui est pour l'instant notre seule
		// parcours
		listNoeud.add(nodePere);
		// on l'ajoute dans notre liste de parcours
		listePassage.add(listNoeud);

		// on check si on a des parcours
		for (int i = 0; i < listePassage.size(); i++) {

			listNoeudActuel = listePassage.peek();
			System.out.println(" Top : " + listNoeudActuel.size());
			nodePere = listNoeudActuel.get(listNoeudActuel.size() - 1);
			System.out.println("NodePere = " + nodePere.getId());

			for (Edge e : nodePere.getEdges()) {
				nodeFils = e.getOther(nodePere);
				System.out.println(nodePere.getId() + " --> "
						+ nodeFils.getId());

				int actualSize = listNoeudActuel.size();
				listNoeud = new ArrayList<Node>();
				listNoeud.add(nodePere);
				// for (int k = 0; k < actualSize; k++) {
				// listNoeud.add(listNoeudActuel.get(k));
				// }
				listNoeud.add(nodeFils);
				for (int z = 0; z < listNoeud.size(); z++) {
					System.out.print("-->" + listNoeud.get(z).getId());
				}
				listNoeud.remove(nodePere);

				System.out.println("");
				newListePassage.add(listNoeud);
				System.out.println(" Taille : " + newListePassage.size());

			}

		}

		// a remonter d'un cran
		listePassage = newListePassage;
	}
}
