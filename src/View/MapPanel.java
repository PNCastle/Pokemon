/*
 * Authors:  Paul Castleberry, Angel Burr, Sohyun Kim, Isaac Kim
 * Filename: MapPanel.java
 * Purpose:  The interior map of the GUI. Contains the game itself and the
 * 			 listeners.
 */

package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import Model.Map;
import Model.Pokemon;
import Model.Trainer;


public class MapPanel extends JPanel implements Runnable {

	// instance variables
	static final int WIDTH = 750;
	static final int HEIGHT = 550;
	private Thread thread;
	private boolean running;
	private boolean inBattle;

	//used to get graphics object that is passed to trainer and map, so they can draw themselves
	private BufferedImage image;
	private Graphics2D g;

	private int FPS = 50;
	private int targetTime = 1000 / FPS;

	//used in transition animation
	int red = 255;
	int green = 255;
	int blue = 255;

	//mapPanel stores instance of map and trainer so they can draw themselves
	private Map theMap;
	private Trainer theTrainer;
	
	//win condition set in GUI
	//allows us to check whether user has won game
	private String winCondition;


	// ctor
	public MapPanel(String map) {
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setSize(this.getPreferredSize());
		this.setVisible(true);
		this.setFocusable(true);
		this.requestFocus();
		theMap = new Map(map, 50);
		theTrainer = new Trainer(theMap);

		inBattle = false;
	}

	// additional constructor for saving and loading games
	public MapPanel(String map, Object[] toLoad) {
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setSize(this.getPreferredSize());
		this.setVisible(true);
		this.setFocusable(true);
		this.requestFocus();
		theMap = new Map(map, 50);
		theTrainer = new Trainer(theMap, toLoad);

		inBattle = false;
	}

	// addNotify, start threads and add key listeners to
	// the mapPanel
	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}
	 
	//helps handle transition between battleView and mapView
	public void battleMode() {
		if (!inBattle) {
			inBattle = true;
			JPanel thisPanel = this;

			new Timer(3, new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					red -= 1;
					green -= 1;
					blue -= 1;

					thisPanel.setBackground(new Color(red, green, blue));

					if (red == 0) {
						((Timer) e.getSource()).stop();
					}
				}
			}).start();

		}
	}

	//helps handle the transition between battleView and mapView
	public void mapMode() {
		theTrainer.setUp(false);
		theTrainer.setDown(false);
		theTrainer.setLeft(false);
		theTrainer.setRight(false);

		inBattle = false;
		red = green = blue = 255;
		this.requestFocus();
	}
	/*
	 * public void enableKeyListener(){ this.addKeyListener(this);
	 * this.requestFocus(); }
	 */

	// run the thread, calculate the time between
	// updates and check for win conditions
	// as long as the thread is running the trainer
	// and map are updated, drawn, and rendered.
	@Override
	public void run() {

		this.init();
		long startTime, urdTime, waitTime;

		while (running) {
			while (inBattle) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			startTime = System.nanoTime();
			update();
			render();
			draw();

			// calculate wait time to sleep the thread
			urdTime = (System.nanoTime() - startTime) / 100000;
			waitTime = Math.abs(targetTime - urdTime);

			try {
				Thread.sleep(waitTime);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// check for lose conditions, end game if true
			if (theTrainer.getStepCount() == 500) {
				JOptionPane.showMessageDialog(null,
						"Game Over! 500 steps taken.");
				break;
			}
			if (theTrainer.safariBallCount() == 0){
				JOptionPane.showMessageDialog(null,
						"Game Over! All 30 Safari Balls used.");
				break;
			}
			//check for win condition, end game if true
			if(didWin()){
				JOptionPane.showMessageDialog(null, "Game Over, you win!");
				break;
			}
		}
	}
	
	

	// init, set variables running, image, g, and load the tiles
	private void init() {
		running = true;
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		theMap.loadTiles("resizedTiles2.png");
	}

	// update theMap and theTrainer objects
	private void update() {
		theMap.update();
		theTrainer.update();
	}

	// render theMap and theTrainer objects
	private void render() {
		theMap.draw(g);
		theTrainer.draw(g);
	}

	// draw the graphics
	private void draw() {
		Graphics g2 = getGraphics();
		if (g2 != null) {
			g2.drawImage(image, 0, 0, null);
			g2.dispose();
		}
	}

	// retrieve theTrainer for this mapPanel
	public Trainer getTrainer() {
		return theTrainer;
	}

	//setter method for winCondition variable
	public void setWinCondition(String string) {
		this.winCondition = string;
	}
	
	//determines whether user has won game
	//based on win condition selected at start of game
	public boolean didWin(){
		switch(winCondition){
		case "catchEmAll":
			return caughtEmAll();
		default:
			return caughtTwenty();
		}
	}
	
	//returns true if at least oen of each type of pokemon is caught
	//there are 10 total types
	private boolean caughtEmAll(){
		int howMany = 1;
		ArrayList<Pokemon> pokedex = theTrainer.getPokedex();
		Collections.sort(pokedex);
		for(int i = 0; i < pokedex.size() - 1; i++){
			if(pokedex.get(i+1).getPokemonID() != pokedex.get(i).getPokemonID()){
				howMany++;
			}
		}
		return (howMany == 10);
	}
	
	//returns true if pokedex is greater than or equal to 20 in size
	private boolean caughtTwenty(){
		return (theTrainer.getPokedex().size() >= 20);
	}

}