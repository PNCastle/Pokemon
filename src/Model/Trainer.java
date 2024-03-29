/**
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: Trainer.java
 * Purpose: central object of pokemon safari zone. models a player of the game who stores a reference of the map.
 */

package Model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

import javax.imageio.ImageIO;

import items.*;
import pokemon.*;

//this class extends observable
public class Trainer extends Observable {

	/*
	 * Movement variables here
	 */
	private double x; // current x location
	private double y; // current y location
	private double dx; // change in x
	private double dy; // change in y

	// keeps track of trainer's current and previous cell location on the tile
	// map
	// this is useful for step counting
	private int prevRow, currRow, prevCol, currCol;

	// trainer's size
	private int width;
	private int height;

	// if true we are moving that direction
	// by design only one can be true at any single time
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	private boolean isOnBike;

	// variables used to calculate trainer's position
	// the trainers has a constant acceleration and maximum speed.
	// friction is used to bring the trainer to a halt.
	private double acceleration;
	private double maxVelocity;
	private double friction;

	// these booleans are used in collsion detection
	private boolean top, bottom, midLeft, midRight, topLeft, topRight, bottomLeft, bottomRight, center, spawning,
			hasItem, hasBike;

	// trainer stores a reference to the map
	private Map map;

	// the following variables are used for animating the trainer
	private Animation animation;
	private int delayTime = 100;

	private BufferedImage trainerRunning;
	private BufferedImage trainerBiking;

	private BufferedImage[] walkingLeft;
	private BufferedImage[] walkingRight;
	private BufferedImage[] walkingUp;
	private BufferedImage[] walkingDown;

	private BufferedImage[] standingLeft;
	private BufferedImage[] standingRight;
	private BufferedImage[] standingUp;
	private BufferedImage[] standingDown;

	boolean facingLeft;
	boolean facingRight;
	boolean facingUp;
	boolean facingDown;

	/*
	 * End movement variables
	 */

	//This object is created to store necessary game features,
	//So that our system is persistent
	public Object[] toSerialize() {
	    Object[] toSerialize = new Object[13];
	    toSerialize[0] = x;
	    toSerialize[1] = y;
	    toSerialize[2] = dx;
	    toSerialize[3] = dy;
	    toSerialize[4] = stepsTaken;
	    toSerialize[5] = facingLeft;
	    toSerialize[6] = facingRight;
	    toSerialize[7] = facingUp;
	    toSerialize[8] = facingDown;
	    toSerialize[9] = items;
	    toSerialize[10] = pokeDex;
	    toSerialize[11] = map.getMap();
	    
	    return toSerialize;
	}

	/*
	 * Hierarchy variables here
	 */

	private int stepsTaken; // number of steps the trainer has taken
	private boolean gameOver; // true when game is over

	private ArrayList<Pokemon> pokeDex; //list of pokemon that trainer has caught
	private ArrayList<Pokemon> commonCollection; //list of common pokemon that exist in the game
	private ArrayList<Pokemon> uncommonCollection; //list of uncommon pokemon that exist in the game
	private ArrayList<Pokemon> rareCollection; //list of rare pokemon that exist in the game
	private ArrayList<Item> items; //list of items that trainer currently has

	// this is set to pokemon that trainer encounters
	//i.e. the pokemon that trainer will battle
	private Pokemon currentPokemon;
	/*
	 * End hierarchy variables
	 */

