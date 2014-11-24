package algo.builder;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import algo.graph.Dijkstra;
import algo.graph.Djikstra;
import algo.graph.LinkedPriorityQueue;
import algo.graph.Node;

public class Application {

	public static void main(String[] args) {
		MapBuilder mb = new MapBuilder();
		try {
			
			mb.load("TestFiles/test.txt");
			MapFrame mf = new MapFrame(mb);
			//mf.getMb().deplacerSouris(mf);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
