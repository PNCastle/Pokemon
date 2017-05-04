package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import Model.*;
import items.*;

/**
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim File:
 * Trainer.java Purpose: central object of pokemon safari zone. models a player
 * of the game who stores a reference of the map.
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

	JButton usePotion;
	
	JTable itemTable;
	JScrollPane scrollPane;
	TableModel model;

	int currentRow;
	
	Pokemon currentPokemon;
	private Trainer theTrainer;

	public PokedexPanel(Trainer thePokedex) {
		// Shows first Pokemon in Pokedex by default
		this.theTrainer = thePokedex;
		currentPokemon = thePokedex.getPokedex().get(0);
		currentRow = 0;
		
		currentPokemon.useItem(new Rock());
		currentPokemon.useItem(new Rock());
		currentPokemon.useItem(new Rock());
		currentPokemon.useItem(new Rock());
		currentPokemon.useItem(new Rock());
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(750, 300));
		setBackground(Color.WHITE);
		// poke info layout
		this.setLayout(null);

		try {
			image = ImageIO.read(new File("trainerLarge.png"));
		} catch (IOException e) {
			System.err.println("Could not read image file");
		}

		model = new ItemTableModel();
		itemTable = new JTable(model);
		scrollPane = new JScrollPane(itemTable);
		scrollPane.setPreferredSize(new Dimension(550, 100));
		scrollPane.setLocation(200, 200);

		itemTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				if (currentRow != itemTable.rowAtPoint(e.getPoint())){
					currentRow = itemTable.rowAtPoint(e.getPoint());
					currentPokemon = thePokedex.getPokedex().get(currentRow);
					pokemonInfo();
				}
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		// List of Pokemons
		add(scrollPane);
		scrollPane.setEnabled(false);
		scrollPane.setLocation(0, 0);
		scrollPane.setSize(300, 300);

		InputMap im = itemTable.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		im.put(KeyStroke.getKeyStroke("DOWN"), "none");
		im.put(KeyStroke.getKeyStroke("UP"), "none");
		im.put(KeyStroke.getKeyStroke("LEFT"), "none");
		im.put(KeyStroke.getKeyStroke("RIGHT"), "none");
		
		pokemonInfo();
	}

	private void pokemonInfo() {
		if (PokemonCard != null){
			this.remove(PokemonCard);
			this.remove(name);
			this.remove(pokemonID);
			this.remove(type);
			this.remove(hp);
			this.remove(info);
		}
		
		// Pic of Pokemons
		image = Toolkit.getDefaultToolkit()
				.createImage(currentPokemon.getPicFileName());

		// set up the pokemon Card
		PokemonCard = new JLabel("PokeDex");
		PokemonCard.setFont(new Font("Helvetica", Font.BOLD, 25));
		PokemonCard.setSize(new Dimension(500, 30));
		PokemonCard.setLocation(500, 10);
		this.add(PokemonCard);

		// set up the name;
		name = new JLabel("NAME : " + currentPokemon.getName());
		name.setFont(new Font("Helvetica", Font.BOLD, 20));
		name.setSize(new Dimension(600, 30));
		name.setLocation(530, 60);
		this.add(name);

		// set up the PokemonID;
		pokemonID = new JLabel("Pokemon ID : " + currentPokemon.getPokemonID());
		pokemonID.setFont(new Font("Helvetica", Font.BOLD, 20));
		pokemonID.setSize(new Dimension(600, 30));
		pokemonID.setLocation(530, 90);
		this.add(pokemonID);

		// set up the pokemon Type
		type = new JLabel("TYPE : " + currentPokemon.getType());
		type.setFont(new Font("Helvetica", Font.BOLD, 20));
		type.setSize(new Dimension(600, 30));
		type.setLocation(530, 120);
		this.add(type);

		// set up the HP
		hp = new JLabel("HP : " + currentPokemon.getHP());
		hp.setFont(new Font("Helvetica", Font.BOLD, 20));
		hp.setSize(new Dimension(600, 30));
		hp.setLocation(530, 150);
		this.add(hp);

		// set up the information
		info = new JLabel("<html>POKEDEX INFO : <br>" + currentPokemon.getInfo()
				+ "</html>");
		info.setFont(new Font("Helvetica", Font.BOLD, 18));
		info.setSize(new Dimension(600, 100));
		info.setLocation(310, 190);
		this.add(info);

		usePotion = new JButton("Use Potion");
		usePotion.setSize(new Dimension(110, 25));
		usePotion.setLocation(625, 15);
		usePotion.addActionListener(new PotionButtonListener());
		this.add(usePotion);
		
		setVisible(true);
		this.repaint();
	}

	private class PotionButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (new Potion().useOne()){
				currentPokemon.useItem(new Potion());
				pokemonInfo();
			}
		}
		
	}
	
	private class ItemTableModel implements TableModel {

		ArrayList<Pokemon> pokemonList;

		public ItemTableModel() {
			pokemonList = theTrainer.getPokedex();
		}

		@Override
		public int getRowCount() {
			return pokemonList.size();
		}

		@Override
		public int getColumnCount() {
			return 3;
		}

		@Override
		public String getColumnName(int columnIndex) {
			if (columnIndex == 0) {
				return "No";
			}
			if (columnIndex == 1) {
				return "Pokemon";
			}
			if (columnIndex == 2) {
				return "Pokemon ID";
			}

			return "";
		}

		@Override
		public Class<?> getColumnClass(int columnIndex) {
			return Object.class;
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			if (columnIndex == 0) {
				return rowIndex + 1;
			}
			if (columnIndex == 1) {
				return pokemonList.get(rowIndex).getName();

			}
			if (columnIndex == 2) {
				return pokemonList.get(rowIndex).getPokemonID();
			}

			return "";
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		}

		@Override
		public void addTableModelListener(TableModelListener l) {
		}
		
		@Override
		public void removeTableModelListener(TableModelListener l) {
		}
	}

	public void updateTrainer(Trainer newTrainer) {
		theTrainer = newTrainer;
	}

	// Pokemons Pic location and size
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 310, 40, 200, 150, this);
	}

}