	//ctor for trainer
	//The trainer is passed a map as parameter and stored as an instance variable to trainer
	//The trainers movement variables are set here such as:
	//starting position, speed, acceleration, friction, and other booleans
	public Trainer(Map map) {

		this.map = map; //store instance of map

		this.width = 50; //trainers width
		this.height = 50; //trainers height

		this.x = 78 / 2 * width; //trainers starting x position
		this.y = 110 / 2 * height;//trainers starting y position
		this.dx = 0; //set to zero since on construction no movement in x direction
		this.dy = 0; //set to zero since on construction no movement in y direction

		isOnBike = false; //trainer starts off bike
		acceleration = 1; //set acceleration, velocity, and friction
		maxVelocity = 7;
		friction = .55;

		currRow = map.getRowTileIndex((int) y); //initialize currRow & currCol
		currCol = map.getColTileIndex((int) x);
		stepsTaken = 0; //trainers begins with 0 steps taken
		gameOver = false;
		currentPokemon = null; //there is no currentPokemon upon construction
		initCollections();
		
		Collections.shuffle(commonCollection);
		currentPokemon = commonCollection.get(0);

		//load running and biking sprites and set current images to running
		try {
			walkingLeft = new BufferedImage[3];
			walkingRight = new BufferedImage[3];
			walkingDown = new BufferedImage[3];
			walkingUp = new BufferedImage[3];

			trainerRunning = ImageIO.read(new File("trainerRunning.png"));
			trainerBiking = ImageIO.read(new File("trainerOnBike.png"));

			standingLeft = new BufferedImage[1];
			standingRight = new BufferedImage[1];
			standingDown = new BufferedImage[1];
			standingUp = new BufferedImage[1];
			standingRight[0] = trainerRunning.getSubimage(200, 50, width, height);
			standingLeft[0] = trainerRunning.getSubimage(50, 50, width, height);
			standingDown[0] = trainerRunning.getSubimage(50, 0, width, height);
			standingUp[0] = trainerRunning.getSubimage(200, 0, width, height);

			for (int i = 0; i < 3; i++) {
				walkingRight[i] = trainerRunning.getSubimage(i * (width) + 150, 50, width, height);

				walkingLeft[i] = trainerRunning.getSubimage(i * (width), 50, width, height);

				walkingDown[i] = trainerRunning.getSubimage(i * (width), 0, width, height);

				walkingUp[i] = trainerRunning.getSubimage(i * (width) + 150, 0, width, height);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		//instantiate an instance of animation class
		//set trainer facing down upon construction
		animation = new Animation();
		animation.setFrames(standingDown);

	}

	// additional constructor used an existing state of the model is loaded
	public Trainer(Map map, Object[] toLoad) {
		this.map = map;
		map.setMap((int[][]) toLoad[11]); 

		this.width = 50;
		this.height = 50;

		this.x = (double) toLoad[0];
		this.y = (double) toLoad[1];
		this.dx = (double) toLoad[2];
		this.dy = (double) toLoad[3];
		acceleration = 1;
		maxVelocity = 10;
		friction = .5;

		currRow = map.getRowTileIndex((int) y);
		currCol = map.getColTileIndex((int) x);
		stepsTaken = (int) toLoad[4];
		gameOver = false;

		facingLeft = (boolean) toLoad[5];
		facingRight = (boolean) toLoad[6];
		facingUp = (boolean) toLoad[7];
		facingDown = (boolean) toLoad[8];

		initCollections();
		
		items = (ArrayList<Item>) toLoad[9];
		pokeDex = (ArrayList<Pokemon>) toLoad[10];
		
		Collections.shuffle(commonCollection);
		currentPokemon = commonCollection.get(0);

		try {
			walkingLeft = new BufferedImage[3];
			walkingRight = new BufferedImage[3];
			walkingDown = new BufferedImage[3];
			walkingUp = new BufferedImage[3];

			BufferedImage image = ImageIO.read(new File("trainerOneTrans.png"));

			standingLeft = new BufferedImage[1];
			standingRight = new BufferedImage[1];
			standingDown = new BufferedImage[1];
			standingUp = new BufferedImage[1];
			standingLeft[0] = image.getSubimage(148, 48, width, height);
			standingRight[0] = image.getSubimage(0, 48, width, height);
			standingDown[0] = image.getSubimage(0, 0, width, height);
			standingUp[0] = image.getSubimage(148, 0, width, height);

			for (int i = 0; i < 3; i++) {
				walkingLeft[i] = image.getSubimage(i * (width) + 148, 48, width, height);

				walkingRight[i] = image.getSubimage(i * (width), 48, width, height);

				walkingDown[i] = image.getSubimage(i * (width), 0, width, height);

				walkingUp[i] = image.getSubimage(i * (width) + 148, 0, width, height);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		animation = new Animation();
		animation.setFrames(standingDown);
	}

	//this method is used to change the sprites of the trainer
	//this method is used to have trainer mount and dismount bike
	public void switchImages(BufferedImage toSwitch) {
		try {
			standingRight[0] = toSwitch.getSubimage(200, 50, width, height);
			standingLeft[0] = toSwitch.getSubimage(50, 50, width, height);
			standingDown[0] = toSwitch.getSubimage(50, 0, width, height);
			standingUp[0] = toSwitch.getSubimage(200, 0, width, height);

			for (int i = 0; i < 3; i++) {
				walkingRight[i] = toSwitch.getSubimage(i * (width) + 150, 50, width, height);

				walkingLeft[i] = toSwitch.getSubimage(i * (width), 50, width, height);

				walkingDown[i] = toSwitch.getSubimage(i * (width), 0, width, height);

				walkingUp[i] = toSwitch.getSubimage(i * (width) + 150, 0, width, height);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

//		animation = new Animation();
//		animation.setFrames(standingDown);

	}


	// initializes all lists currently stored in the trainer
	public void initCollections() {
		pokeDex = new ArrayList<Pokemon>();
		commonCollection = new ArrayList<Pokemon>();
		uncommonCollection = new ArrayList<Pokemon>();
		rareCollection = new ArrayList<Pokemon>();
		items = new ArrayList<Item>();

		items.add(new SafariBall());
		items.add(new Rock());
		items.add(new Bait());
		items.add(new Bike());
		items.add(new Potion());

		// Testing purposes?
		pokeDex.add(new Pikachu(25));

		// Placeholder Pokemon spawner for now
		commonCollection.add(new Abra(63));
		commonCollection.add(new Drowzee(96));
		commonCollection.add(new Grimer(88));
		commonCollection.add(new Pidgey(16));
		commonCollection.add(new Pikachu(25));
		commonCollection.add(new Staryu(120));

		uncommonCollection.add(new Graveler(75));
		uncommonCollection.add(new Haunter(93));
		uncommonCollection.add(new Rapidash(78));

		rareCollection.add(new Dragonair(148));
	}

	//throws a pokeball
	//decrements number of pokeballs by 1
	public void throwSafariBall() {
		items.get(0).useOne();
	}

	//return number of pokeballs trainer currently has
	public int safariBallCount() {
		return items.get(0).amount();
	}

	// The following setters allow the key listener on mapPanel,
	// to tell the player to move
	public void setLeft(boolean b) {
		this.left = b;
	}

	public void setRight(boolean b) {
		this.right = b;
	}

	public void setUp(boolean b) {
		this.up = b;
	}

	public void setDown(boolean b) {
		this.down = b;
	}

	// this method is called in mapPanel update method
	// this method calculates the speed of the trainer and sets dx and dy
	// accordingly, based on the direction the trainer is moving
	// this method method then checks whether the trainer is going to collide
	// with an non-walkable object (such as a tree)
	// if the trainer is going to collide the trainers position is adjusted
	// accordingly and the trainer stops movement in that direction
	// as a side task, this method also updates stepsTaken as the trainer moves
	// from tile to tile.
	public void update() {
		// determine dx and dy based on the acceleration, current speed, and
		// direction of the trainer
		if (left) { // moving left
			dx -= acceleration;
			facingLeft = true;
			facingDown = facingUp = facingRight = false;
			if (dx < -maxVelocity) {
				dx = -maxVelocity;
			}
		}
		if (right) { // moving right
			dx += acceleration;
			facingRight = true;
			facingDown = facingLeft = facingUp = false;
			if (dx > maxVelocity) {
				dx = maxVelocity;
			}
		}
		if (up) { // moving up
			dy -= acceleration;
			facingUp = true;
			facingDown = facingLeft = facingRight = false;
			if (dy < -maxVelocity) {
				dy = -maxVelocity;
			}
		}
		if (down) { // moving down
			dy += acceleration;
			facingDown = true;
			facingUp = facingLeft = facingRight = false;
			if (dy > maxVelocity) {
				dy = maxVelocity;
			}
		}

		// apply friction in any direction the player is moving

		// stopping in x direction
		if (dx != 0) {
			if (dx > 0) {
				dx -= friction;
				if (dx < 0) {
					dx = 0;
				}
			} else {
				dx += friction;
				if (dx > 0) {
					dx = 0;
				}
			}
		}
		// stopping in y direction
		if (dy != 0) {
			if (dy > 0) {
				dy -= friction;
				if (dy < 0) {
					dy = 0;
				}
			} else {
				dy += friction;
				if (dy > 0) {
					dy = 0;
				}
			}
		}

		// update steps taken and notify mapView so that step count display is
		// updated in view
		prevRow = currRow;
		prevCol = currCol;
		currCol = map.getColTileIndex((int) x);
		currRow = map.getRowTileIndex((int) y);

		// collision check here

		// determine would be position of trainer
		double to_x = x + dx;
		double to_y = y + dy;

		// use temp variables for now to be careful
		double temp_x = x;
		double temp_y = y;

		// set instance variables based on whether the neighbors of would be
		// position are blocked
		calculateNeighbors(to_x, to_y);

		//when a new cell of map is reached update steps taken
		//and check if the trainer encountered a pokemon
		if (prevRow != currRow) {
			stepsTaken++;
			setChanged();
			notifyObservers(stepsTaken);
			if (spawning && (dx != 0 || dy != 0) && !isOnBike) {
				double prob = Math.random();
//				System.out.println(prob);
				if (prob >= .85) {
					this.setCurrentPokemon(prob);
					// send currentPokemon to battlePanel
//					System.out.println(currentPokemon.getName());
					setChanged();
					notifyObservers(-1);
				}
			}
		}
		//when a new cell of map is reached update steps taken
		//and check if the trainer encountered a pokemon
		if (prevCol != currCol) {
			stepsTaken++;
			setChanged();
			notifyObservers(stepsTaken);
			if (spawning && (dx != 0 || dy != 0) && !isOnBike) {
				double prob = Math.random();
				System.out.println(prob);
				if (prob >= .85) {
					this.setCurrentPokemon(prob);
					// send currentPokemon to battlePanel
					// System.out.println(currentPokemon.getName());
					setChanged();
					notifyObservers(-1);
				}
			}
		}

		// check these booleans and if collision is occurring the adjust
		// trainer's position so he is stopped by the un-walkable tile
		// if no collision is going to occur then set trainers position to (x +
		// dx, y + dy).
		// Note dx is scaled by 1.36 because our map is 1.36 times wider than
		// tall.
		// This offset allows speed to appear more consistent in vertical and
		// horizontal directions
		if (dy < 0) {
			if (topLeft || top || topRight) {
				dy = 0;
				temp_y = currRow * map.getTileSize() + height / 2;
			} else {
				temp_y += dy;
			}
		}
		if (dy > 0) {
			if (bottomLeft || bottom || bottomRight) {
				dy = 0;
				temp_y = (currRow + 1) * map.getTileSize() - height / 2;
			} else {
				temp_y += dy;
			}
		}
		if (dx < 0) {
			if (left) {
				dx = 0;
				temp_x = currCol * map.getTileSize() + width / 2;
			} else {
				temp_x += dx * (1.36);
			}
		}
		if (dx > 0) {
			if (right) {
				dx = 0;
				temp_x = (currCol + 1) * map.getTileSize() - width / 2;
			} else {
				temp_x += dx * (1.36);
			}
		}

		if (center) {
			temp_y = y - dy * (2);
			temp_x = x - dx * (2);
		}
		// set x and y to trainer's new position
		x = temp_x;
		y = temp_y;

		// currCol = map.getColTileIndex((int) x);
		// currRow = map.getRowTileIndex((int) y);

		//if the trainer encounters a cell with a pokeball then,
		//add one pokeball to the trainers item list and remove the item from the map
		if (hasItem) {
			this.items.get(0).addOne();
			map.removeItem(map.getRowTileIndex((int) y), map.getColTileIndex((int) x));
		}
		//if the trainer encounters a cell with a bike then,
		//add one bike to the trainers item list and remove the item from the map
		if (hasBike) {
			this.items.get(3).addOne();
			map.removeItem(map.getRowTileIndex((int) y), map.getColTileIndex((int) x));
		}

		// hard-coded dimensions of MapPanel
		// this keeps player centered at all times
		map.setX(750 / 2 - x);
		map.setY(550 / 2 - y);

		// sprite stuff
		if (facingDown) {
			if (dy > 0) {
				animation.setFrames(walkingDown);
				animation.setDelay(delayTime);
			} else {
				animation.setFrames(standingDown);
				animation.setDelay(-1);
			}
		} else if (facingUp) {
			if (dy < 0) {
				animation.setFrames(walkingUp);
				animation.setDelay(delayTime);
			} else {
				animation.setFrames(standingUp);
				animation.setDelay(-1);
			}
		} else if (facingLeft) {
			if (dx < 0) {
				animation.setFrames(walkingLeft);
				animation.setDelay(delayTime);
			} else {
				animation.setFrames(standingLeft);
				animation.setDelay(-1);
			}
		} else if (facingRight) {
			if (dx > 0) {
				animation.setFrames(walkingRight);
				animation.setDelay(delayTime);
			} else {
				animation.setFrames(standingRight);
				animation.setDelay(-1);
			}
		}
		animation.update();
		// checkWinConditions();
	}

	//this method is used to set currentPokemon when our spawn probability is high enough
	public void setCurrentPokemon(double prob) {
		if (prob >= .975) {
			Collections.shuffle(rareCollection);
			currentPokemon = rareCollection.get(0);
		} else if (prob >= .925) {
			Collections.shuffle(uncommonCollection);
			currentPokemon = uncommonCollection.get(0);
		} else {
			Collections.shuffle(commonCollection);
			currentPokemon = commonCollection.get(0);
		}
	}

	// the win condition for this iteration is going over 500 steps
	// this method checks steps taken and sets the boolean gameOver accordingly
	/*
	 * private void checkWinConditions() { if (stepsTaken >= 500) { gameOver =
	 * true; } else if (pokeDex.size() == 10) { gameOver = true; } }
	 */

	// this method is used to check for collision
	// the method is passed the x and y coordinates of the trainer's would be
	// position
	// this method sets instance variables to determine which type of collision
	// is going to occur
	// the instance variables are set by checking the isBlocked boolean of the
	// tile class
	private void calculateNeighbors(double y, double x) {
		int rowIndex = map.getRowTileIndex((int) y);
		int colIndex = map.getColTileIndex((int) x);
		int leftIndex = map.getColTileIndex((int) (x - width / 2));
		int rightIndex = map.getColTileIndex((int) (x + width / 2) - 1);
		int topIndex = map.getColTileIndex((int) (y - height / 2));
		int bottomIndex = map.getColTileIndex((int) (y + height / 2) - 1);

		center = (map.isBlocked(rowIndex, colIndex));
		left = (map.isBlocked(rowIndex, leftIndex));
		right = (map.isBlocked(rowIndex, rightIndex));

		top = (map.isBlocked(topIndex, colIndex));
		bottom = (map.isBlocked(bottomIndex, colIndex));

		topLeft = (map.isBlocked(topIndex, leftIndex));
		topRight = (map.isBlocked(topIndex, rightIndex));

		bottomLeft = (map.isBlocked(bottomIndex, leftIndex));
		bottomRight = (map.isBlocked(bottomIndex, rightIndex));

		spawning = map.isSpawnable(rowIndex, colIndex);
		hasItem = map.hasItem(rowIndex, colIndex);
		hasBike = map.hasBike(rowIndex, colIndex);
	}

	// draw method which draws trainer in the center of the mapPanel
	public void draw(Graphics2D g) {
		int tileX = map.getX();
		int tileY = map.getY();
		g.drawImage(animation.getImage(), (int) (tileX + x - width / 2), (int) (tileY + y - height / 2), null);
	}

	// getter for steps taken
	public int getStepCount() {
		return this.stepsTaken;
	}

	// getter for Pokedex
	public ArrayList<Pokemon> getPokedex() {
		return pokeDex;
	}

	// setter for dy for testing
	public void setDy(double vel) {
		this.dy = vel;
	}

	// setter for dx for testing
	public void setDx(double vel) {
		this.dx = vel;
	}

	//trainer ran from battle
	public void ran() {
		setChanged();
		//-2 is passed so that appropriate observer knows we are transitioning
		//from battleView to mapView
		notifyObservers(-2);
	}

	//getter for currentPokemon
	public Pokemon getCurrentPokemon() {
		return this.currentPokemon;
	}

	//getter for currentPokemon's ID
	public int getCurrentPokemonID() {
		return this.currentPokemon.getPokemonID();

	}

	//getter for item list
	public ArrayList<Item> getItemsList() {
		return items;
	}

	//getter that returns if trainer is currently on bike
	public boolean isOnBike() {
		return this.isOnBike;
	}

	//method to dismount bike
	//sets speed variables back to walking rates
	//changes sprite to walking sprite
	public void dismountBike() {
		this.isOnBike = false;
		this.acceleration = 1;
		this.maxVelocity = 10;
		this.friction = .5;
		switchImages(trainerRunning);
	}

	//method to mount bike
	//sets speed variables to bike rates
	//sets sprite to bike sprites
	public void mountBike() {
		if (this.items.get(3).amount() != 0) {
			this.isOnBike = true;
			this.acceleration = 5;
			this.maxVelocity = 25;

			this.friction = 3;
			switchImages(trainerBiking);
		}
	}
	
	//getter for map instance variable
	//used for testing purposes
	public Map getMap(){
		return this.map;
	}
	
	//this method is used for testing
	public void setHasBikeNItem(boolean hasBike) {
		this.hasBike = hasBike;
		this.hasItem = hasBike;
	}

	//for testing purposes
	public ArrayList<Pokemon> getCommon(){
		return this.commonCollection;
	}
	
	//for testing purposes
	public ArrayList<Pokemon> getRare(){
		return this.rareCollection;
	}
	
	//for testing purposes
	public ArrayList<Pokemon> getUncommon(){
		return this.uncommonCollection;
	}

}
