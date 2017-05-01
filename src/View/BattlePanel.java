package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Model.Animation;
import Model.Map;
import Model.Trainer;

public class BattlePanel extends JPanel {
	
	static final int WIDTH = 750;
	static final int HEIGHT = 550;
	
	private BufferedImage image;
//	private Graphics2D g;
	
	private Map theMap;
	private Trainer theTrainer;
	
	private BufferedImage[] throwingObj;
	private BufferedImage[] standingStill;
	
	private BufferedImage[] ballImages;
	private BufferedImage[] nullSpace;
	
	private Animation animation;
	private Animation aerial;

	public BattlePanel() {
		
		makeTrainer();
		makeThrowAnimations();
		
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setSize(this.getPreferredSize());
		this.setVisible(true);
		
///		g.drawImage(standingStill[0], 0, 0, null);
		
	//	this.setFocusable(true);
	//	this.requestFocus();
		
	}
	
	private void makeThrowAnimations() {
		try {
			ballImages = new BufferedImage[5];
			nullSpace = new BufferedImage[1];
			
			BufferedImage image = ImageIO.read(new File("ballAnimation.png"));
			
			nullSpace[0] = image.getSubimage(0, 550, 750, 550);
			
			for (int i = 0; i < 5; i++) {
				ballImages[i] = image.getSubimage(i*750, 0, 750, 550);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//trying stuff here
		aerial.setFrames(nullSpace);
		aerial.setDelay(-1);
		aerial.update();
	}

	private void makeTrainer() {
		try {
			throwingObj = new BufferedImage[5];
			standingStill = new BufferedImage[1];
			
			BufferedImage image = ImageIO.read(new File("maleTrainerBattle.png"));
			
			
			standingStill[0] = image.getSubimage(0, 0, 192, 195);
			
			for (int i = 0; i < 5; i++) {
				throwingObj[i] = image.getSubimage(i*(192), 0, 192, 195);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		aerial = new Animation();
		//aerial.setFrames(nullSpace);
		//aerial.setDelay(-1);
		//aerial.update();
		
		animation.setFrames(standingStill);
		animation.setDelay(-1);
		animation.update();
		
	}
	

	public void throwObject(String string) {
		animation.setFrames(throwingObj);
		animation.setDelay(200);
		switch(string){
		case "Rock":
			//aerial.setFrames(rockImages);
			break;
		case "Pokeball":
			aerial.setFrames(ballImages);
			break;
		case "Bait":
			//aerial.setFrames(baitImages);
			break;
		}
		aerial.setDelay(300);
		animation.update();
				
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//if(aerial != null){
			g.drawImage(aerial.getImage(), 0, 0, null);
		//}
		g.drawImage(animation.getImage(), 0, 355, null);
		
		
			
	}
	
	public void throwAerial(){
		aerial.update();
	}

	public void doneThrowing() {
		animation.setFrames(standingStill);
		animation.setDelay(-1);
		animation.update();
		
	}

	public void doneThrowingAerial() {
		aerial.setFrames(nullSpace);
		aerial.setDelay(-1);
		aerial.update();
	}
}
