//Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
//File: PokemonGUI.java
//Purpose: Main controller in Pokemon Safari Zone application

package Controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Model.Pokemon;
import Model.Trainer;
import View.BattleView;
//import View.BattleView;
import View.MapView;
import View.PokedexPanel;
import View.TrainerPanel;
import songplayer.*;

//pokemon class that extends JFrame
public class PokemonGUI extends JFrame implements ActionListener, Observer, KeyListener {

	// instance variables
	private static final int HEIGHT = 1000;
	private static final int WIDTH = 1000;
	private MapView mapView;
	private BattleView battleView;
	private JPanel currentView;
	private Trainer theTrainer;
	private Thread mapMusic;
	private Thread battleMusic;
	private EndOfSongListener mapMusicListener;
	private EndOfSongListener battleMusicListener;

	// simple main method
	public static void main(String args[]) {
		PokemonGUI g = new PokemonGUI();
		g.setVisible(true);
	}

	// ctor
	public PokemonGUI() {
		// set standard information such as size, title etc.
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("Pokemon Safari Zone");
		this.setLocation(0, 0);

		// this section of code makes the system persistant, so that we can load
		// and save games
		File file = new File("persistence");

		if (file.exists()) {
			int ret = JOptionPane.showOptionDialog(null,
					"Start with previous game?\n"
							+ "No means start with a new game.\nIf no, previous game can be loaded from menu.",
					"Select an Option", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

			// try to read the file
			if (ret == JOptionPane.YES_OPTION) {
				try {
					FileInputStream fis = new FileInputStream(file);
					try {
						ObjectInputStream ois = new ObjectInputStream(fis);
						try {
							Object[] toLoad = (Object[]) ois.readObject();
							mapView = new MapView(WIDTH, HEIGHT, toLoad);
						} catch (ClassNotFoundException e) {
							System.err.println("Could not read persistence file");
						}
						ois.close();
					} catch (IOException e) {
						System.err.println("Could not read persistence file");
					}
				} catch (FileNotFoundException e1) {
					System.err.println("Could not read persistence file");
				}
			} else {
				Object[] mapChoice = { "Map 2", "Map 1" };

				int retMap = JOptionPane.showOptionDialog(null, "Which map would you like to start with?",
						"Select an Option", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, mapChoice,
						null);

				if (retMap == JOptionPane.YES_OPTION) {
					mapView = new MapView(WIDTH, HEIGHT, "mapTwo.txt");
				} else {
					mapView = new MapView(WIDTH, HEIGHT, "mapOne.txt");
				}
				battleView = new BattleView(WIDTH, HEIGHT);
			}
		} else {
			Object[] mapChoice = { "Map 2", "Map 1" };

			int retMap = JOptionPane.showOptionDialog(null, "Which map would you like to start with?",
					"Select an Option", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, mapChoice, null);

			if (retMap == JOptionPane.YES_OPTION) {
				mapView = new MapView(WIDTH, HEIGHT, "mapTwo.txt");
			} else {
				mapView = new MapView(WIDTH, HEIGHT, "mapOne.txt");
			}
			battleView = new BattleView(WIDTH, HEIGHT);
		}
		
		Object[] winConditionChoice = { "Catch one of each Pokemon", "Catch 20 total Pokemon"};
		int retWinCond = JOptionPane.showOptionDialog(null, "Choose win condition.",
				"Select an Option", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, winConditionChoice,
				null);
		if(retWinCond == JOptionPane.YES_OPTION){
			mapView.setWinCondition("catchEmAll");
		}
		else{
			mapView.setWinCondition("catchTwenty");
		}

		// add mapView as an observer of the trainer
		theTrainer = mapView.getTrainer();
		theTrainer.addObserver(mapView);
		theTrainer.addObserver(battleView);
		theTrainer.addObserver(this);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus();
		setViewTo(mapView); // set default view to map view
		setUpMenus(); // build menu system
		music();

		WindowListener exit = new WindowListener();
		this.addWindowListener(exit);
	}

	// additional constructor used in load game
	public PokemonGUI(Object[] toLoad) {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("Pokemon Safari Zone");
		this.setLocation(0, 0);
		// initialize mapView and add as an observer of trainer
		mapView = new MapView(WIDTH, HEIGHT, toLoad);
		theTrainer = mapView.getTrainer();
		theTrainer.addObserver(mapView);
		setViewTo(mapView); // set default view to map view
		setUpMenus();
		music();

		WindowListener exit = new WindowListener();
		this.addWindowListener(exit);
	}

	private void music() {
		mapMusicListener = new EndOfSongListener() {

			@Override
			public void songFinishedPlaying(EndOfSongEvent eventWithFileNameAndDateFinished) {
				mapMusic = new AudioFilePlayer("music/map.wav");
				mapMusic.start();
			}
		};

		battleMusicListener = new EndOfSongListener() {

			@Override
			public void songFinishedPlaying(EndOfSongEvent eventWithFileNameAndDateFinished) {
				mapMusic = new AudioFilePlayer("music/battle.wav");
				mapMusic.start();
			}
		};

		mapMusic = new AudioFilePlayer("music/map.wav");
		((AudioFilePlayer) mapMusic).addEndOfSongListener(mapMusicListener);
		mapMusic.start();
	}

	// private helper method whose purpose is to build the menu system
	// this menu system contains features such as: new game, save game, load
	// game, and others
	private void setUpMenus() {
		JMenuItem menu = new JMenu("Options");
		JMenuItem newGame = new JMenuItem("New Game");
		menu.add(newGame);
		JMenuItem saveGame = new JMenuItem("Save Game");
		menu.add(saveGame);
		JMenuItem loadGame = new JMenuItem("Load Game");
		menu.add(loadGame);
		JMenuItem forfeit = new JMenuItem("Forfeit");
		menu.add(forfeit);
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

		JMenuItem stepCount = new JMenu("Step Count: " + mapView.getTrainer().getStepCount());
		stepCount.setEnabled(false);

		// set up menu bar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(menu);
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(stepCount);

		// add listeners
		menu.addActionListener(this);
		newGame.addActionListener(this);
		saveGame.addActionListener(this);
		loadGame.addActionListener(this);
		forfeit.addActionListener(this);
		trainer.addActionListener(this);
		pokedex.addActionListener(this);
		viewTrainer.addActionListener(this);
		hideTrainer.addActionListener(this);
		viewPokedex.addActionListener(this);
		hidePokedex.addActionListener(this);

		Runnable updateSteps = new Runnable() {

			@Override
			public void run() {

				while (true) {
					stepCount.setText("Step Count: " + mapView.getTrainer().getStepCount());
				}

			}
		};

		new Thread(updateSteps).start();
	}

	// helper method to change views
	public void setViewTo(JPanel newView) {
		if (currentView != null) {
			remove(currentView);
		}
		currentView = newView;
		add(currentView);
		currentView.repaint();
		validate();
	}

	// //// MENU ITEM LISTENER ////////////////

	@Override
	public void actionPerformed(ActionEvent e) {
		String text = ((JMenuItem) e.getSource()).getText();

		// if new game is selected build a new instance of the GUI and
		// dispose of the old one
		if (text.equals("New Game")) {
			PokemonGUI g = new PokemonGUI();
			g.setVisible(true);
			setVisible(false);
			dispose();
		}

		// if save game is selected then write the current state of the
		// system to file
		if (text.equals("Save Game")) {
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

		// if load game is selected the construct a new GUI object from the
		// system information that was saved
		if (text.equals("Load Game")) {
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

		if (text.equals("Forfeit")) {
			JOptionPane.showMessageDialog(null, "Game Over!");
			this.setViewTo(new PokedexPanel(mapView.getTrainer()));
			this.add(new TrainerPanel(mapView.getTrainer()), BorderLayout.CENTER);
			
			repaint();
		}

		if (text.equals("View Trainer")) {
			mapView.setSecondaryView(0);
			this.requestFocus();
		}

		if (text.equals("Hide Trainer")) {
			mapView.setSecondaryView(1);
			this.requestFocus();
		}

		if (text.equals("Battle Start")) {
			// mapView.animateOut(this, battleView);
		}

		if (text.equals("Battle End")) {
			mapView.enableMapPanel();
		}

		if (text.equals("View Pokedex")) {
			mapView.setSecondaryView(2);
			this.requestFocus();
		}

		if (text.equals("Hide Pokedex")) {
			mapView.setSecondaryView(3);
			this.requestFocus();
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	public void update(Observable o, Object arg) {
		if ((int) arg == -1) {
			mapMusic.stop();
			mapView.animateOut(this, battleView);

			battleMusic = new AudioFilePlayer("music/battle.wav");
			((AudioFilePlayer) battleMusic).addEndOfSongListener(battleMusicListener);
			battleMusic.start();
		}
		if ((int) arg == -2) {
			AudioFilePlayer runSound = new AudioFilePlayer("music/run.wav");
			runSound.play();

			battleMusic.stop();
			mapMusic = new AudioFilePlayer("music/map.wav");
			((AudioFilePlayer) mapMusic).addEndOfSongListener(mapMusicListener);
			mapMusic.start();
			mapView.enableMapPanel();
			setViewTo(mapView);
		}
	}

	// unused, ignore for now
	// may be used in iteration 2
	@Override
	public void keyTyped(KeyEvent key) {
	}

	// listen for any of the arrow keys being pressed
	// if they are set the direction of theTrainer object
	// to true so that theTrainer can move in that direction
	// only one direction boolean may be flagged to true at a time
	@Override
	public void keyPressed(KeyEvent key) {
		int code = key.getKeyCode();
		if(code == KeyEvent.VK_B){
			if(theTrainer.isOnBike()){
				theTrainer.dismountBike();
			}
			else{
				theTrainer.mountBike();
			}
		}
		if (code == KeyEvent.VK_LEFT) {
			theTrainer.setLeft(true);
			// set other directions to false
			theTrainer.setDown(false);
			theTrainer.setUp(false);
			theTrainer.setRight(false);
		}
		if (code == KeyEvent.VK_RIGHT) {
			theTrainer.setRight(true);
			// set other directions to false
			theTrainer.setLeft(false);
			theTrainer.setUp(false);
			theTrainer.setDown(false);

		}
		if (code == KeyEvent.VK_UP) {
			theTrainer.setUp(true);
			// set other directions to false
			theTrainer.setDown(false);
			theTrainer.setLeft(false);
			theTrainer.setRight(false);
		}
		if (code == KeyEvent.VK_DOWN) {
			theTrainer.setDown(true);
			// set other directions to false
			theTrainer.setUp(false);
			theTrainer.setLeft(false);
			theTrainer.setRight(false);
		}
	}

	// listen for any of the arrow keys being released
	// if they are set the direction of theTrainer object
	// to false so that theTrainer can stop in that direction
	@Override
	public void keyReleased(KeyEvent key) {
		int code = key.getKeyCode();
		if (code == KeyEvent.VK_LEFT) {
			theTrainer.setLeft(false);
		}
		if (code == KeyEvent.VK_RIGHT) {
			theTrainer.setRight(false);
		}
		if (code == KeyEvent.VK_UP) {
			theTrainer.setUp(false);
		}
		if (code == KeyEvent.VK_DOWN) {
			theTrainer.setDown(false);
		}
	}

	/**
	 * A listener for when the user closes the application. Offers a save option
	 * before the application is closed.
	 */
	private class WindowListener extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			int ret = JOptionPane.showOptionDialog(null, "Would you like to save the game?", "Select an Option",
					JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

			if (ret == JOptionPane.YES_OPTION) {
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
		}
	}

}