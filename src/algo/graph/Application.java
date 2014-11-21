package algo.graph;

public class Application {

	public static void main(String[] args) {

		Graph graph = new Graph();

		// Build node.
		Node A = new Node("A",0,0);
		Node B = new Node("B",0,0);
		Node C = new Node("C",0,0);
		Node D = new Node("D",0,0);
		Node E = new Node("E",0,0);
		Node F = new Node("F",0,0);
		Node G = new Node("G",0,0);

		graph.registerNode(A);
		graph.registerNode(B);
		graph.registerNode(C);
		graph.registerNode(D);
		graph.registerNode(E);
		graph.registerNode(F);
		graph.registerNode(G);

		// Build edges
		new Edge(A, B, 5);
		new Edge(A, C, 20);
		new Edge(A, D, 10);
		new Edge(B, F, 10);
		new Edge(B, C, 30);
		new Edge(C, D, 5);
		new Edge(C, F, 5);
		new Edge(C, G, 10);
		new Edge(C, E, 2);
		new Edge(D, E, 10);
		new Edge(E, G, 10);
		new Edge(F, G, 20);

		Dijkstra d = new Dijkstra(graph,A,G);

		d.cheminPlusCourt();
		
	}

}
