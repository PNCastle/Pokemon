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

public class Trainer extends Observable {

	/*
	 * Movement variables here
	 */
	private double x;
	private double y;
	private double dx;
	private double dy;

	private int prevRow, currRow, prevCol, currCol;

	private int width;
	private int height;

	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	private double acceleration;
	private double maxVelocity;
	private double friction;

	private boolean top, bottom, midLeft, midRight, topLeft, topRight, bottomLeft, bottomRight;

	private Map map;

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

	private int stepsTaken;
	private boolean gameOver;

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

			// walkingLeft[0] = ImageIO.read(new File("trainerOneTrans.png"));
			// walkingRight[0] = ImageIO.read(new File("trainerOneTrans.png"));
			// walkingUp[0] = ImageIO.read(new File("trainerOneTrans.png"));
			// walkingDown[0] = ImageIO.read(new File("trainerOneTrans.png"));

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

			// walkingLeft[0] = ImageIO.read(new File("trainerOneTrans.png"));
			// walkingRight[0] = ImageIO.read(new File("trainerOneTrans.png"));
			// walkingUp[0] = ImageIO.read(new File("trainerOneTrans.png"));
			// walkingDown[0] = ImageIO.read(new File("trainerOneTrans.png"));

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

	public void update() {
		// determine next position
		// animation.setFrames(standingDown);
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

		// stopping
		if (dx != 0) { // stopping in x direction
			if (dx > 0) {
				dx -= friction;
				if (dx < 0) {
					dx = 0;
//					facingRight = true;
//					facingDown = facingLeft = facingUp = false;
//					animation.setFrames(standingRight);
				}
			} else {
				dx += friction;
				if (dx > 0) {
					dx = 0;
//					facingLeft = true;
//					facingDown = facingUp = facingRight = false;
//					animation.setFrames(standingLeft);
				}
			}
		}
		if (dy != 0) { // stopping in y direction
			if (dy > 0) {
				dy -= friction;
				if (dy < 0) {
					dy = 0;
//					facingUp = true;
//					facingDown = facingLeft = facingRight = false;
				}
			} else {
				dy += friction;
				if (dy > 0) {
					dy = 0;
//					facingDown = true;
//					facingUp = facingLeft = facingRight = false;
				}
			}
		}

		// update steps taken
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
		double to_x = x + dx;
		double to_y = y + dy;

		double temp_x = x;
		double temp_y = y;
		calculateNeighbors(to_x, to_y);
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
		// calculateNeighbors(to_x, y);
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
		/*
		 * if (x < to_x) { facingRight = true; } if (x > to_x) { facingLeft =
		 * true; } if (y < to_y) { facingDown = true; } if (y > to_y) { facingUp
		 * = true; }
		 */
		x = temp_x;
		y = temp_y;

		// hardcoded dimensions of MapPanel
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

		// stepsTaken++;
		checkWinConditions();
	}

	private void checkWinConditions() {
		if (stepsTaken == 500) {
			gameOver = true;
		} else if (pokeDex.size() == 10) {
			gameOver = true;
		}
	}

	private void calculateNeighbors(double y, double x) {
		int rowIndex = map.getRowTileIndex((int) y);
		int colIndex = map.getColTileIndex((int) x);
		int leftIndex = map.getColTileIndex((int) (x - width / 2));
		int rightIndex = map.getColTileIndex((int) (x + width / 2) - 1);
		int topIndex = map.getColTileIndex((int) (y - height / 2));
		int bottomIndex = map.getColTileIndex((int) (y + height / 2) - 1);

		left = (map.isBlocked(rowIndex, leftIndex)); // == 0);
		right = (map.isBlocked(rowIndex, rightIndex)); // == 0);

		top = (map.isBlocked(topIndex, colIndex)); // == 0);
		bottom = (map.isBlocked(bottomIndex, colIndex)); // == 0);

		topLeft = (map.isBlocked(topIndex, leftIndex)); // == 0);
		topRight = (map.isBlocked(topIndex, rightIndex)); // == 0);

		bottomLeft = (map.isBlocked(bottomIndex, leftIndex)); // == 0);
		bottomRight = (map.isBlocked(bottomIndex, rightIndex)); // == 0);
	}

	public void draw(Graphics2D g) {
		int tileX = map.getX();
		int tileY = map.getY();

		// g.setColor(Color.BLACK);
		// g.fillRect(tileX + 600, tileY + 50, 100, 50);
		// g.drawString("Number of steps taken: " + this.getStepCount(), tileX +
		// 600, tileY + 52);

		// g.setColor(Color.RED);
		// g.fillRect((int) (tileX + x - width / 2), (int) (tileY + y - height /
		// 2), width, height);

		if (true) {
			g.drawImage(animation.getImage(), (int) (tileX + x - width / 2), (int) (tileY + y - height / 2), null);
		}

	}

	public int getStepCount() {
		return this.stepsTaken;
	}

}