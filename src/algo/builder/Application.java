package algo.builder;

import java.io.IOException;

import algo.graph.Djikstra;
import algo.graph.LinkedPriorityQueue;
import algo.graph.Node;

public class Application {

	public static void main(String[] args) {
		MapBuilder mb = new MapBuilder();
		try {
			mb.load("/Users/pascal/git/ProjetAlgo/src/algo/builder/test1.txt");
			//mb.show(mb.graph);
			
			MapFrame mf = new MapFrame(mb.nbcol, mb.nbligne, mb.graph);
			
			Djikstra d = new Djikstra(mb.graph);

//			Node nodeDepart = mb.graph.getNode(3, 3);
//			Node nodeArrive = mb.graph.getNode(38, 1);

			Node nodeDepart = mb.graph.getNode(1, 2);
			Node nodeArrive = mb.graph.getNode(4, 5);
			
			LinkedPriorityQueue listeCheminPlusCourt = d.cheminPlusCourt(nodeDepart, nodeArrive);
			mb.deplacerSouris(listeCheminPlusCourt,mf);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

}
