package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import Model.Pokemon;

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