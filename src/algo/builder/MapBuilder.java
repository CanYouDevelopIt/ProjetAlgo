package algo.builder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import algo.graph.*;

public class MapBuilder {

	File fichier;
	Graph graph;
	Node[][] nodes;
	int nbligne;
	int nbcol;

	public void load(String f) throws IOException {
		fichier = new File(f);
		graph = new Graph();
		if (fichier.exists()) {
			BufferedReader br = new BufferedReader(new FileReader(fichier));
			String ligne;
			nbligne = 0;
			nbcol = 0;
			while ((ligne = br.readLine()) != null) {
				nbligne++;
				String[] tab = ligne.split("");
				nbcol = tab.length - 1;
			}
			br.close();

			nodes = new Node[nbligne][nbcol];

			BufferedReader br2 = new BufferedReader(new FileReader(fichier));
			String ligne2;
			int cptligne = 0;
			while ((ligne2 = br2.readLine()) != null) {
				String[] tab2 = ligne2.split("");
				for (int i = 1; i < tab2.length; i++) {
					if (tab2[i].equals(" ") || tab2[i].equals("D")
							|| tab2[i].equals("A") || tab2[i].equals("G")) {
						nodes[cptligne][i - 1] = new Node(tab2[i], i - 1,
								cptligne);
					}
				}
				cptligne++;
			}
			br2.close();
			for (int i = 0; i < nodes.length; i++) {
				for (int j = 0; j < nodes[i].length; j++) {
					if (nodes[i][j] != null) {
						graph.registerNode(nodes[i][j]);
						if(nodes[i][j].getId().equals("G")){
							if (nodes[i][j + 1] != null) {
								new Edge(nodes[i][j], nodes[i][j + 1], 2);
							}
							if (nodes[i + 1][j] != null) {
								new Edge(nodes[i][j], nodes[i + 1][j], 2);
							}
						}else{
							if (nodes[i][j + 1] != null) {
								new Edge(nodes[i][j], nodes[i][j + 1], 1);
							}
							if (nodes[i + 1][j] != null) {
								new Edge(nodes[i][j], nodes[i + 1][j], 1);
							}
						}

					}
				}
			}

		}
	}

	public void show(Graph graph) {
		FileWriter writer = null;
		Collection<Node> mesNodes = graph.getNodes();
		try {
			writer = new FileWriter(fichier);
			for (int i = 0; i < nbligne; i++) {
				String ligne = "";
				for (int j = 0; j < nbcol; j++) {
					boolean trouve = false;
					for (Node uneNode : mesNodes) {
						if (uneNode.getY() == i && uneNode.getX() == j) {
							ligne += uneNode.getId();
							trouve = true;
						}
					}
					if (!trouve) {
						ligne += "*";
					}
				}
				writer.write(ligne + "\r\n");
			}
			writer.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void deplacerSouris(MapFrame mf) {
		
		System.out.println("test");
		
		Node nodeDepart = this.graph.getNode(3, 3);
		Node nodeArrive = this.graph.getNode(38, 1);
		
		System.out.println(nodeDepart.getId() + " = " + nodeArrive.getId());
		
		int i = 1;
		
		Dijkstra d = new Dijkstra(this.graph, nodeDepart, nodeArrive);
		List<Node> cheminPlusCourt = d.cheminPlusCourt();
		nodeDepart = cheminPlusCourt.get(i);
		System.out.println(nodeDepart.getId());
		
		while(!nodeDepart.equals(d.getNodeArrive())){
			
			System.out.println("boucle while");
			
			mf.repaintFrame(nodeDepart.getX(), nodeDepart.getY());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			i++;
			cheminPlusCourt = d.cheminPlusCourt();
			nodeDepart = cheminPlusCourt.get(i);	
			
		}
		
	}

}
