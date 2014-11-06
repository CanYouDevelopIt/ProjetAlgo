package algo.builder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import algo.graph.*;

public class MapBuilder {

	File fichier;
	Graph graph;
	Node[][] nodes;
	
	public void load(String f) throws IOException{
		fichier = new File(f);
		graph = new Graph();
			if(fichier.exists()){
				BufferedReader br = new BufferedReader(new FileReader(fichier));
				String ligne;
				int nbligne = 0;
				int nbcol = 0;
				while((ligne = br.readLine()) != null){
					nbligne++;
					String[] tab = ligne.split("");
					nbcol = tab.length-1;
				}
				br.close();
				
				nodes = new Node[nbligne][nbcol];
				
				BufferedReader br2 = new BufferedReader(new FileReader(fichier));
				String ligne2;
				int cptligne = 0;
				while((ligne2 = br2.readLine()) != null){
					String[] tab2 = ligne2.split("");
					for(int i = 1; i < tab2.length; i++){
						if(tab2[i].equals(" ") || tab2[i].equals("D") || tab2[i].equals("A") || tab2[i].equals("G")){
							nodes[cptligne][i-1] = new Node(tab2[i]);
						}
					}
					cptligne++;
				}
				br2.close();
				
				for(int i = 0; i < nodes.length; i++){
					for(int j = 0; j < nodes[i].length; j++){
						if(nodes[i][j] != null){
							graph.registerNode(nodes[i][j]);
							if(nodes[i][j+1] != null){
								new Edge(nodes[i][j], nodes[i][j+1], 1);
							}
							if(nodes[i+1][j+1] != null){
								new Edge(nodes[i][j], nodes[i+1][j+1], 1);								
							}
							if(nodes[i+1][j] != null){
								new Edge(nodes[i][j], nodes[i+1][j], 1);
							}
						}
					}
				}
				
			}
	}
}
