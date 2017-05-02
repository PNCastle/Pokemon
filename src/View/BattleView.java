package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import Model.Map;
import Model.Trainer;

public class BattleView extends JPanel implements Observer {
	
	private Trainer theTrainer;
	
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
		
		ballButton.setSize(100, 50);
		rockButton.setSize(100, 50);
		baitButton.setSize(100, 50);
		runButton.setSize(100, 50);
		
		baitButton.setLocation(325, 600);
		ballButton.setLocation(425, 600);
		rockButton.setLocation(325, 650);
		runButton.setLocation(425, 650);
		
		baitButton.setVisible(true);
		rockButton.setVisible(true);
		ballButton.setVisible(true);
		runButton.setVisible(true);
		
		baitButton.addActionListener(new ButtonListener());
		rockButton.addActionListener(new ButtonListener());
		ballButton.addActionListener(new ButtonListener());
		runButton.addActionListener(new ButtonListener());
		
		
		this.add(baitButton);
		this.add(ballButton);
		this.add(rockButton);
		this.add(runButton);
		
	//	update(theTrainer, 0);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		int anInt = (int) arg;
		if (theTrainer == null) {
			theTrainer = (Trainer) o;
		}
		if (anInt == -1) {
			battlePanel.setToSpawn(theTrainer.getCurrentPokemon());
			battlePanel.makePokemon();
		}
		
	}
	
	
	private class ButtonListener implements ActionListener {
		

		
//		Timer timer = new Timer(flags, null);
		int count = 0; //counter for player sprite
		int count2 = 0; //counter for aerial object sprite
		
		private Timer makeTimer(String str){
			return new Timer(275, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					count++;
					battlePanel.throwObject(str);
					repaint();
					{
						if(count == 5) {
							((Timer) e.getSource()).stop();
							count = 0;
							battlePanel.doneThrowing();
						}
					}
				}
			});
		}
		
		Timer aerialTimer = new Timer(350, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				count2++;
				battlePanel.throwAerial();
				repaint();
				{
					if(count2 == 5){
						((Timer) e.getSource()).stop();
						count2 = 0;
						battlePanel.doneThrowingAerial();
					}
				}
			}
		});

		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton buttonClicked = (JButton) arg0.getSource();
			
			
			if (buttonClicked.getText().equals("Rock")) {
				Timer rockTimer = makeTimer("Rock");
				rockTimer.start();
				aerialTimer.start();
			} 
			if (buttonClicked.getText().equals("Bait")) {
				Timer baitTimer = makeTimer("Bait");
				baitTimer.start();
				aerialTimer.start();
			}
			if (buttonClicked.getText().equals("Pokeball")) {
				Timer ballTimer = makeTimer("Pokeball");
				ballTimer.start();
				aerialTimer.start();
				theTrainer.throwSafariBall();
			}
			if (buttonClicked.getText().equals("Run")) {
				theTrainer.ran();
			}
			
		}		
	}

}
