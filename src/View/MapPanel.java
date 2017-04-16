package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Model.Map;
import Model.Trainer;

public class MapPanel extends JPanel implements Runnable, KeyListener {

	//instance variables
	static final int WIDTH = 750; //assuming tile size is 50*50
	static final int HEIGHT = 550;
	private Thread thread;
	private boolean running;
	
	private BufferedImage image;
	private Graphics2D g;
	
	private int FPS = 30;
	private int targetTime = 1000/ FPS;
	
	private Map theMap;
	private Trainer theTrainer;
	
	//ctor
	public MapPanel(){
		//super();
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setSize(this.getPreferredSize());
		this.setVisible(true);
		this.setFocusable(true);
		this.requestFocus();
		
	}
	
	public void addNotify(){
		super.addNotify();
		if(thread == null){
			thread = new Thread(this);
			thread.start();
		}
		this.addKeyListener(this);
	}
	
	@Override
	public void run() {
		
		this.init();
		long startTime, urdTime, waitTime;
		
		while(running){
			startTime = System.nanoTime();
			update();
			render();
			draw();
			
			urdTime = (System.nanoTime() - startTime) / 100000;
			waitTime = Math.abs(targetTime - urdTime);
			
			try{
				Thread.sleep(waitTime);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private void init(){
		running = true;
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		
		
		theMap = new Map("mapOne.txt",50);
		theTrainer = new Trainer(theMap);
		//load tiles for map here
	}
	
	private void update(){
		theMap.update();
		theTrainer.update();
	}
	
	private void render(){
		theMap.draw(g);
		theTrainer.draw(g);
	}
	
	private void draw(){
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}
	
	
	
	
	
	
	
	
	
	
	
	//////////// KEY LISTENER CODE ///////////////////////////////
	//ignore
	@Override
	public void keyTyped(KeyEvent e) {	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
