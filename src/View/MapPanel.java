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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Model.Map;
import Model.Trainer;

public class MapPanel extends JPanel implements Runnable, KeyListener {

	//instance variables
	static final int WIDTH = 750;
	static final int HEIGHT = 550;
	private Thread thread;
	private boolean running;
	
	private BufferedImage image;
	private Graphics2D g;
	
	private int FPS = 15;
	private int targetTime = 1000/FPS;
	
	private Map theMap;
	private Trainer theTrainer;
	
	//ctor
	public MapPanel(){
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setSize(this.getPreferredSize());
		this.setVisible(true);
		this.setFocusable(true);
		this.requestFocus();
		theMap = new Map("mapTwo.txt", 50);
		theTrainer = new Trainer(theMap);	
	}
	
	//additional constructor for saving and loading games
	public MapPanel(Object[] toLoad){
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setSize(this.getPreferredSize());
		this.setVisible(true);
		this.setFocusable(true);
		this.requestFocus();
		theMap = new Map("mapTwo.txt", 50);
		theTrainer = new Trainer(theMap, toLoad);
		
	}
	
	//addNotify, start threads and add key listeners to
	//the mapPanel
	public void addNotify(){
		super.addNotify();
		if(thread == null){
			thread = new Thread(this);
			thread.start();
		}
		this.addKeyListener(this);
	}
	
	//run the thread, calculate the time between
	//updates and check for win conditions
	//as long as the thread is running the trainer
	//and map are updated, drawn, and rendered.
	@Override
	public void run() {
		
		this.init();
		long startTime, urdTime, waitTime;
		
		while(running){
			startTime = System.nanoTime();
			update();
			render();
			draw();
			
			//calculate wait time to sleep the thread
			urdTime = (System.nanoTime() - startTime) / 100000;
			waitTime = Math.abs(targetTime - urdTime);
			
			try{
				Thread.sleep(waitTime);
			} catch(Exception e){
				e.printStackTrace();
			}
			
			//check for win conditions
			if (theTrainer.getStepCount() == 500){
				JOptionPane.showMessageDialog(null, "Game Over! 500 steps taken.");
				break;
			}
		}
	}
	
	//init, set variables running, image, g, and load the tiles
	private void init(){
		running = true;
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();		
		theMap.loadTiles("resizedTiles.png");
	}
	
	//update theMap and theTrainer objects
	private void update(){
		theMap.update();
		theTrainer.update();
	}
	
	//render theMap and theTrainer objects
	private void render(){
		theMap.draw(g);
		theTrainer.draw(g);
	}
	
	//draw the graphics
	private void draw(){
		Graphics g2 = getGraphics();
		if (g2 != null){
			g2.drawImage(image, 0, 0, null);
			g2.dispose();
		}
	}
	
	//retrieve theTrainer for this mapPanel
	public Trainer getTrainer() {
		return theTrainer;
	}
		
	/*********************
	 * KEYCODE LISTENERS *
	 *********************/
	
	//unused, ignore for now
	//may be used in iteration 2
	@Override
	public void keyTyped(KeyEvent key) {	
	}
	
	//listen for any of the arrow keys being pressed
	//if they are set the direction of theTrainer object
	//to true so that theTrainer can move in that direction
	//only one direction boolean may be flagged to true at a time
	@Override
	public void keyPressed(KeyEvent key) {
		int code = key.getKeyCode();
		if(code == KeyEvent.VK_LEFT){
			theTrainer.setLeft(true);
			//set other directions to false
			theTrainer.setDown(false);
			theTrainer.setUp(false);
			theTrainer.setRight(false);
		}
		if(code == KeyEvent.VK_RIGHT){
			theTrainer.setRight(true);
			//set other directions to false
			theTrainer.setLeft(false);
			theTrainer.setUp(false);
			theTrainer.setDown(false);
			
		}
		if(code == KeyEvent.VK_UP){
			theTrainer.setUp(true);
			//set other directions to false
			theTrainer.setDown(false);
			theTrainer.setLeft(false);
			theTrainer.setRight(false);
		}
		if(code == KeyEvent.VK_DOWN){
			theTrainer.setDown(true);
			//set other directions to false
			theTrainer.setUp(false);
			theTrainer.setLeft(false);
			theTrainer.setRight(false);
		}
	}


	//listen for any of the arrow keys being released
	//if they are set the direction of theTrainer object
	//to false so that theTrainer can stop in that direction
	@Override
	public void keyReleased(KeyEvent key) {
		int code = key.getKeyCode();
		if(code == KeyEvent.VK_LEFT){
			theTrainer.setLeft(false);
		}
		if(code == KeyEvent.VK_RIGHT){
			theTrainer.setRight(false);
		}
		if(code == KeyEvent.VK_UP){
			theTrainer.setUp(false);
		}
		if(code == KeyEvent.VK_DOWN){
			theTrainer.setDown(false);
		}
	}
}
