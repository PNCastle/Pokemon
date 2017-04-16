package View;

import java.awt.Color;

import javax.swing.JPanel;

public class MapView extends JPanel {

	
	//instance variables
	private MapPanel mapPanel;
	private TrainerPanel trainerPanel;
	private PokedexPanel pokedexPanel;
	
	//ctor
	public MapView(int width, int height){
		this.setSize(width, height);
		this.setLayout(null);
		this.setLocation(0, 0);
		this.setBackground(Color.cyan);
		this.setVisible(true);
		mapPanel = new MapPanel();
		mapPanel.setLocation(125, 225);
		this.add(mapPanel);
	}
}
