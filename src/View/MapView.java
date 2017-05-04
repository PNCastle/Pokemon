//Authors: Angel Burr
//File: MapView.java
//Purpose: One of two views necessary for pokemon safari zone application
//		   This view displays the animated trainer and map, before any pokemon have been detected			
package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import Controller.PokemonGUI;
import Model.Trainer;

//this class models map view of the pokemon safari zone game
//this class observes the trainer class
public class MapView extends JPanel implements Observer {

	
	//instance variables
	private MapPanel mapPanel;
	private JLabel stepCount;
	private TrainerPanel trainerPanel;
	private PokedexPanel pokedexPanel;
	
	private boolean inBattle; //lets us know if currently in battle
	
	//ctor
	//initializes stepCount and mapPanel
	public MapView(int width, int height, String map){
		stepCount = new JLabel("Steps Taken: " + 0);
		stepCount.setSize(200, 50);
		stepCount.setBackground(Color.BLACK);
		stepCount.setForeground(Color.cyan);
		stepCount.setLocation(775, 0);
		this.setSize(width, height);
		this.setLocation(0, 0);
		this.setBackground(Color.DARK_GRAY);
		mapPanel = new MapPanel(map);
		mapPanel.setLocation(125, 50);
		
		trainerPanel = new TrainerPanel(getTrainer());
		trainerPanel.setLocation(125, 700);
		trainerPanel.setVisible(false);
		
		pokedexPanel = new PokedexPanel(getTrainer());
		pokedexPanel.setLocation(125, 700);
		pokedexPanel.setVisible(false);
		
		
		this.add(mapPanel, BorderLayout.NORTH);
		this.add(trainerPanel, BorderLayout.PAGE_END);
		this.add(pokedexPanel, BorderLayout.PAGE_END);
		
		inBattle = false;
	}
	
	//additional ctor used to allow persistent behavior
	public MapView(int width, int height, Object[] toLoad){
		stepCount = new JLabel("Steps Taken: " + toLoad[4]);
		stepCount.setSize(200, 50);
		stepCount.setBackground(Color.BLACK);
		stepCount.setForeground(Color.cyan);
		stepCount.setLocation(775, 0);
		this.setSize(width, height);
		this.setLocation(0, 0);
		this.setBackground(Color.DARK_GRAY);
		mapPanel = new MapPanel("mapTwo.txt", toLoad);
		mapPanel.setLocation(125, 50);
		
		trainerPanel = new TrainerPanel(getTrainer());
		trainerPanel.setLocation(125, 700);
		trainerPanel.setVisible(false);
		pokedexPanel = new PokedexPanel(getTrainer());
		pokedexPanel.setLocation(125, 700);
		pokedexPanel.setVisible(false);
		
		this.add(mapPanel, BorderLayout.NORTH);
		this.add(trainerPanel, BorderLayout.PAGE_END);
		this.add(pokedexPanel, BorderLayout.PAGE_END);
		
		inBattle = false;
	}
	
	//transition animation that runs when we go from mapView to battleView
	public void animateOut(PokemonGUI gui, BattleView battleView) {
		mapPanel.setLocation(125, 0);
		
		if (!inBattle){
			mapPanel.battleMode();
			
			new Timer(1, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mapPanel.setLocation(mapPanel.getX() - 2, 50);
					if (mapPanel.getX() + mapPanel.getWidth() <= 0) {
						((Timer) e.getSource()).stop();
						gui.setViewTo(battleView);
					}
				}
			}).start();
			
			inBattle = true;
			this.repaint();
		}
	}
	
	//enables the map panel
	public void enableMapPanel() {
		mapPanel.mapMode();
		mapPanel.setEnabled(true);
		inBattle = false;
	}
	
	//getter for inBattle boolean
	public boolean inBattle() {
		return inBattle;
	}
	
	// 0 is show trainer, 1 is hide trainer
	// 2 is show pokedex, 3 is hide pokedex
	public void setSecondaryView(int type){
		trainerPanel.updateTrainer(getTrainer());
		pokedexPanel.updateTrainer(getTrainer());
		
		if (type == 0){
			if (!trainerPanel.isVisible()){
				if (pokedexPanel.isVisible()){
					pokedexPanel.setVisible(false);
				}
				trainerPanel.repaint();
				trainerPanel.setVisible(true);
			}
		}
		if (type == 1){
			if (trainerPanel.isVisible()){
				trainerPanel.setVisible(false);
			}
		}
		if (type == 2) {
			if (!pokedexPanel.isVisible()){
				if (trainerPanel.isVisible()){
					trainerPanel.setVisible(false);
				}
				pokedexPanel.repaint();
				pokedexPanel.setVisible(true);
			}
		}
		if (type == 3) {
			if (pokedexPanel.isVisible()){
				pokedexPanel.setVisible(false);
			}
		}
	}
	
	//update method
	//for now this method only updates stepCount label when the traines has moved across a new tile
	@Override
	public void update(Observable o, Object arg) {
		stepCount.setText("Steps Taken: " + mapPanel.getTrainer().getStepCount());
		//pokedexPanel.updatePokedex(getTrainer().getPokedex());
	}

	//getter method for trainer
	public Trainer getTrainer() {
		return mapPanel.getTrainer();
	}

	//sets the win condition variable in map panel when the player selects from JOption Pane
	public void setWinCondition(String string) {
		mapPanel.setWinCondition(string);
	}
}
