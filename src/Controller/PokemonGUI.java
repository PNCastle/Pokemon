package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Model.Trainer;
//import View.BattleView;
import View.MapView;

public class PokemonGUI extends JFrame {

	//instance variables
	private static final int HEIGHT = 1000;
	private static final int WIDTH = 1000;
	private MapView mapView;
	//private BattleView battleView;
	private JPanel currentView;
	private Trainer theTrainer;
	
	public static void main(String args[]){
		PokemonGUI g = new PokemonGUI();
		g.setVisible(true);
	}
	
	public PokemonGUI(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("Pokemon Safari Zone");
		this.setLocation(0, 0);
		mapView = new MapView(WIDTH, HEIGHT);
		theTrainer = mapView.getTrainer();
		theTrainer.addObserver(mapView);
		setViewTo(mapView); //set default view to map view
		setUpMenus();
		
	}
	
	public PokemonGUI(Object[] toLoad){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("Pokemon Safari Zone");
		this.setLocation(0, 0);
		mapView = new MapView(WIDTH, HEIGHT, toLoad);
		theTrainer = mapView.getTrainer();
		theTrainer.addObserver(mapView);
		setViewTo(mapView); //set default view to map view
		setUpMenus();
		
	}

	//set up menus
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

	
	//helper method to change view from graphic to text
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
			
			if(text.equals("New Game")){
				//DO SOMETHING, MAYBE CALL mapPanel.init() ?
			}
			
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
				//SET Trainer.visible to TRUE
			}
			
			if(text.equals("Hide Trainer")){
				//set TrainerPanel.visible to False
			}
			
			if(text.equals("View Pokedex")){
				//set PokdexPanel.visible to true
			}
			
			if(text.equals("Hide Pokedex")){
				//set PokedexPanel.visible to false
			}
			
		}
		
	}
}
