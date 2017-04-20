//Authors: Angel Burr
//File: MapView.java
//Purpose: One of two views necessary for pokemon safari zone application
//		   This view displays the animated trainer and map, before any pokemon have been detected			
package View;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Trainer;

//this class models map view of the pokemon safari zone game
//this class observes the trainer class
public class MapView extends JPanel implements Observer {

	
	//instance variables
	private MapPanel mapPanel;
	private JLabel stepCount;
	//private TrainerPanel trainerPanel;
	//private PokedexPanel pokedexPanel;
	
	//ctor
	//initializes stepCount and mapPanel
	public MapView(int width, int height){
		stepCount = new JLabel("Steps Taken: " + 0);
		stepCount.setSize(200, 50);
		stepCount.setBackground(Color.BLACK);
		stepCount.setForeground(Color.cyan);
		stepCount.setLocation(775, 0);
		stepCount.setLayout(null);
		this.setSize(width, height);
		this.setLayout(null);
		this.setLocation(0, 0);
		this.setBackground(Color.DARK_GRAY);
		mapPanel = new MapPanel();
		mapPanel.setLocation(125, 50);
		this.add(mapPanel);
		this.add(stepCount);
	}
	
	//additional ctor used to allow persistent behavior
	public MapView(int width, int height, Object[] toLoad){
		stepCount = new JLabel("Steps Taken: " + toLoad[4]);
		stepCount.setSize(200, 50);
		stepCount.setBackground(Color.BLACK);
		stepCount.setForeground(Color.cyan);
		stepCount.setLocation(775, 0);
		stepCount.setLayout(null);
		this.setSize(width, height);
		this.setLayout(null);
		this.setLocation(0, 0);
		this.setBackground(Color.DARK_GRAY);
		mapPanel = new MapPanel(toLoad);
		mapPanel.setLocation(125, 50);
		this.add(mapPanel);
		this.add(stepCount);
	}

	//update method
	//for now this method only updates stepCount label when the traines has moved across a new tile
	@Override
	public void update(Observable o, Object arg) {
		stepCount.setText("Steps Taken: " + mapPanel.getTrainer().getStepCount());
	}

	//getter method for trainer
	public Trainer getTrainer() {
		return mapPanel.getTrainer();
	}
}
