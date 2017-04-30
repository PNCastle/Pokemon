//Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
//File: PokemonGUI.java
//Purpose: Main controller in Pokemon Safari Zone application

package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Model.Pokemon;
import Model.Trainer;
//import View.BattleView;
import View.MapView;


//pokemon class that extends JFrame
public class PokemonGUI extends JFrame implements Observer {

	//instance variables
	private static final int HEIGHT = 1000;
	private static final int WIDTH = 1000;
	private MapView mapView;
	//private BattleView battleView;
	private JPanel currentView;
	private Trainer theTrainer;
	
	//simple main method
	public static void main(String args[]){
		PokemonGUI g = new PokemonGUI();
		g.setVisible(true);
	}
	
	//ctor
	public PokemonGUI(){
		//set standard information such as size, title etc.
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("Pokemon Safari Zone");
		this.setLocation(0, 0);
		
		//this section of code makes the system persistant, so that we can load and save games
		File file = new File("persistence");

		if (file.exists()){
			int ret = JOptionPane.showOptionDialog(null, "Start with previous game?\n"
					+ "No means start with a new game.\nIf no, previous game can be loaded from menu.", 
					"Select an Option", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

			//try to read the file
			if (ret == JOptionPane.YES_OPTION){
				try {
					FileInputStream fis = new FileInputStream(file);
					try {
						ObjectInputStream ois = new ObjectInputStream(fis);
						try {
							Object[] toLoad = (Object[]) ois.readObject();
							mapView = new MapView(WIDTH, HEIGHT, toLoad);
						} catch (ClassNotFoundException e) {
							System.err.println("Could not read persistence file");
						}
						ois.close();
					} catch (IOException e) {
						System.err.println("Could not read persistence file");
					}
				} catch (FileNotFoundException e1) {
					System.err.println("Could not read persistence file");
				}
			}
			else {
				mapView = new MapView(WIDTH, HEIGHT);
			}
		}
		else {
			mapView = new MapView(WIDTH, HEIGHT);
		}
		
		//add mapView as an observer of the trainer
		theTrainer = mapView.getTrainer();
		theTrainer.addObserver(mapView);
		theTrainer.addObserver(this);
		setViewTo(mapView); //set default view to map view
		setUpMenus(); //build menu system
		
	}
	
	//additional constructor used in load game
	public PokemonGUI(Object[] toLoad){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("Pokemon Safari Zone");
		this.setLocation(0, 0);
		//initialize mapView and add as an observer of trainer
		mapView = new MapView(WIDTH, HEIGHT, toLoad);
		theTrainer = mapView.getTrainer();
		theTrainer.addObserver(mapView);
		setViewTo(mapView); //set default view to map view
		setUpMenus();
		
	}

	//private helper method whose purpose is to build the menu system
	//this menu system contains features such as: new game, save game, load game, and others
	private void setUpMenus() {
		JMenuItem menu = new JMenu("Options");
		JMenuItem newGame = new JMenuItem("New Game");
		menu.add(newGame);
		JMenuItem saveGame = new JMenuItem("Save Game");
		menu.add(saveGame);
		JMenuItem loadGame = new JMenuItem("Load Game");
		menu.add(loadGame);
		JMenuItem trainer = new JMenu("Trainer");
		menu.add(trainer);
		JMenuItem pokedex = new JMenu("Pokedex");
		menu.add(pokedex);
		JMenuItem viewTrainer = new JMenuItem("View Trainer");
		JMenuItem hideTrainer = new JMenuItem("Hide Trainer");
		trainer.add(viewTrainer);
		trainer.add(hideTrainer);
		JMenuItem viewPokedex = new JMenuItem("View Pokedex");
		JMenuItem hidePokedex = new JMenuItem("Hide Pokedex");
		pokedex.add(viewPokedex);
		pokedex.add(hidePokedex);
		
		//set up menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(menu);
		
		//add listeners
		MenuItemListener menuListener = new MenuItemListener();
		menu.addActionListener(menuListener);
		newGame.addActionListener(menuListener);
		saveGame.addActionListener(menuListener);
		loadGame.addActionListener(menuListener);
		trainer.addActionListener(menuListener);
		pokedex.addActionListener(menuListener);
		viewTrainer.addActionListener(menuListener);
		hideTrainer.addActionListener(menuListener);
		viewPokedex.addActionListener(menuListener);
		hidePokedex.addActionListener(menuListener);
	}

	
	//helper method to change views
	private void setViewTo(JPanel newView) {
		if (currentView != null){
			remove(currentView);
		}
		currentView = newView;
		add(currentView);
		currentView.repaint();
		validate();
	}
	
	
	////// MENU ITEM LISTENER ////////////////
	private class MenuItemListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String text = ((JMenuItem) e.getSource()).getText();
			
			//if new game is selected build a new instance of the GUI and dispose of the old one
			if(text.equals("New Game")){
				PokemonGUI g = new PokemonGUI();
				g.setVisible(true);
				setVisible(false);
				dispose();
			}
			
			//if save game is selected then write the current state of the system to file
			if(text.equals("Save Game")){
				try {
					FileOutputStream fos = new FileOutputStream("persistence");
					try {
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						oos.writeObject(mapView.getTrainer().toSerialize());
						oos.close();
					} catch (IOException e1) {
						System.err.println("Can't write to file");
						e1.printStackTrace();
					}
				} catch (FileNotFoundException e1) {
					System.err.println("Can't write to file");
					e1.printStackTrace();
				}
			}
			
			//if load game is selected the construct a new GUI object from the system information that was saved
			if (text.equals("Load Game")){
				try {
					FileInputStream fis = new FileInputStream("persistence");
					try {
						ObjectInputStream ois = new ObjectInputStream(fis);
						try {
							Object[] toLoad = (Object[]) ois.readObject();
							PokemonGUI g = new PokemonGUI(toLoad);
							g.setVisible(true);
							setVisible(false);
							dispose();
						} catch (ClassNotFoundException cnfe) {
							System.err.println("Could not read persistence file");
						}
						ois.close();
					} catch (IOException ioe) {
						System.err.println("Could not read persistence file");
					}
				} catch (FileNotFoundException e1) {
					System.err.println("Could not read persistence file");
				}
			}
			
			if(text.equals("View Trainer")){
				//TO DO
			}
			
			if(text.equals("Hide Trainer")){
				//TO DO
			}
			
			if(text.equals("View Pokedex")){
				Trainer theTrainer = mapView.getTrainer();
				ArrayList<Pokemon> pokedex = theTrainer.getPokedex();
				Pokemon first = pokedex.get(0);
				JOptionPane.showMessageDialog(null, "Pokedex (Placeholder for "
						+ "this iteration)\n1. " + first.getName() + " - " +
						"HP: " + first.getHP() + " ID: " + first.getPokemonID());
			}
			
			if(text.equals("Hide Pokedex")){
				//TO DO
			}
			
		}
		
	}


	@Override
	public void update(Observable o, Object arg) {
		if((int) arg < 0){
			//change view to battle view
		}
		//otherwise ignore
	}
}
