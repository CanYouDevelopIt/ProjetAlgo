package algo.builder;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import algo.graph.Dijkstra;
import algo.graph.Edge;
import algo.graph.Graph;
import algo.graph.Node;

public class Map extends JFrame implements ActionListener{
	
	final JButton btn = new JButton("Close");
	File fichier;
	Graph graph;
	Node[][] nodes;
	int nbligne;
	int nbcol;
	
	JPanel jpNord;
	JPanel jpSud;
	JButton buttonLancer;
	
	public Map(String f){
		try {
			this.loadFichier(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadFichier(String f) throws IOException {
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
		this.buildMap();
	}
	
	public void buildMap(){
		
		setTitle("Simulateur de foule");
		setSize(nbcol * 26, nbligne * 26 + 80);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jpSud = new JPanel();
		jpSud.setBackground(new java.awt.Color(201, 128, 55));
		
		this.actualiserMap();
		
		JLabel labelTour = new JLabel("TOUR : 0");
		JLabel labelDeplacement = new JLabel("DEPLACEMENTS : 0");
		JLabel labelNbSourisEnCours = new JLabel("SOURIS EN DEPLACEMENTS : 0");
		JLabel labelNbSourisArrive = new JLabel("SOURIS ARRIVEES : 0");
		
		buttonLancer = new JButton("Lancer");
		
		buttonLancer.addActionListener(this);
		
		jpSud.add(labelTour);
		jpSud.add(labelDeplacement);
		jpSud.add(labelNbSourisEnCours);
		jpSud.add(labelNbSourisArrive);
		jpSud.add(buttonLancer);
		
		this.getContentPane().add(jpSud, BorderLayout.SOUTH);
		this.getContentPane().setBackground(new java.awt.Color(201, 128, 55));
	}

	
	public void actualiserMap(){
		
		if(jpNord != null){
			this.getContentPane().remove(jpNord);
		}
		
		jpNord = new JPanel();
		jpNord.setBackground(new java.awt.Color(201, 128, 55));
		
		GridLayout g = new GridLayout(nbligne, nbcol);
		jpNord.setLayout(g);
		for (int i = 0; i < nbligne; i++) {
			for (int j = 0; j < nbcol; j++) {
				Node node = graph.getNode(j, i);
				if (node != null) {
					if (node.getId().equals(" ")) {
						JLabel picLabel = new JLabel(new ImageIcon(
								Images.espace));
						jpNord.add(picLabel);
					} else if (node.getId().equals("D")) {

						JLabel picLabel = new JLabel(new ImageIcon(
								Images.depart));
						jpNord.add(picLabel);
					} else if (node.getId().equals("A")) {

						JLabel picLabel = new JLabel(new ImageIcon(
								Images.fromage));
						jpNord.add(picLabel);
					} else if (node.getId().equals("G")) {
						JLabel picLabel = new JLabel(new ImageIcon(
								Images.buisson));
						jpNord.add(picLabel);
					} else if (node.getId().equals("S")) {
						System.out.println("Souris trouvé = " + i + " - " + j);
						JLabel picLabel = new JLabel(new ImageIcon(
								Images.souris));
						jpNord.add(picLabel);
					}
				} else {
					JLabel picLabel = new JLabel(new ImageIcon(Images.mur));
					jpNord.add(picLabel);

				}
			}
		}
		
		this.getContentPane().add(jpNord, BorderLayout.NORTH);
		
	}
	
	public void repaintFrame(){
		this.validate();
		this.repaint();
	}
	
	public void deplacerSouris() {
		
		Node nodeDepart = this.graph.getNode(3, 3);
		Node nodeArrive = this.graph.getNode(38, 1);
		
		//int i = 1;
		
		Dijkstra d = new Dijkstra(this.graph, nodeDepart, nodeArrive);
		List<Node> cheminPlusCourt = d.cheminPlusCourt();
		nodeDepart = cheminPlusCourt.get(1);
		
		while(!nodeDepart.equals(d.getNodeArrive())){
						
			//System.out.println( nodeDepart.getX() + " - " + nodeDepart.getY());
			
			//PLACEMENT DE LA SOURIS
			graph.getNode(nodeDepart.getX(),nodeDepart.getY()).setId("S");
			
			//ACTUALISER LA MAP
			this.actualiserMap();
			this.repaintFrame();
			
			//SLEEP
			try {
				if(nodeDepart.getIdOrigine().equals("G")){
					Thread.sleep(500);
				}else{
					Thread.sleep(250);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//REMETTRE L'ANCIENNE VALEUR AVANT SOURIS
			graph.getNode(nodeDepart.getX(),nodeDepart.getY()).setId(graph.getNode(nodeDepart.getX(),nodeDepart.getY()).getIdOrigine());
			
			//i++;

			nodeDepart = cheminPlusCourt.get(1);
			d.setNodeDepart(graph.getNode(nodeDepart.getX(),nodeDepart.getY()));
			cheminPlusCourt = d.cheminPlusCourt();	
			
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    if(e.getSource().equals(buttonLancer)){
	    	  new Thread(new Runnable() {
	    	      public void run() {
	    	        deplacerSouris();
	    	      }
	    	  }).start();
	    }
	}
	
}
