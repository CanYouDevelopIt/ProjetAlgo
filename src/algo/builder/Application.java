package algo.builder;

import java.io.IOException;

import algo.graph.Djikstra;
import algo.graph.Node;

public class Application {

	public static void main(String[] args) {
		MapBuilder mb = new MapBuilder();
		try {
			mb.load("C:\\Users\\Jeremy\\Documents\\Git\\ProjetAlgo\\src\\algo\\builder\\test.txt");
			mb.show(mb.graph);
			
			Djikstra d = new Djikstra(mb.graph);

			Node nodeDepart = mb.graph.getNode(3, 3);
			Node nodeArrive = mb.graph.getNode(38, 1);
			
//			Node nodeDepart = mb.graph.getNode(1, 2);
//			Node nodeArrive = mb.graph.getNode(5, 2);
			
			d.cheminPlusCourt(nodeDepart, nodeArrive);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new MapFrame(mb.nbcol, mb.nbligne, mb.graph);
	}

}
