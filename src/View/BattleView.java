package View;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import Model.Map;
import Model.Pokemon;
import Model.Trainer;
import javafx.application.Platform;
import songplayer.AudioFilePlayer;

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
			battlePanel.setToSpawn(theTrainer.getCurrentPokemonID());
			battlePanel.makePokemon();
		}
		
	}
	
	private void setButtonsClickable(boolean canClick) {
		this.ballButton.setEnabled(canClick);
		this.baitButton.setEnabled(canClick);
		this.rockButton.setEnabled(canClick);
		this.runButton.setEnabled(canClick);
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
		
		private boolean aerialAniDone = true;
		
		Timer aerialTimer = new Timer(350, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				aerialAniDone = false;
				count2++;
				battlePanel.throwAerial();
				repaint();
				{
					if(count2 == 5){
						((Timer) e.getSource()).stop();
						count2 = 0;
						battlePanel.doneThrowingAerial();
						aerialAniDone = true;						
					}
				}
			}
			
		});

		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton buttonClicked = (JButton) arg0.getSource();
			
			Pokemon currentPokemon = theTrainer.getCurrentPokemon();
			
			ButtonsOffThread buttonsRunner = new ButtonsOffThread();
			Thread buttonWaiter = new Thread(buttonsRunner);
			
			CatchAnimThread animThread = new CatchAnimThread();
			Thread animWaiter = new Thread(animThread);
			
			
			if (buttonClicked.getText().equals("Rock")) {
				buttonWaiter.start();
				Timer rockTimer = makeTimer("Rock");
				rockTimer.start();
				aerialTimer.start();
 				theTrainer.getCurrentPokemon().useItem(theTrainer.getItemsList().get(1));
			} 
			
			if (buttonClicked.getText().equals("Bait")) {
				buttonWaiter.start();
				Timer baitTimer = makeTimer("Bait");
				baitTimer.start();
				aerialTimer.start();
 				theTrainer.getCurrentPokemon().useItem(theTrainer.getItemsList().get(2));
			}
			
			if (buttonClicked.getText().equals("Pokeball")) {
				buttonWaiter.start();
				Timer ballTimer = makeTimer("Pokeball");
				ballTimer.start();
				aerialTimer.start();
				
				while(!aerialAniDone) {
				}
								
				theTrainer.getCurrentPokemon().useItem(theTrainer.getItemsList().get(0));
				
				//int pokemonCatchRate = (int)
				Random random = new Random();
				int theRand = random.nextInt(450);
				double maybeCatch = theRand / 1000.0;
				double catchProb = currentPokemon.getCatchProbability();
				System.out.println("RNG = " + maybeCatch + " CatchProb = " + catchProb);
				if (maybeCatch <= catchProb) {
					theTrainer.getPokedex().add(currentPokemon);
				//	
					//pokemon into pokeball animation
					//theTrainer.ran();
					System.out.println(theTrainer.getPokedex().get(1));
					animWaiter.start();
				}
				//setButtonsClickable(aerialAniDone);
				theTrainer.throwSafariBall();
			}
			
			if (buttonClicked.getText().equals("Run")) {
				theTrainer.ran();
			}
			
		}		
	}
	
	private class CatchAnimThread implements Runnable {
		
		@Override
		public void run() {
			try {
				 synchronized(this) {
					 this.wait(2000);
				 }
	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			theTrainer.ran();
		}
	}
	
	private class ButtonsOffThread implements Runnable {

		@Override
		public void run() {
			setButtonsClickable(false);
			try {
				 synchronized(this) {
					 this.wait(2000);
				 }
	
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setButtonsClickable(true);
		}
		
	}

}
