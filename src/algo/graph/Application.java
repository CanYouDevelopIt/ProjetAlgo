package algo.graph;

public class Application {

	public static void main(String[] args) {

		Graph graph = new Graph();

		// Build node.
		Node paris = new Node("Paris");
		Node lyon = new Node("Lyon");
		Node grenoble = new Node("Grenoble");
		Node valence = new Node("Valence");
		Node gap = new Node("Gap");
		Node marseille = new Node("Marseille");

		graph.registerNode(paris);
		graph.registerNode(lyon);
		graph.registerNode(grenoble);
		graph.registerNode(valence);
		graph.registerNode(gap);
		graph.registerNode(marseille);

		// Build edges
		new Edge(paris, lyon, 400);
		new Edge(lyon, grenoble, 100);
		new Edge(lyon, valence, 110);
		new Edge(grenoble, valence, 100);
		new Edge(grenoble, gap, 100);
		new Edge(gap, marseille, 150);
		new Edge(valence, marseille, 250);

		graph.cheminPlusCourt(paris, marseille);

	}

}
