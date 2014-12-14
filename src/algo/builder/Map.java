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
import java.util.HashMap;
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

import algo.graph.Dikstra;
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

	private List<JTextField> listFieldNbSourisSorti = new ArrayList<JTextField>();
	private List<Integer> listNbSourisSorti = new ArrayList<Integer>();
	private List<Node> listNodeDepart = new ArrayList<Node>();
	private List<Node> listNodeFromage = new ArrayList<Node>();
	private HashMap<Node, Node> listDepartToFromage = new HashMap<Node, Node>();

	private JTextField fieldVitesse;

	private int nbPorte = 0;
	private int nbTour = 0;
	private int nbDeplacement = 0;
	private int nbSourisEnCours = 0;
	private int nbSourisArrivees = 0;
	private int nbSourisSorties = 0;
	private int vitesseDeplacement = 500;
	private boolean isNotDisabled = true;

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

						if (nodes[i][j].getId().equals("D")) {
							listNodeDepart.add(nodes[i][j]);
							nbPorte++;
						} else if (nodes[i][j].getId().equals("A")) {
							listNodeFromage.add(nodes[i][j]);
						}

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
		if (nbligne == 0 & nbcol == 0) {
			setSize(d.width / 2, d.height / 2);
		} else {
			setSize(nbcol * 26, nbligne * 26 + 80);
		}
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jpNord = new JPanel();
		jpNord.setBackground(new java.awt.Color(201, 128, 55));

		jpSud = new JPanel();
		jpSud.setBackground(new java.awt.Color(201, 128, 55));

		JLabel labelTour = new JLabel("Tour: " + nbTour);
		JLabel labelDeplacement = new JLabel("Déplacements: " + nbDeplacement);
		JLabel labelNbSourisEnCours = new JLabel("Souris en déplacement: "
				+ nbSourisEnCours);
		JLabel labelNbSourisArrive = new JLabel("Souris arrivées: "
				+ nbSourisArrivees);
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
		jpSud.add(labelVitesse);
		jpSud.add(fieldVitesse);
		jpSud.add(buttonLancer);

		this.getContentPane().add(jpNord, BorderLayout.NORTH);
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

						JLabel picLabel = null;

						if (node.getIdOrigine().equals("G")) {
							picLabel = new JLabel(new ImageIcon(
									Images.sourisBuisson));
						} else {
							picLabel = new JLabel(new ImageIcon(Images.souris));
						}

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
		JLabel labelDeplacement = new JLabel("Déplacements: " + nbDeplacement);
		JLabel labelNbSourisEnCours = new JLabel("Souris en déplacement: "
				+ nbSourisEnCours);
		JLabel labelNbSourisArrive = new JLabel("Souris arrivées: "
				+ nbSourisArrivees);

		buttonLancer = new JButton("Lancer");
		buttonLoadFile = new JButton("Load File");
		buttonLancer.addActionListener(this);
		buttonLoadFile.addActionListener(this);

		JLabel labelVitesse = new JLabel("Vitesse: ");
		fieldVitesse = new JTextField();
		fieldVitesse.setPreferredSize(new Dimension(30, 20));
		fieldVitesse.setText(Integer.toString(vitesseDeplacement));

		jpSud.add(buttonLoadFile);
		jpSud.add(labelTour);
		jpSud.add(labelDeplacement);
		jpSud.add(labelNbSourisEnCours);
		jpSud.add(labelNbSourisArrive);

		for (int i = 0; i < nbPorte; i++) {
			JLabel labelPorte = new JLabel("Porte " + (i + 1) + " :");
			jpSud.add(labelPorte);
			jpSud.add(listFieldNbSourisSorti.get(i));
		}

		jpSud.add(labelVitesse);
		jpSud.add(fieldVitesse);
		jpSud.add(buttonLancer);

		this.getContentPane().add(jpNord, BorderLayout.NORTH);
		this.getContentPane().add(jpSud, BorderLayout.SOUTH);
		this.getContentPane().setBackground(new java.awt.Color(201, 128, 55));
		
		buttonLancer.setEnabled(isNotDisabled);
		buttonLoadFile.setEnabled(isNotDisabled);
	}

	public void repaintFrame() {
		this.validate();
		this.repaint();
	}

	public void deplacerSouris() {

		List<Souris> listeSouris = new ArrayList<Souris>();

		for (int i = 0; i < listNbSourisSorti.size(); i++) {
			for (int j = 0; j < listNbSourisSorti.get(i); j++) {
				Node nodeSouris = listNodeDepart.get(i);
				Node nodeFromage = listDepartToFromage.get(listNodeDepart
						.get(i));
				listeSouris.add(new Souris(nbSourisSorties, nodeSouris,
						nodeFromage));
				nbSourisSorties++;
			}
		}

		System.out.println("Nb souris envoyees : " + nbSourisSorties);

		vitesseDeplacement = Integer.parseInt(fieldVitesse.getText());
		System.out.println("Vitesse : " + vitesseDeplacement);

		while (nbSourisSorties != nbSourisArrivees) {

			for (int i = 0; i < listeSouris.size(); i++) {

				// CREATION DIKSTRA DEPUIS POSITION SOURIS
				Dikstra d = new Dikstra(graph, graph.getNode(listeSouris.get(i)
						.getNodeSouris().getX(), listeSouris.get(i)
						.getNodeSouris().getY()), graph.getNode(listeSouris
						.get(i).getNodeFromage().getX(), listeSouris.get(i)
						.getNodeFromage().getY()), listeSouris.get(i)
						.getNodesDejaPasses());
				List<Node> cheminPlusCourt = d.cheminPlusCourtOptimiser();

				if (cheminPlusCourt.size() > 1) {

					try {
						if (cheminPlusCourt.get(0).getIdOrigine().equals("G")) {
							Thread.sleep(vitesseDeplacement * 2);
						} else {
							Thread.sleep(vitesseDeplacement);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					// PLACER GRAPHIQUEMENT LA SOURIS
					graph.getNode(cheminPlusCourt.get(0).getX(),
							cheminPlusCourt.get(0).getY()).setId(
							cheminPlusCourt.get(0).getIdOrigine());
					graph.getNode(cheminPlusCourt.get(1).getX(),
							cheminPlusCourt.get(1).getY()).setId("S");

					// SET NOUVELLE POSITION SOURIS
					listeSouris.get(i).setNodeSouris(cheminPlusCourt.get(1));
					// listeSouris.get(i).ajouterNodeDejaPasse(cheminPlusCourt.get(0));
					nbDeplacement++;

					// SI UNE SOURIS COMMENCE A SE DEPLACER ++
					for (int j = 0; j < listNodeDepart.size(); j++) {
						if (cheminPlusCourt.get(0)
								.equals(listNodeDepart.get(j))
								&& !cheminPlusCourt.get(1).equals(
										listNodeDepart.get(j))) {
							nbSourisEnCours++;
						}
					}

					// SI UNE SOURIS EST ARRIVEE

					if (listeSouris.get(i).getNodeSouris()
							.equals(listeSouris.get(i).getNodeFromage())) {
						graph.getNode(
								listeSouris.get(i).getNodeSouris().getX(),
								listeSouris.get(i).getNodeSouris().getY())
								.setId(listeSouris.get(i).getNodeSouris()
										.getIdOrigine());
						nbSourisArrivees++;
						nbSourisEnCours--;
					}

				}

				if (listeSouris.size() == 1) {
					nbTour++;
				} else if (i == listeSouris.size() - 1) {
					nbTour++;
				}

				// ACTUALISER LA MAP
				this.actualiserMap();
				this.repaintFrame();
			}
		}

		isNotDisabled = true;
		buttonLancer.setEnabled(isNotDisabled);
		buttonLoadFile.setEnabled(isNotDisabled);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(buttonLancer)) {
			isNotDisabled = false;
			buttonLancer.setEnabled(isNotDisabled);
			buttonLoadFile.setEnabled(isNotDisabled);
			nbTour = 0;
			nbDeplacement = 0;
			nbSourisEnCours = 0;
			nbSourisArrivees = 0;
			nbSourisSorties = 0;
			listNbSourisSorti.clear();

			new Thread(new Runnable() {
				public void run() {
					for (int i = 0; i < nbPorte; i++) {
						listNbSourisSorti.add(Integer
								.parseInt(listFieldNbSourisSorti.get(i)
										.getText()));
					}
					deplacerSouris();
				}
			}).start();
		}

		if (e.getSource().equals(buttonLoadFile)) {
			initMap();

			for (int i = 0; i < listNodeDepart.size(); i++) {

				int distance = 999999;

				for (int j = 0; j < listNodeFromage.size(); j++) {

					Dikstra di = new Dikstra(graph, listNodeDepart.get(i),
							listNodeFromage.get(j), null);
					List<Node> chemin = di.cheminPlusCourtOptimiser();

					if (distance > chemin.size()) {
						distance = chemin.size();
						listDepartToFromage.put(listNodeDepart.get(i),
								listNodeFromage.get(j));
					}
				}

			}
		}
	}

	private void initMap() {

		listFieldNbSourisSorti.clear();
		listNbSourisSorti.clear();
		listNodeDepart.clear();
		listNodeFromage.clear();
		listDepartToFromage.clear();

		fieldVitesse.setText("500");

		nbPorte = 0;
		nbTour = 0;
		nbDeplacement = 0;
		nbSourisEnCours = 0;
		nbSourisArrivees = 0;
		nbSourisSorties = 0;
		vitesseDeplacement = 500;

		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File("TestFiles/"));
		fc.setDialogTitle("Choisir Fichier de Simulation");
		fc.setFileFilter(new FileNameExtensionFilter("TXT", "txt"));
		if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

			try {
				loadFichier(fc.getSelectedFile().getAbsolutePath());
			} catch (IOException e) {
			}

			setSize(nbcol * 26, nbligne * 26 + 80);

			for (int i = 0; i < nbPorte; i++) {
				JTextField fieldNbSourisSorti = new JTextField();
				fieldNbSourisSorti.setPreferredSize(new Dimension(30, 20));
				fieldNbSourisSorti.setText(Integer.toString(1));
				listFieldNbSourisSorti.add(fieldNbSourisSorti);
			}

			actualiserMap();
		}
		System.out.println("Over");
	}

}
