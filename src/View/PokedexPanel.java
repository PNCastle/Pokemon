package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Trainer.java
 * Purpose: central object of pokemon safari zone. models a player of the game who stores a reference of the map.
 */

public class PokedexPanel extends JPanel {
	private Image image;
	private Graphics2D g;
	
	public PokedexPanel() {
		setLayout(new GridLayout(1, 0, 0, 0));
		setPreferredSize(new Dimension(750, 300));
		setBackground(Color.WHITE);
		this.setLayout(null);
		
		JTextArea textArea = new JTextArea();
		JScrollPane pokesList = new JScrollPane(textArea);
		
		// List of Pokemons
		add(pokesList);
		pokesList.setLocation(0, 0);
		pokesList.setSize(300, 300);
		
		image = Toolkit.getDefaultToolkit().createImage("./pokePic/Abra.gif");
		
		// Pic of Pokemon
///*		try {
//			image = ImageIO.read(getClass().getResource("./pokePic/Abra.gif"));
//		} catch (IOException e) {
//			System.err.println("Could not read image file");
//		}*/
//		
//		ImageIcon imageIcon = new ImageIcon("./pokePic/Abra.gif");
//		JLabel label = new JLabel(imageIcon);
//		label.setPreferredSize(new Dimension(300, 200));
//		label.setLocation(300, 0);
//		this.add(label);
		
		setVisible(true);
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 300, 0, this);
    }
	
}