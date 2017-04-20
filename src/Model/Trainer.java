//Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
//File: Trainer.java
//Purpose: central object of pokemon safari zone. models a player of the game who stores a reference of the map.

package Model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;

import javax.imageio.ImageIO;

import items.*;
import pokemon.*;


//this class extends observable
public class Trainer extends Observable {

	/*
	 * Movement variables here
	 */
	private double x; 	//current x location
	private double y;	//current y location
	private double dx;	//change in x
	private double dy;	//change in y

	//keeps track of trainer's current and previous cell location on the tile map
	//this is useful for step counting
	private int prevRow, currRow, prevCol, currCol;

	//trainer's size
	private int width;
	private int height;

	//if true we are moving that direction
	//by design only one can be true at any single time
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	//variables used to calculate trainer's position
	//the trainers has a constant acceleration and maximum speed.
	//friction is used to bring the trainer to a halt.
	private double acceleration;
	private double maxVelocity;
	private double friction;

	//these booleans are used in collsion detection
	private boolean top, bottom, midLeft, midRight, topLeft, topRight, bottomLeft, bottomRight, center;

	//trainer stores a reference to the map
	private Map map;

	//the following variables are used for animating the trainer
	private Animation animation;
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

	public Object[] toSerialize() {
	    Object[] toSerialize = new Object[9];
	    toSerialize[0] = x;
	    toSerialize[1] = y;
	    toSerialize[2] = dx;
	    toSerialize[3] = dy;
	    toSerialize[4] = stepsTaken;
	    toSerialize[5] = facingLeft;
	    toSerialize[6] = facingRight;
	    toSerialize[7] = facingUp;
	    toSerialize[8] = facingDown;
	    
	    return toSerialize;
	}
	
	/*
	 * Hierarchy variables here
	 */

	private int stepsTaken; 	//number of steps the trainer has taken
	private boolean gameOver;	//true when game is over

	//the trainer stores a 
	private ArrayList<Pokemon> pokeDex;
	private ArrayList<CommonPokemon> commonCollection;
	private ArrayList<UncommonPokemon> uncommonCollection;
	private ArrayList<RarePokemon> rareCollection;
	private ArrayList<Item> items;

	/*
	 * End hierarchy variables
	 */

