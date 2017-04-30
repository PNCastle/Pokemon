package View;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BattleView extends JPanel implements Observer {

	
	private BattlePanel battlePanel;
	private JButton ballButton;
	private JButton rockButton;
	private JButton baitButton;
	private JButton runButton;
	
	public BattleView(int width, int height) {
		//basic set up
		this.setLayout(null);
		this.setSize(width, height);
		this.setLocation(0, 0);
		this.setBackground(Color.DARK_GRAY);
		//initialize battlePanel
		battlePanel = new BattlePanel();
		battlePanel.setLocation(125, 50);
		this.add(battlePanel);
		//initialize and place buttons
		ballButton = new JButton("Pokeball");
		rockButton = new JButton("Rock");
		baitButton = new JButton("Bait");
		runButton = new JButton("Run");
		
		ballButton.setSize(375, 150);
		rockButton.setSize(375, 150);
		baitButton.setSize(375, 150);
		runButton.setSize(375, 150);
		
		baitButton.setLocation(125, 700);
		ballButton.setLocation(500, 700);
		rockButton.setLocation(125, 850);
		runButton.setLocation(500, 850);
		
		baitButton.setVisible(true);
		rockButton.setVisible(true);
		ballButton.setVisible(true);
		runButton.setVisible(true);
		
		this.add(baitButton);
		this.add(ballButton);
		this.add(rockButton);
		this.add(runButton);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		
	}

}
