package algo.builder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import algo.graph.Dijkstra;
import algo.graph.Edge;
import algo.graph.Graph;
import algo.graph.Node;

public class Map extends JFrame implements ActionListener {

	private File fichier;
	private Graph graph;
	private Node[][] nodes;
	private int nbligne;
	private int nbcol;

	private JPanel jpNord;
	private JPanel jpSud;
	private JButton buttonLancer;
	private JButton buttonLoadFile;
	private JTextField fieldNbSourisSorti;
	private JTextField fieldVitesse;
	
	private int nbTour = 0;
	private int nbDeplacement = 0;
	private int nbSourisEnCours = 0;
	private int nbSourisArrivees = 0;
	private int nbSourisSorties = 1;
	private int vitesseDeplacement = 500;
	
	// public Map(String f) {
	// try {
	// this.loadFichier(f);
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	public Map() {
		buildMap();
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
						if (nodes[i][j].getId().equals("G")) {
							if (nodes[i][j + 1] != null) {
								new Edge(nodes[i][j], nodes[i][j + 1], 2);
							}
							if (nodes[i + 1][j] != null) {
								new Edge(nodes[i][j], nodes[i + 1][j], 2);
							}
						} else {
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

	public void buildMap() {

		setTitle("Simulateur de foule");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		if(nbligne == 0 & nbcol == 0){
			setSize(d.width / 2, d.height / 2);
		} else {
			setSize(nbcol * 26, nbligne * 26 + 80);
		}
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jpSud = new JPanel();
		jpSud.setBackground(new java.awt.Color(201, 128, 55));

		// this.actualiserMap();

		JLabel labelTour = new JLabel("Tour: " + nbTour);
		JLabel labelDeplacement = new JLabel("D�placements: " + nbDeplacement);
		JLabel labelNbSourisEnCours = new JLabel("Souris en d�placement: " + nbSourisEnCours);
		JLabel labelNbSourisArrive = new JLabel("Souris arriv�es: " + nbSourisArrivees);
		
		JLabel labelPorte = new JLabel("Porte: ");
		fieldNbSourisSorti = new JTextField();
		fieldNbSourisSorti.setPreferredSize(new Dimension(30, 20));
		fieldNbSourisSorti.setText(Integer.toString(nbSourisSorties));
		
		JLabel labelVitesse = new JLabel("Vitesse: ");
		fieldVitesse = new JTextField();
		fieldVitesse.setPreferredSize(new Dimension(30, 20));
		fieldVitesse.setText(Integer.toString(vitesseDeplacement));
		
		buttonLancer = new JButton("Lancer");
		buttonLoadFile = new JButton("Load File");
		buttonLancer.addActionListener(this);
		buttonLoadFile.addActionListener(this);

		jpSud.add(buttonLoadFile);
		jpSud.add(labelTour);
		jpSud.add(labelDeplacement);
		jpSud.add(labelNbSourisEnCours);
		jpSud.add(labelNbSourisArrive);
		jpSud.add(labelPorte);
		jpSud.add(fieldNbSourisSorti);
		jpSud.add(labelVitesse);
		jpSud.add(fieldVitesse);
		jpSud.add(buttonLancer);

		this.getContentPane().add(jpSud, BorderLayout.SOUTH);
		this.getContentPane().setBackground(new java.awt.Color(201, 128, 55));
	}

	public void actualiserMap() {

		this.getContentPane().removeAll();
		
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
						System.out.println("Souris trouvees: " + i + "-" + j);
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
		
		jpSud = new JPanel();
		jpSud.setBackground(new java.awt.Color(201, 128, 55));

		JLabel labelTour = new JLabel("Tour: " + nbTour);
		JLabel labelDeplacement = new JLabel("D�placements: " + nbDeplacement);
		JLabel labelNbSourisEnCours = new JLabel("Souris en d�placement: " + nbSourisEnCours);
		JLabel labelNbSourisArrive = new JLabel("Souris arriv�es: " + nbSourisArrivees);
		

		buttonLancer = new JButton("Lancer");
		buttonLoadFile = new JButton("Load File");
		buttonLancer.addActionListener(this);
		buttonLoadFile.addActionListener(this);
		
		JLabel labelPorte = new JLabel("Porte: ");
		fieldNbSourisSorti = new JTextField();
		fieldNbSourisSorti.setPreferredSize(new Dimension(30, 20));
		fieldNbSourisSorti.setText(Integer.toString(nbSourisSorties));
		
		JLabel labelVitesse = new JLabel("Vitesse: ");
		fieldVitesse = new JTextField();
		fieldVitesse.setPreferredSize(new Dimension(30, 20));
		fieldVitesse.setText(Integer.toString(vitesseDeplacement));
		
		jpSud.add(buttonLoadFile);
		jpSud.add(labelTour);
		jpSud.add(labelDeplacement);
		jpSud.add(labelNbSourisEnCours);
		jpSud.add(labelNbSourisArrive);
		jpSud.add(labelPorte);
		jpSud.add(fieldNbSourisSorti);
		jpSud.add(labelVitesse);
		jpSud.add(fieldVitesse);
		jpSud.add(buttonLancer);
		
		this.getContentPane().add(jpNord, BorderLayout.NORTH);
		this.getContentPane().add(jpSud, BorderLayout.SOUTH);
		this.getContentPane().setBackground(new java.awt.Color(201, 128, 55));
		
	}

	public void repaintFrame() {
		this.validate();
		this.repaint();
	}

	public void deplacerSouris() {

		Node nodeDepart = this.graph.getNode(3, 3);
		Node nodeArrive = this.graph.getNode(38, 1);
		
		nbSourisSorties = Integer.parseInt(fieldNbSourisSorti.getText());
		System.out.println("Nb souris envoyees : " + nbSourisSorties);
		
		vitesseDeplacement = Integer.parseInt(fieldVitesse.getText());
		System.out.println("Vitesse : " + vitesseDeplacement);
		
		List<Node> listeSouris = new ArrayList<Node>();
		for(int i = 0; i < nbSourisSorties; i++){
			Node nodeSouris = nodeDepart;
			listeSouris.add(nodeSouris);
		}
		
		while(nbSourisSorties != nbSourisArrivees){
			
//			for(int i = 0; i < listeSouris.size(); i++){
//				
//				Dijkstra d = new Dijkstra(graph, graph.getNode(listeSouris.get(i).getX(), listeSouris.get(i).getY()), nodeArrive);
//				List<Node> cheminPlusCourt = d.cheminPlusCourt();
//				
//				try {
//					if (cheminPlusCourt.get(0).getIdOrigine().equals("G")) {
//						Thread.sleep(vitesseDeplacement*2);
//					} else {
//						Thread.sleep(vitesseDeplacement);
//					}
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				
//				graph.getNode(cheminPlusCourt.get(1).getX(), cheminPlusCourt.get(1).getY()).setId("S");
//				nbDeplacement++;
//				
//				// ACTUALISER LA MAP
//				this.actualiserMap();
//				this.repaintFrame();
//				
//				graph.getNode(cheminPlusCourt.get(1).getX(), cheminPlusCourt.get(1).getY()).setId(cheminPlusCourt.get(1).getIdOrigine());
//				if(cheminPlusCourt.get(1).equals(nodeArrive)){
//					nbSourisArrivees++;
//				}
//			}
			
			int i = 1;
			Dijkstra d = new Dijkstra(this.graph, nodeDepart, nodeArrive);
			List<Node> cheminPlusCourt = d.cheminPlusCourt();
			Node nodeSouris = cheminPlusCourt.get(i);
			
			
			while (!nodeSouris.equals(d.getNodeArrive())) {
				
				// DEPLACEMENT DE LA SOURIS
				graph.getNode(nodeSouris.getX(), nodeSouris.getY()).setId("S");
				nbDeplacement++;
				nbTour++;
				
				// ACTUALISER LA MAP
				this.actualiserMap();
				this.repaintFrame();
			
				// SLEEP
				try {
					if (nodeSouris.getIdOrigine().equals("G")) {
						Thread.sleep(vitesseDeplacement*2);
					} else {
						Thread.sleep(vitesseDeplacement);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			
				// REMETTRE L'ANCIENNE VALEUR AVANT SOURIS
				graph.getNode(nodeSouris.getX(), nodeSouris.getY()).setId(
						graph.getNode(nodeSouris.getX(), nodeSouris.getY())
								.getIdOrigine());
			
				i++;
				nodeSouris = cheminPlusCourt.get(i);
			}
			
			nbSourisArrivees++;
			
			// ACTUALISER LA MAP
			this.actualiserMap();
			this.repaintFrame();
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(buttonLancer)) {
			new Thread(new Runnable() {
				public void run() {
					deplacerSouris();
				}
			}).start();
		}

		if (e.getSource().equals(buttonLoadFile)) {
			// initMap();
			try {
				loadFichier("TestFiles/test.txt");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Toolkit tk = Toolkit.getDefaultToolkit();
			Dimension d = tk.getScreenSize();
			setSize(nbcol * 26, nbligne * 26 + 80);

			actualiserMap();
		}
	}

	private void initMap() {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File("TestFiles/"));
		fc.setDialogTitle("Choisir Fichier de Simulation");
		fc.setFileFilter(new FileNameExtensionFilter("TXT", "txt"));
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

			try {
				loadFichier(fc.getSelectedFile().getAbsolutePath());
			} catch (IOException e) {
			}

			Toolkit tk = Toolkit.getDefaultToolkit();
			Dimension d = tk.getScreenSize();
			setSize(nbcol * 26, nbligne * 26 + 80);

			actualiserMap();
		}
		System.out.println("Over");
	}

}
