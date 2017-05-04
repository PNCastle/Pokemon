/*
 * Authors:  Paul Castleberry, Angel Burr, Sohyun Kim, Isaac Kim
 * Filename: BattleView.java
 * Purpose:  The view of battles held inside the GUI. Contains all the
 * 			 buttons and a text area with battle updates along with the 
 * 		     battlePanel with all the graphics 			 
 */

package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

import Model.Map;
import Model.Pokemon;
import Model.Trainer;
import javafx.application.Platform;
import songplayer.AudioFilePlayer;

public class BattleView extends JPanel implements Observer {
	
	private Trainer theTrainer;
	
	private BattlePanel battlePanel;
	private JTextArea battleInfo;
	private JButton ballButton;
	private JButton rockButton;
	private JButton baitButton;
	private JButton runButton;
	
	//ctor
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
		battleInfo = new JTextArea();
		
		battleInfo.setSize(550, 100);
		battleInfo.setLocation(325, 600);
		battleInfo.setText("temp");
		battleInfo.setVisible(true);
		battleInfo.setFont(new Font("Consolas", Font.BOLD, 32));
		
		ballButton.setSize(100, 50);
		rockButton.setSize(100, 50);
		baitButton.setSize(100, 50);
		runButton.setSize(100, 50);
		
		baitButton.setLocation(125, 600);
		ballButton.setLocation(225, 600);
		rockButton.setLocation(125, 650);
		runButton.setLocation(225, 650);
		
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
		this.add(battleInfo);
		
		
	}

	// -1 tells this observer that its time to initiate battle view and draw the pokemon
	// image
	@Override
	public void update(Observable o, Object arg) {
		int anInt = (int) arg;
		if (theTrainer == null) {
			theTrainer = (Trainer) o;
		}
		if (anInt == -1) {
			setButtonsClickable(true);
			battlePanel.setToSpawn(theTrainer.getCurrentPokemonID());
			battlePanel.setPokemon(theTrainer.getCurrentPokemon());
			battlePanel.makePokemon();
			battleInfo.setText("\n  A wild " + theTrainer.getCurrentPokemon().getName() 
								+ " appeared!");
		}
		
	}
	
	// a helper method to disable all buttons at once during throw animation
	private void setButtonsClickable(boolean canClick) {
		this.ballButton.setEnabled(canClick);
		this.baitButton.setEnabled(canClick);
		this.rockButton.setEnabled(canClick);
		this.runButton.setEnabled(canClick);
	}
		
	private class ButtonListener implements ActionListener {
		
		int count = 0; //counter for player sprite
		int count2 = 0; //counter for aerial object sprite
			
		// a timer for the throwing animation of the trainer
		private Timer makeTimer(String str){
			return new Timer(275, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					count++;
					battlePanel.throwObject(str);
					battlePanel.repaint();
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
		
		// a timer for the movement of the object 
		Timer aerialTimer = new Timer(350, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				aerialAniDone = false;
				count2++;
				battlePanel.throwAerial();
				battlePanel.repaint();
				{
					if(count2 == 5){
						((Timer) e.getSource()).stop();
						count2 = 0;
						battlePanel.doneThrowingAerial();
						aerialAniDone = true;		
						battlePanel.updateHPBar();
					}
				}
			}
			
		});

		// the action events various buttons and the respective action
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton buttonClicked = (JButton) arg0.getSource();
			
			Pokemon currentPokemon = theTrainer.getCurrentPokemon();
			
			// a thread for our buttons, to dictate when to toggle on and off
			ButtonsOffThread buttonsRunner = new ButtonsOffThread();
			Thread buttonWaiter = new Thread(buttonsRunner);
			
			// a thread for our animation to run before the pokemon runs
			RunAnimThread runAnimThread = new RunAnimThread();
			Thread runAnimWaiter = new Thread(runAnimThread);
			
			// a thread for the pokeball animation to occur before the pokemon is caught
			CatchAnimThread catchAnimThread = new CatchAnimThread();
			Thread catchAnimWaiter = new Thread(catchAnimThread);
			
			// for rock, increase chance to catch but increase chance to run
			if (buttonClicked.getText().equals("Rock")) {
				buttonWaiter.start();
				Timer rockTimer = makeTimer("Rock");
				rockTimer.start();
				aerialTimer.start();
 				theTrainer.getCurrentPokemon().useItem(theTrainer.getItemsList().get(1));
 				if (currentPokemon.pokemonRun()) {
 					buttonWaiter.stop();
 					setButtonsClickable(false);
					runAnimWaiter.start();					
				} else
					battleInfo.setText("\n  "+currentPokemon.getName() + " glares at you...");
			} 
			
			// for bait, decrease chance to run but decrease change to catch
			if (buttonClicked.getText().equals("Bait")) {
				buttonWaiter.start();
				Timer baitTimer = makeTimer("Bait");
				baitTimer.start();
				aerialTimer.start();
 				theTrainer.getCurrentPokemon().useItem(theTrainer.getItemsList().get(2));
 				if (currentPokemon.pokemonRun()) {
 					buttonWaiter.stop();
 					setButtonsClickable(false);
					runAnimWaiter.start();
				} else
					battleInfo.setText("\n  "+currentPokemon.getName() + " glares at you...");
			}
			
			// for pokeball
			if (buttonClicked.getText().equals("Pokeball")) {
				buttonWaiter.start();
				Timer ballTimer = makeTimer("Pokeball");
				ballTimer.start();
				aerialTimer.start();
				theTrainer.throwSafariBall();
							
				theTrainer.getCurrentPokemon().useItem(theTrainer.getItemsList().get(0));
				
				// generate a random number to determine if we caught the pokemon
				Random random = new Random();
				int theRand = random.nextInt(450);
				double maybeCatch = theRand / 1000.0;
				double catchProb = currentPokemon.getCatchProbability();
				System.out.println("RNG = " + maybeCatch + " CatchProb = " + catchProb);
				if (maybeCatch <= catchProb) {	
					//pokemon into pokeball animation, no time fo rthis
					buttonWaiter.stop();
					setButtonsClickable(false);
					catchAnimWaiter.start();
				} else
				if (currentPokemon.pokemonRun()) {
					buttonWaiter.stop();
 					setButtonsClickable(false);
					runAnimWaiter.start();
				} else
					battleInfo.setText("\n  "+currentPokemon.getName() + " glares at you...");
			}
			
			// for run being clicked
			if (buttonClicked.getText().equals("Run")) {
				theTrainer.ran();
			}
			
		}		
	}
	
	// the thread class for our catch animation
	private class CatchAnimThread implements Runnable {
		Pokemon currentPokemon = theTrainer.getCurrentPokemon();

		@Override
		public void run() {
			try {
				 synchronized(this) {
					 this.wait(2500);
				 }
	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			theTrainer.getPokedex().add(currentPokemon);
			battleInfo.setText("\n  "+"You caught " + currentPokemon.getName() + "!");try {
			synchronized(this) {
					 this.wait(1500);
				 }
	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			theTrainer.ran();
		}
		
	}
	
	// a thread for the animation before a pokemon runs
	private class RunAnimThread implements Runnable {
		Pokemon currentPokemon = theTrainer.getCurrentPokemon();
		@Override
		public void run() {
			try {
				 synchronized(this) {
					 this.wait(2500);
				 }
	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// probably should do something else here but this works for now
			battleInfo.setText("\n  "+currentPokemon.getName() + " ran away!");
			theTrainer.ran();
		}
	}
	
	// a thread to turn off our buttons for the time it takes our animation to run
	// whatever the animation may be
	private class ButtonsOffThread implements Runnable {

		@Override
		public void run() {
			setButtonsClickable(false);
			try {
				 synchronized(this) {
					 this.wait(2000);
				 }
	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setButtonsClickable(true);
		}
		
	}

}