	public Trainer(Map map) {

		this.map = map;

		this.width = 50;
		this.height = 50;

		this.x = 78 / 2 * width;
		this.y = 110 / 2 * height;
		this.dx = 0;
		this.dy = 0;
		acceleration = 1;
		maxVelocity = 7;
		friction = .55;

		currRow = map.getRowTileIndex((int) y);
		currCol = map.getColTileIndex((int) x);
		stepsTaken = 0;
		gameOver = false;

		initCollections();

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
	
	//additional constructor used an existing state of the model is loaded
	public Trainer(Map map, Object[] toLoad){
		this.map = map;

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

	//initializes all lists currently stored in the trainer
	public void initCollections() {
		pokeDex = new ArrayList<Pokemon>();
		commonCollection = new ArrayList<CommonPokemon>();
		uncommonCollection = new ArrayList<UncommonPokemon>();
		rareCollection = new ArrayList<RarePokemon>();
		items = new ArrayList<Item>();

		items.add(new SafariBall());
		items.add(new Rock());
		items.add(new Bait());

		// Testing purposes?
		pokeDex.add(new Pikachu(4));

		// Placeholder Pokemon spawner for now
		commonCollection.add(new Abra(0));
		commonCollection.add(new Drowzee(1));
		commonCollection.add(new Grimer(2));
		commonCollection.add(new Pidgey(3));
		commonCollection.add(new Pikachu(4));
		commonCollection.add(new Staryu(5));

		uncommonCollection.add(new Graveler(6));
		uncommonCollection.add(new Haunter(7));
		uncommonCollection.add(new Rapidash(8));

		rareCollection.add(new Dragonair(9));
	}

	//The following setters allow the key listener on mapPanel,
	// to tell the player to move
	public void setLeft(boolean b) 	{ this.left = b;}
	public void setRight(boolean b) { this.right = b;}
	public void setUp(boolean b) 	{ this.up = b;}
	public void setDown(boolean b) 	{ this.down = b;}

	//this method is called in mapPanel update method
	//this method calculates the speed of the trainer and sets dx and dy accordingly, based on the direction the trainer is moving
	//this method method then checks whether the trainer is going to collide with an non-walkable object (such as a tree)
	//if the trainer is going to collide the trainers position is adjusted accordingly and the trainer stops movement in that direction
	//as a side task, this method also updates stepsTaken as the trainer moves from tile to tile.
	public void update() {
		// determine dx and dy based on the acceleration, current speed, and direction of the trainer
		if (left) { // moving left
			dx -= acceleration;
			facingLeft = true;
			facingDown = facingUp = facingRight = false;
			if (dx < -maxVelocity) {
				dx = -maxVelocity;
			}
		} else if (right) { // moving right
			dx += acceleration;
			facingRight = true;
			facingDown = facingLeft = facingUp = false;
			if (dx > maxVelocity) {
				dx = maxVelocity;
			}
		} else if (up) { // moving up
			dy -= acceleration;
			facingUp = true;
			facingDown = facingLeft = facingRight = false;
			if (dy < -maxVelocity) {
				dy = -maxVelocity;
			}
		} else if (down) { // moving down
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

		// update steps taken and notify mapView so that step count display is updated in view 
		prevRow = currRow;
		prevCol = currCol;
		currCol = map.getColTileIndex((int) x);
		currRow = map.getRowTileIndex((int) y);
		if (prevRow != currRow) {
			stepsTaken++;
			setChanged();
			notifyObservers();
		}
		if (prevCol != currCol) {
			stepsTaken++;
			setChanged();
			notifyObservers();
		}

		// collision check here
		
		//determine would be position of trainer
		double to_x = x + dx;
		double to_y = y + dy;

		//use temp variables for now to be careful
		double temp_x = x;
		double temp_y = y;
		
		//set instance variables based on whether the neighbors of would be position are blocked
		calculateNeighbors(to_x, to_y);
		
		//check these booleans and if collision is occurring the adjust trainer's position so he is stopped by the un-walkable tile
		//if no collision is going to occur then set trainers position to (x + dx, y + dy).
		//Note dx is scaled by 1.36 because our map is 1.36 times wider than tall.
		//This offset allows speed to appear more consistent in vertical and horizontal directions
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
		
		if(center){
			temp_y = y - dy*(2);
			temp_x = x - dx*(2);
		}
		//set x and y to trainer's new position
		x = temp_x;
		y = temp_y;

		// hard-coded dimensions of MapPanel
		// this keeps player centered at all times
		map.setX(750 / 2 - x);
		map.setY(550 / 2 - y);

		// sprite stuff
		if (facingDown) {
			if (dy > 0) {
				animation.setFrames(walkingDown);
				animation.setDelay(100);
			} else {
				animation.setFrames(standingDown);
				animation.setDelay(-1);
			}
		} else if (facingUp) {
			if (dy < 0) {
				animation.setFrames(walkingUp);
				animation.setDelay(100);
			} else {
				animation.setFrames(standingUp);
				animation.setDelay(-1);
			}
		} else if (facingLeft) {
			if (dx < 0) {
				animation.setFrames(walkingLeft);
				animation.setDelay(100);
			} else {
				animation.setFrames(standingLeft);
				animation.setDelay(-1);
			}
		} else if (facingRight) {
			if (dx > 0) {
				animation.setFrames(walkingRight);
				animation.setDelay(100);
			} else {
				animation.setFrames(standingRight);
				animation.setDelay(-1);
			}
		}

		animation.update();

		checkWinConditions();
	}

	//the win condtion for this iteration is going over 500 steps
	//this method checks stps taken and sets the boolean gameOver accordingly
	private void checkWinConditions() {
		if (stepsTaken == 500) {
			gameOver = true;
		} else if (pokeDex.size() == 10) {
			gameOver = true;
		}
	}

	//this method is used to check for collision
	//the method is passed the x and y coordinates of the trainer's would be position
	//this method sets instance variables to determine which type of collision is going to occur
	//the instance variables are set by checking the isBlocked boolean of the tile class
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
	}

	//draw method which draws trainer in the center of the mapPanel
	public void draw(Graphics2D g) {
		int tileX = map.getX();
		int tileY = map.getY();
		g.drawImage(animation.getImage(), (int) (tileX + x - width / 2), (int) (tileY + y - height / 2), null);
	}

	//getter for steps taken
	public int getStepCount() {
		return this.stepsTaken;
	}

}