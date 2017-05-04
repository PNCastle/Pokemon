/*
 * Authors:  Paul Castleberry, Angel Burr, Sohyun Kim, Isaac Kim
 * Filename: BattlePanel.java
 * Purpose:  The interior battle panel of the GUI. Contains the panel for all graphics
 * 			 to be drawn.
 */

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
import Model.Pokemon;
import Model.Trainer;

public class BattlePanel extends JPanel {

	static final int WIDTH = 750;
	static final int HEIGHT = 550;

	private BufferedImage image;

	private BufferedImage[] backGround;

	private BufferedImage[] throwingObj;
	private BufferedImage[] standingStill;

	private BufferedImage[] ballImages;
	private BufferedImage[] baitImages;
	private BufferedImage[] rockImages;
	private BufferedImage[] nullSpace;

	private BufferedImage[] abra;
	private BufferedImage[] dragonair;
	private BufferedImage[] drowzee;
	private BufferedImage[] graveler;
	private BufferedImage[] grimer;
	private BufferedImage[] haunter;
	private BufferedImage[] pidgey;
	private BufferedImage[] pikachu;
	private BufferedImage[] rapidash;
	private BufferedImage[] staryu;

	private Animation backDrop;
	private Animation animation;
	private Animation aerial;
	private Animation pokemonAni;

	private int toSpawn;

	//ctor
	public BattlePanel() {

		makeTrainer();
		makeBackground();
		makeThrowAnimations();
		makePokemon();

		this.setLayout(null);
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setSize(this.getPreferredSize());
		this.setVisible(true);

		/// g.drawImage(standingStill[0], 0, 0, null);

		// this.setFocusable(true);
		// this.requestFocus();

	}

