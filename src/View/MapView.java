package View;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Model.Trainer;

public class MapView extends JPanel implements Observer {

	
	//instance variables
	private MapPanel mapPanel;
	private JLabel stepCount;
	//private TrainerPanel trainerPanel;
	//private PokedexPanel pokedexPanel;
	
	//ctor
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

	@Override
	public void update(Observable o, Object arg) {
		stepCount.setText("Steps Taken: " + mapPanel.getTrainer().getStepCount());
	}

	public Trainer getTrainer() {
		return mapPanel.getTrainer();
	}
}
