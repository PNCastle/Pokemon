package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Model.Item;
import Model.Trainer;

import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim File:
 * Trainer.java Purpose: central object of pokemon safari zone. models a player
 * of the game who stores a reference of the map.
 */

public class TrainerPanel extends JPanel {
	private BufferedImage image;
	private Graphics2D g;

	JLabel trainerCard;
	JLabel name;
	JLabel pokemonNum;
	JLabel pokemonMeet;
	JLabel info;
	JLabel pokedex;

	JPanel scroll;
	
	JTable itemTable;
	JScrollPane scrollPane;
	TableModel model;

	private Trainer theTrainer;

	public TrainerPanel(Trainer theTrainer) {
		this.theTrainer = theTrainer;

		setLayout(null);
		setPreferredSize(new Dimension(750, 300));
		setBackground(Color.WHITE);

		try {
			image = ImageIO.read(new File("trainerLarge.png"));
		} catch (IOException e) {
			System.err.println("Could not read image file");
		}

		trainerCard = new JLabel("Trainer Card");
		trainerCard.setFont(new Font("Helvetica", Font.BOLD, 25));
		trainerCard.setSize(new Dimension(600, 30));
		trainerCard.setLocation(515, 20);
		this.add(trainerCard);
 
		model = new ItemTableModel();
		itemTable = new JTable(model);
		scrollPane = new JScrollPane(itemTable);
		scrollPane.setPreferredSize(new Dimension(550, 100));
		scrollPane.setLocation(200, 200);
		
		// List of Pokemons
		add(scrollPane);
		scrollPane.setEnabled(false);
		scrollPane.setLocation(0, 0);
		scrollPane.setSize(300, 300);
		
		name = new JLabel("Name : Ash");
		name.setFont(new Font("Herculanum", Font.BOLD, 20));
		name.setSize(new Dimension(600, 25));
		name.setLocation(490, 50);
		this.add(name);

		pokemonNum = new JLabel("Pokemon Num : " + (theTrainer.getPokedex().size() + 1));
		pokemonNum.setFont(new Font("Herculanum", Font.BOLD, 20));
		pokemonNum.setSize(new Dimension(600, 30));
		pokemonNum.setLocation(490, 70);
		this.add(pokemonNum);
		
		info = new JLabel("<html>Trainer INFO : <br> Ash was a U of A student,<br>majored in Pokemon training.<br>After graduation, he travels<br>all of the world to become<br>the greatest Pokemon<br>trainer.</html>");
		info.setFont(new Font("Herculanum", Font.BOLD, 18));
		info.setSize(new Dimension(600, 190));
		info.setLocation(495, 95);
		this.add(info);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(550, 100));
		panel.setLocation(200, 200);
		
	}

	private class ItemTableModel implements TableModel {

		ArrayList<Item> itemsList;

		public ItemTableModel() {
			itemsList = theTrainer.getItemsList();
		}
		
		@Override
		public int getRowCount() {
			return itemsList.size();
		}

		@Override
		public int getColumnCount() {
			return 3;
		}

		@Override
		public String getColumnName(int columnIndex) {
			if (columnIndex == 0) {
				return "Item Name";
			}
			if (columnIndex == 1) {
				return "Amount";
			}
			if (columnIndex == 2) {
				return "Is Throwable";
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
				return itemsList.get(rowIndex).getName();
			}
			if (columnIndex == 1) {
				if (itemsList.get(rowIndex).amount() == -1){
					return "" + '\u221e';
				}
				
				return itemsList.get(rowIndex).amount();
			}
			if (columnIndex == 2) {
				return itemsList.get(rowIndex).isThrowable();
			}

			return "";
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub

		}

		@Override
		public void addTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub

		}

		@Override
		public void removeTableModelListener(TableModelListener l) {
			// TODO Auto-generated method stub

		}

	}

	public void updateTrainer(Trainer newTrainer) {
		theTrainer = newTrainer;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 300, 0, 209, 300, this);
	}

}
