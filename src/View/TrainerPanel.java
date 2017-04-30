package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Trainer.java
 * Purpose: central object of pokemon safari zone. models a player of the game who stores a reference of the map.
 */

public class TrainerPanel extends JPanel {
	private BufferedImage image;
	private Graphics2D g;
	
	JLabel trainerCard;
	JLabel name;
	JLabel pokedex;
	
	public TrainerPanel() {
		setLayout(null);
		setPreferredSize(new Dimension(750, 300));
		setBackground(Color.WHITE);
		
		try {
			image = ImageIO.read(new File("trainerLarge.png"));
		} catch (IOException e) {
			System.err.println("Could not read image file");
		}
		
		trainerCard = new JLabel("Trainer Card");
		trainerCard.setFont(new Font("Helvetica", Font.BOLD, 30));
		trainerCard.setSize(new Dimension(600, 30));
		trainerCard.setLocation(415, 20);
		this.add(trainerCard);
		
		name = new JLabel("Name                                 Ash");
		name.setFont(new Font("Herculanum", Font.BOLD, 28));
		name.setSize(new Dimension(600, 30));
		name.setLocation(300, 60);
		this.add(name);
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 6, 200, 287, this);
    }
	
}
