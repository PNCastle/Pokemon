package Controller;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import View.MapView;

public class PokemonGUI extends JFrame {

	//instance variables
	private static final int HEIGHT = 1000;
	private static final int WIDTH = 1000;
	private MapView mapView;
	private JPanel currentView;
	
	public static void main(String args[]){
		PokemonGUI g = new PokemonGUI();
		g.setVisible(true);
	}
	
	public PokemonGUI(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("Pokemon Safari Zone");
		this.setLocation(0, 0);
		mapView = new MapView(WIDTH, HEIGHT);
		setViewTo(mapView);
		setUpMenus();
		
	}

	//set up menus
	private void setUpMenus() {
		JMenuItem menu = new JMenu("Options");
		JMenuItem newGame = new JMenuItem("New Game");
		menu.add(newGame);
		JMenuItem saveGame = new JMenuItem("Save Game");
		menu.add(saveGame);
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
}
