package algo.builder;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;

import algo.graph.Graph;
import algo.graph.Node;

public class MapFrame extends JFrame {

	private Graph graph;
	private int nbligne;
	private int nbcol;
	
	private JPanel jpNord;
	private JPanel jpSud;
	
	public MapFrame(int nbcol_, int nbligne_, Graph graph_) {
		
		this.graph = graph_;
		this.nbcol = nbcol_;
		this.nbligne = nbligne_;
		
		setTitle("Simulateur de foule");
		setSize(nbcol * 26, nbligne * 26 + 80);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jpNord = new JPanel();
		jpSud = new JPanel();
		
		System.out.println(nbcol);
		System.out.println(nbligne);
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
		
		JLabel labelTour = new JLabel("TOUR : 0");
		JLabel labelDeplacement = new JLabel("DEPLACEMENTS : 0");
		JLabel labelNbSourisEnCours = new JLabel("SOURIS EN DEPLACEMENTS : 0");
		JLabel labelNbSourisArrive = new JLabel("SOURIS ARRIVEES : 0");
		
		JButton buttonLancer = new JButton("Lancer");
		
		jpSud.add(labelTour);
		jpSud.add(labelDeplacement);
		jpSud.add(labelNbSourisEnCours);
		jpSud.add(labelNbSourisArrive);
		jpSud.add(buttonLancer);
		
		this.getContentPane().add(jpNord, BorderLayout.NORTH);
		this.getContentPane().add(jpSud, BorderLayout.SOUTH);
		
		setVisible(true);
	}
	
	public void repaintFrame(Graph graph_){
		
		this.graph = graph_;
//		this.invalidate();
//		this.validate();
//		this.repaint();
		jpNord.revalidate();
		jpNord.repaint();
		
	}
}