	// construct the images for all the pokemon from the image file pokemonBattleSprites.png
	public void makePokemon() {
		try {
			BufferedImage image = ImageIO.read(new File("pokemonBattleSprites.png"));
			pokemonInit();

			abra[0] = image.getSubimage(175 * 0, 0, 175, 175);
			dragonair[0] = image.getSubimage(175 * 1, 0, 175, 175);
			drowzee[0] = image.getSubimage(175 * 2, 0, 175, 175);
			graveler[0] = image.getSubimage(175 * 3, 0, 175, 175);
			grimer[0] = image.getSubimage(175 * 4, 0, 175, 175);
			haunter[0] = image.getSubimage(175 * 0, 175, 175, 175);
			pidgey[0] = image.getSubimage(175 * 1, 175, 175, 175);
			pikachu[0] = image.getSubimage(175 * 2, 175, 175, 175);
			rapidash[0] = image.getSubimage(175 * 3, 175, 175, 175);
			staryu[0] = image.getSubimage(175 * 4, 175, 175, 175);

			if (toSpawn == 63) {
				pokemonAni.setFrames(abra);
			}
			if (toSpawn == 148) {
				pokemonAni.setFrames(dragonair);
			}
			if (toSpawn == 96) {
				pokemonAni.setFrames(drowzee);
			}
			if (toSpawn == 75) {
				pokemonAni.setFrames(graveler);
			}
			if (toSpawn == 88) {
				pokemonAni.setFrames(grimer);
			}
			if (toSpawn == 93) {
				pokemonAni.setFrames(haunter);
			}
			if (toSpawn == 16) {
				pokemonAni.setFrames(pidgey);
			}
			if (toSpawn == 25) {
				pokemonAni.setFrames(pikachu);
			}
			if (toSpawn == 78) {
				pokemonAni.setFrames(rapidash);
			}
			if (toSpawn == 120) {
				pokemonAni.setFrames(staryu);
			}

			pokemonAni.setDelay(-1);
			pokemonAni.update();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// paint the background image for the battle panel
	private void makeBackground() {
		try {
			backGround = new BufferedImage[1];

			BufferedImage image = ImageIO.read(new File("battleBackground.png"));
			backGround[0] = image.getSubimage(0, 0, 750, 550);
		} catch (Exception e) {
			e.printStackTrace();
		}
		backDrop.setFrames(backGround);
		backDrop.setDelay(-1);
		backDrop.update();
	}

	// construct the various image arrays for all of our throwing animations
	private void makeThrowAnimations() {
		try {
			ballImages = new BufferedImage[5];
			baitImages = new BufferedImage[5];
			rockImages = new BufferedImage[5];
			nullSpace = new BufferedImage[1];

			BufferedImage imageBall = ImageIO.read(new File("ballAnimation.png"));
			nullSpace[0] = imageBall.getSubimage(0, 1100, 750, 550);
			for (int i = 0; i < 5; i++) {
				ballImages[i] = imageBall.getSubimage(i * 750, 0, 750, 550);
			}
			BufferedImage imageBait = ImageIO.read(new File("baitAnimation.png"));
			for (int i = 0; i < 5; i++) {
				baitImages[i] = imageBait.getSubimage(i * 750, 0, 750, 550);
			}
			BufferedImage imageRock = ImageIO.read(new File("rockAnimation.png"));
			for (int i = 0; i < 5; i++) {
				rockImages[i] = imageRock.getSubimage(i * 750, 0, 750, 550);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		// trying stuff here
		aerial.setFrames(nullSpace);
		aerial.setDelay(-1);
		aerial.update();
	}

	// construct the image array for the trainer in battle for animation of throws
	private void makeTrainer() {
		try {
			throwingObj = new BufferedImage[5];
			standingStill = new BufferedImage[1];

			BufferedImage image = ImageIO.read(new File("maleTrainerBattle.png"));

			standingStill[0] = image.getSubimage(0, 0, 192, 195);

			for (int i = 0; i < 5; i++) {
				throwingObj[i] = image.getSubimage(i * (192), 0, 192, 195);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		animation = new Animation();
		aerial = new Animation();
		backDrop = new Animation();
		pokemonAni = new Animation();
		// aerial.setFrames(nullSpace);
		// aerial.setDelay(-1);
		// aerial.update();

		animation.setFrames(standingStill);
		animation.setDelay(-1);
		animation.update();

	}

	// dictates which object to throw and starts the animation
	public void throwObject(String string) {
		animation.setFrames(throwingObj);
		animation.setDelay(200);
		switch (string) {
		case "Rock":
			aerial.setFrames(rockImages);
			break;
		case "Pokeball":
			aerial.setFrames(ballImages);
			break;
		case "Bait":
			aerial.setFrames(baitImages);
			break;
		}
		aerial.setDelay(300);
		animation.update();

	}

	// required painting of all images
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backDrop.getImage(), 0, 0, null);
		g.drawImage(aerial.getImage(), 0, 0, null);
		g.drawImage(animation.getImage(), 0, 355, null);
		g.drawImage(pokemonAni.getImage(), 435, 200, null);
	}

	// begin throwing our aerial object
	public void throwAerial() {
		aerial.update();
	}

	// reset the throwing animations to the starting frame
	public void doneThrowing() {
		animation.setFrames(standingStill);
		animation.setDelay(-1);
		animation.update();

	}

	// reset the thrown object to the starting frame(empty/null space)
	public void doneThrowingAerial() {
		aerial.setFrames(nullSpace);
		aerial.setDelay(-1);
		aerial.update();
		
		
		
	}

	// initialize all pokemon image arrays
	private void pokemonInit() {

		abra = new BufferedImage[1];
		dragonair = new BufferedImage[1];
		drowzee = new BufferedImage[1];
		graveler = new BufferedImage[1];
		grimer = new BufferedImage[1];
		haunter = new BufferedImage[1];
		pidgey = new BufferedImage[1];
		pikachu = new BufferedImage[1];
		rapidash = new BufferedImage[1];
		staryu = new BufferedImage[1];

	}

	// inform the battle panel which pokemon is about to spawn
	public void setToSpawn(int currentPokemonID) {
		this.toSpawn = currentPokemonID;
	}

}
