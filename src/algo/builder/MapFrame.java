package algo.builder;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;

import algo.graph.Node;

public class MapFrame extends JFrame {
	
	private JPanel jpNord;
	private JPanel jpSud;
	private MapBuilder mb;
	private MapFrame mf;
	
	public MapFrame(MapBuilder mb_) {
		
		mb = mb_;
		mf = this;
		
		setTitle("Simulateur de foule");
		setSize(mb.nbcol * 26, mb.nbligne * 26 + 80);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		jpSud = new JPanel();
		jpSud.setBackground(new java.awt.Color(201, 128, 55));
		
		//this.affichageMap();
		
		jpNord = new JPanel();
		jpNord.setBackground(new java.awt.Color(201, 128, 55));
		
		GridLayout g = new GridLayout(mb.nbligne, mb.nbcol);
		jpNord.setLayout(g);
		for (int i = 0; i < mb.nbligne; i++) {
			for (int j = 0; j < mb.nbcol; j++) {
				Node node = mb.graph.getNode(j, i);
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
						System.out.println("Souris trouv� = " + i + " - " + j);
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
		
		JLabel labelTour = new JLabel("TOUR : 0");
		JLabel labelDeplacement = new JLabel("DEPLACEMENTS : 0");
		JLabel labelNbSourisEnCours = new JLabel("SOURIS EN DEPLACEMENTS : 0");
		JLabel labelNbSourisArrive = new JLabel("SOURIS ARRIVEES : 0");
		
		final JButton buttonLancer = new JButton("Lancer");
		
		buttonLancer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mb.deplacerSouris(mf);
			}
		});
		
		jpSud.add(labelTour);
		jpSud.add(labelDeplacement);
		jpSud.add(labelNbSourisEnCours);
		jpSud.add(labelNbSourisArrive);
		jpSud.add(buttonLancer);
		
		this.getContentPane().add(jpSud, BorderLayout.SOUTH);
		
		this.getContentPane().setBackground(new java.awt.Color(201, 128, 55));
		setVisible(true);
	}
	
	public void repaintFrame(int x, int y){
		
		//mb.graph.getNode(x,y).setId("S");
		
		System.out.println("repaint");
		
		this.affichageMap();
		this.validate();
		this.repaint();
		
		//mb.graph.getNode(x, y).setId(mb.graph.getNode(x,y).getIdOrigine());
		
	}
	
	public void affichageMap(){
		
		if(jpNord != null){
			this.getContentPane().remove(jpNord);
		}
		
		jpNord = new JPanel();
		jpNord.setBackground(new java.awt.Color(201, 128, 55));
		
		GridLayout g = new GridLayout(mb.nbligne, mb.nbcol);
		jpNord.setLayout(g);
		for (int i = 0; i < mb.nbligne; i++) {
			for (int j = 0; j < mb.nbcol; j++) {
				Node node = mb.graph.getNode(j, i);
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
						System.out.println("Souris trouv� = " + i + " - " + j);
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
	
	public MapBuilder getMb() {
		return mb;
	}

	public void setMb(MapBuilder mb) {
		this.mb = mb;
	}
	
}
