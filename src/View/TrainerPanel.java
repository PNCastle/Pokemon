package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;

/**
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Trainer.java
 * Purpose: central object of pokemon safari zone. models a player of the game who stores a reference of the map.
 */

public class TrainerPanel extends JPanel {
	private BufferedImage image;
	private Graphics2D g;
	
	public TrainerPanel() {
		setLayout(new GridLayout(1, 0, 0, 0));
		setPreferredSize(new Dimension(750, 300));
		setBackground(Color.WHITE);
		
		JPanel panel = new JPanel(new BorderLayout());
		JTextArea textArea = new JTextArea();
		JScrollPane pokesList = new JScrollPane(textArea);
		
		panel.add(pokesList);
		add(panel);
		setSize(300, 300);
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
