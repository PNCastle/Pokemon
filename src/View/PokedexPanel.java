package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Model.*;
import pokemon.Abra;
import pokemon.Dragonair;
import pokemon.Drowzee;
import pokemon.Graveler;
import pokemon.Grimer;
import pokemon.Haunter;
import pokemon.Pidgey;
import pokemon.Pikachu;
import pokemon.Rapidash;
import pokemon.Staryu;

/**
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Trainer.java
 * Purpose: central object of pokemon safari zone. models a player of the game who stores a reference of the map.
 */

public class PokedexPanel extends JPanel {
	private Image image;
	private Graphics2D g;
	
	JLabel PokemonCard;
	JLabel name;
	JLabel pokemonID;
	JLabel type;
	JLabel hp;
	JLabel info;
	
	Pokemon currentPokemon;
	
	public PokedexPanel() {
		currentPokemon = new Pikachu(25);
		
		setLayout(new GridLayout(1, 0, 0, 0));
		setPreferredSize(new Dimension(750, 300));
		setBackground(Color.WHITE);
		this.setLayout(null);
		
		JTextArea pokeListArea = new JTextArea();
		JScrollPane pokesList = new JScrollPane(pokeListArea);
		
		// List of Pokemons
		add(pokesList);
		pokesList.setLocation(0, 0);
		pokesList.setSize(300, 300);
		
		// Pic of Pokemons
		image = Toolkit.getDefaultToolkit().createImage("./pokePic/Abra.gif");
		
		// set up the pokemon Card
		PokemonCard = new JLabel("PokeDex");
		PokemonCard.setFont(new Font("Helvetica", Font.BOLD, 25));
		PokemonCard.setSize(new Dimension(500, 30));
		PokemonCard.setLocation(500, 10);
		this.add(PokemonCard);
		
		// set up the name;
		name = new JLabel(currentPokemon.getName());
		name.setFont(new Font("Helvetica", Font.BOLD, 20));
		name.setSize(new Dimension(600, 30));
		name.setLocation(550, 50);
		this.add(name);
		
		// set up the PokemonID;
		pokemonID = new JLabel ("( #" + currentPokemon.getPokemonID() +" )");
		pokemonID.setFont(new Font("Helvetica", Font.BOLD, 20));
		pokemonID.setSize(new Dimension(600, 30));
		pokemonID.setLocation(650, 50);
		this.add(pokemonID);
		
		// set up the HP
		
		
		// set up the information
		
		
		
		setVisible(true);
	}
	
	// Pokemons Pic location and size
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 310, 50, 200, 150, this);
    }
	
}