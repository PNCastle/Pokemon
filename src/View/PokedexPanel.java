package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Trainer.java
 * Purpose: central object of pokemon safari zone. models a player of the game who stores a reference of the map.
 */

public class PokedexPanel extends JPanel {
	private BufferedImage image;
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
		setVisible(true);
	}
	
	//draw the graphics
	private void draw(){
		Graphics g2 = getGraphics();
		if (g2 != null){
			g2.drawImage(image, 0, 0, null);
			g2.dispose();
		}
	}
	
}