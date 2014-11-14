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

public class MapFrame extends JFrame{
	public MapFrame(int nbcol, int nbligne, Graph graph){


		setTitle("Simulateur de foule");
		setSize(nbcol*26,nbligne*26);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel jp = new JPanel();
		System.out.println(nbcol);
		System.out.println(nbligne);
		GridLayout g = new GridLayout(nbligne, nbcol);
		jp.setLayout(g);
		for(int i = 0; i < nbligne; i++){
			for(int j = 0; j < nbcol; j++){
				Node node =graph.getNode(j, i);
				if(node != null){
					if(node.getId().equals(" ")){
						BufferedImage myPicture;
						try {
							myPicture = ImageIO.read(new File("C:\\wamp\\www\\ProjetAlgo\\src\\algo\\builder\\espace.png"));
							JLabel picLabel = new JLabel(new ImageIcon(myPicture));
							jp.add(picLabel);	
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}				
					} else if(node.getId().equals("D")){
						BufferedImage myPicture;
						try {
							myPicture = ImageIO.read(new File("C:\\wamp\\www\\ProjetAlgo\\src\\algo\\builder\\d.png"));
							JLabel picLabel = new JLabel(new ImageIcon(myPicture));
							jp.add(picLabel);	
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}				
					} else if(node.getId().equals("A")){
						BufferedImage myPicture;
						try {
							myPicture = ImageIO.read(new File("C:\\wamp\\www\\ProjetAlgo\\src\\algo\\builder\\a.png"));
							JLabel picLabel = new JLabel(new ImageIcon(myPicture));
							jp.add(picLabel);	
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}				
					} else if(node.getId().equals("G")){
						BufferedImage myPicture;
						try {
							myPicture = ImageIO.read(new File("C:\\wamp\\www\\ProjetAlgo\\src\\algo\\builder\\buisson.png"));
							JLabel picLabel = new JLabel(new ImageIcon(myPicture));
							jp.add(picLabel);	
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}				
					}
				} else {
					BufferedImage myPicture;
					try {
						myPicture = ImageIO.read(new File("C:\\wamp\\www\\ProjetAlgo\\src\\algo\\builder\\etoile.png"));
						JLabel picLabel = new JLabel(new ImageIcon(myPicture));
						jp.add(picLabel);	
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		this.getContentPane().add(jp);
		
		setVisible(true);
	} 
}
