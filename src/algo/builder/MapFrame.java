package algo.builder;

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

	public MapFrame(int nbcol, int nbligne, Graph graph) {

		setTitle("Simulateur de foule");
		setSize(nbcol * 26, nbligne * 26);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel jp = new JPanel();
		System.out.println(nbcol);
		System.out.println(nbligne);
		GridLayout g = new GridLayout(nbligne, nbcol);
		jp.setLayout(g);
		for (int i = 0; i < nbligne; i++) {
			for (int j = 0; j < nbcol; j++) {
				Node node = graph.getNode(j, i);
				if (node != null) {
					if (node.getId().equals(" ")) {
						JLabel picLabel = new JLabel(new ImageIcon(
								Images.espace));
						jp.add(picLabel);
					} else if (node.getId().equals("D")) {

						JLabel picLabel = new JLabel(new ImageIcon(
								Images.depart));
						jp.add(picLabel);
					} else if (node.getId().equals("A")) {

						JLabel picLabel = new JLabel(new ImageIcon(
								Images.fromage));
						jp.add(picLabel);
					} else if (node.getId().equals("G")) {
						JLabel picLabel = new JLabel(new ImageIcon(
								Images.buisson));
						jp.add(picLabel);
					} else if (node.getId().equals("S")) {
						JLabel picLabel = new JLabel(new ImageIcon(
								Images.souris));
						jp.add(picLabel);
					}
				} else {
					JLabel picLabel = new JLabel(new ImageIcon(Images.mur));
					jp.add(picLabel);

				}
			}
		}

		this.getContentPane().add(jp);

		setVisible(true);
	}
}
